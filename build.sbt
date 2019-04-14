import Dependencies._

name := "recipe-ideas"
version := "0.1"
scalaVersion := "2.11.12"
assemblyJarName in assembly := name.value + "-backend.jar"

Compile / mainClass := Some("backend.Main")

libraryDependencies ++= Seq(
  akka,
  akkaHttp,
  akkaHttpCore,
  akkaHttpSpray,
  akkaSlf4j,
  akkaStream,
  logBack,
  neo4jDriver,
  neoTypes,
  scalaLogging,
  shapeless,

  akkaTestkit,
  akkaHttpTestkit,
  akkaStreamTestkit,
  jerseyCore,
  mockito,
  neo4jHarness,
  scalaTest
)
