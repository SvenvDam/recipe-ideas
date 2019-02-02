package backend.route

import akka.http.scaladsl.server.Directives.{complete, get, path}
import akka.http.scaladsl.server.Route
import backend.Neo4jRepository

case class PingRoute(route: Route) extends BaseRoute

object PingRoute {
  def apply(neo4jRepository: Neo4jRepository) = {
    val route = path("ping") {
      get {
        complete("pong")
      }
    }
    new PingRoute(route)
  }
}
