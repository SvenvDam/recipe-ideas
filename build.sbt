import Dependencies._

name := "recipe-ideas"
version := "0.1"
scalaVersion := "2.12.7"
assemblyJarName in assembly := name.value + "-backend.jar"

Compile / mainClass := Some("backend.Main")

libraryDependencies ++= Seq(
  akka,
  akkaStream,
  akkaHttp,
  akkaSlf4j,
  akkaHttpSpray,
  akkaLogging,
  logBack,

  akkaTestkit,
  akkaStreamTestkit,
  akkaHttpTestkit,
  scalaTest,
  mockito
)