package backend

import akka.http.scaladsl.server.Directives._
import backend.routes._

trait Router
  extends PingRoute with JsonRoute {

  val routes = pingRoute ~ jsonRoute
}
