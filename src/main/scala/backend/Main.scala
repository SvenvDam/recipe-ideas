package backend

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import backend.config.ApplicationConfig.{host, port}

import scala.concurrent.ExecutionContext

object Main {
  implicit val system: ActorSystem = ActorSystem("recipe-ideas")
  implicit val executor: ExecutionContext = system.dispatcher
  implicit val materializer: ActorMaterializer = ActorMaterializer()

  val neo4jRepository = new Neo4jRepository
  val routes = new Router(neo4jRepository).routes

  def main(args: Array[String]): Unit =
    Http().bindAndHandle(routes, host, port)
}

