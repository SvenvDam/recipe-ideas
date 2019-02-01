import sbt._

object Dependencies {
  lazy val akkaVersion = "2.5.19"
  lazy val akkaHttpVersion = "10.1.5"

  lazy val akka = "com.typesafe.akka" %% "akka-actor" % akkaVersion
  lazy val akkaStream = "com.typesafe.akka" %% "akka-stream" % akkaVersion
  lazy val akkaSlf4j = "com.typesafe.akka" %% "akka-slf4j" % akkaVersion
  lazy val akkaHttp = "com.typesafe.akka" %% "akka-http" % akkaHttpVersion
  lazy val akkaHttpSpray = "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion
  lazy val akkaLogging = "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2"
  lazy val logBack = "ch.qos.logback" % "logback-classic" % "1.2.3"

  // Test dependencies
  lazy val akkaTestkit = "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test
  lazy val akkaStreamTestkit = "com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion % Test
  lazy val akkaHttpTestkit = "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion % Test
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.5" % Test
}
