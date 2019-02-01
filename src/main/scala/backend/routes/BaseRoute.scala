package backend.routes

import akka.http.scaladsl.server.Route

trait BaseRoute {
  val route: Route
}
