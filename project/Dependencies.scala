import sbt._

object Dependencies {
  lazy val akkaVersion = "2.5.19"
  lazy val akkaHttpVersion = "10.1.5"

  lazy val akka = "com.typesafe.akka" %% "akka-actor" % akkaVersion
  lazy val akkaHttp = "com.typesafe.akka" %% "akka-http" % akkaHttpVersion
  lazy val akkaHttpCore = "com.typesafe.akka" %% "akka-http-core" % akkaHttpVersion
  lazy val akkaHttpSpray = "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion
  lazy val akkaStream = "com.typesafe.akka" %% "akka-stream" % akkaVersion
  lazy val akkaSlf4j = "com.typesafe.akka" %% "akka-slf4j" % akkaVersion
  lazy val logBack = "ch.qos.logback" % "logback-classic" % "1.2.3"
  lazy val neo4jDriver = "org.neo4j.driver" % "neo4j-java-driver" % "1.6.3"
  lazy val neoTypes = "com.dimafeng" %% "neotypes" % "0.4.0"
  lazy val scalaLogging = "com.typesafe.scala-logging" %% "scala-logging" % "3.9.0"
  lazy val shapeless = "com.chuusai" %% "shapeless" % "2.3.3"
  lazy val sprayJson = "io.spray" %% "spray-json" % "1.3.4"

  // Test dependencies
  lazy val akkaTestkit = "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test
  lazy val akkaHttpTestkit = "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion % Test
  lazy val akkaStreamTestkit = "com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion % Test
  lazy val jerseyCore = "com.sun.jersey" % "jersey-core" % "1.19" % Test
  lazy val mockito = "org.mockito" % "mockito-all" % "1.9.5" % Test
  lazy val neo4jHarness = "org.neo4j.test" % "neo4j-harness" % "3.3.1" % Test
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.5" % Test
}
