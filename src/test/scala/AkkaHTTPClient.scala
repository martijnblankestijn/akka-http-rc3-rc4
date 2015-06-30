import akka.http.ServerSettings
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpRequest
import akka.http.scaladsl.testkit.ScalatestRouteTest
import com.typesafe.config.ConfigFactory
import org.scalatest.{FlatSpec, Matchers}

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import scala.language.postfixOps

class AkkaHTTPClient extends FlatSpec with Matchers with ScalatestRouteTest {
  "The Akka HTTP Client" should "be able to execute requests in parallel" in {
    printConfig

    val futResponses = (0 until 20)
      .map(i => Http().singleRequest(HttpRequest(uri = "http://akka.io"))
        .map(response => {
          print(response.toString)
          response
        }))
    val result = Future.sequence(futResponses)

    print("Before waiting")
    Await.ready(result, 20 second)
    print("After await ready")
  }

  def printConfig: Unit = {
    val config = ConfigFactory.load()
    println(s"Akka version     : ${config.getString("akka.version")}")
    println(s"Akka http Version: ${ServerSettings.getClass.getPackage.getImplementationVersion}")
    println(s"Max Connections  : ${config.getString("akka.http.host-connection-pool.max-connections")}")
    println(s"Client Timeout   : ${config.getString("akka.http.client.connecting-timeout")}")
  }

  def print(msg: String): Unit = println(System.currentTimeMillis() + " = " + msg)
}

