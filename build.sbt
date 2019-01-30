name := "recipe-ideas"

version := "0.1"

scalaVersion := "2.12.7"

Compile/mainClass := Some("backend.Main")

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.5.19",
  "com.typesafe.akka" %% "akka-stream" % "2.5.19",
  "com.typesafe.akka" %% "akka-http" % "10.1.3",
  "org.scalatest" %% "scalatest" % "3.0.5" % Test,
)