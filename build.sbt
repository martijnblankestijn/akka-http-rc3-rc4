name := "akka-http-rc3-4-troubles"

version := "1.0"

scalaVersion := "2.11.7"

//couchdb
resolvers += "Scalaz Bintray Repo" at "https://dl.bintray.com/scalaz/releases"

libraryDependencies ++= {
  val akkaV = "2.3.11"
  val akkaStreamV = "1.0-RC3"
  val monocleV = "1.0.1"
  Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaV,
    "com.typesafe.akka" %% "akka-http-experimental" % akkaStreamV,

    //test deps
    "com.typesafe.akka" %% "akka-http-testkit-experimental" % akkaStreamV % "test",
    "org.scalatest" %% "scalatest" % "2.2.5" % "test"
  )
}
