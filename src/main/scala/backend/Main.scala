package backend

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer

import scala.concurrent.ExecutionContext

object Main {
  def main(args: Array[String]) = {
    val host = "0.0.0.0"
    val port = 9000

    implicit val system: ActorSystem = ActorSystem("hello-world")
    implicit val executor: ExecutionContext = system.dispatcher
    implicit val materializer: ActorMaterializer = ActorMaterializer()

    val route = path("ping") {
      get {
        complete("pong")
      }
    }

    Http().bindAndHandle(route, host, port)
  }
}
