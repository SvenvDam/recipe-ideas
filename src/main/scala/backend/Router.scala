package backend

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import backend.route._

class Router(neo4jRepository: Neo4jRepository) {
  import Router._
  private val endpoints = Seq(
    PingRoute(neo4jRepository),
    IngredientRoute(neo4jRepository)
  )

  val routes: Route = endpoints.foldLeft(indexRoute) {
    case (acc, endpoint) => acc ~ endpoint.route
  }
}

object Router {
  val indexRoute = path("/") {
    get {
      complete(200, "OK")
    }
  }
}
