package backend

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import backend.config.ApplicationConfig.{host, port}

import scala.concurrent.ExecutionContext

object Main extends Router {
  implicit val system: ActorSystem = ActorSystem("recipe-ideas")
  implicit val executor: ExecutionContext = system.dispatcher
  implicit val materializer: ActorMaterializer = ActorMaterializer()

  def main(args: Array[String]): Unit =
    Http().bindAndHandle(routes, host, port)
}
