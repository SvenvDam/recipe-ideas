package backend.routes

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import backend.Neo4jRepository
import backend.model.Ingredient
import spray.json._

import scala.util.{Failure, Success}

case class IngredientRoute(route: Route) extends BaseRoute

object IngredientRoute extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val ingredientFormat = jsonFormat1(Ingredient)

  def apply(neo4jRepository: Neo4jRepository) = {
    val route = path("ingredient") {
      post {
        entity(as[Ingredient]) { ingredient =>
          extractExecutionContext { implicit executor =>
            val result = neo4jRepository.postIngredient(ingredient)
            onComplete(result) {
              case Success(ing) => complete(ing)
              case Failure(e) => complete(500, e)
            }
          }
        }
      } ~
      get {
        extractExecutionContext { implicit executor =>
          val results = neo4jRepository.getIngredients
          onComplete(results) {
            case Success(ings) => complete(ings)
            case Failure(e) => complete(500, e)
          }
        }
      }
    }
    new IngredientRoute(route)
  }
}