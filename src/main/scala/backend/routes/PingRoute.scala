package backend.routes

import akka.http.scaladsl.server.Directives.{complete, get, path}

trait PingRoute {
  val pingRoute = path("ping") {
    get {
      complete("pong")
    }
  }
}
