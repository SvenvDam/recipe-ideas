package backend.route

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import backend.Neo4jRepository
import backend.model.Ingredient
import org.neo4j.driver.v1.summary.ResultSummary
import spray.json._

import scala.util.{Failure, Success}

case class IngredientRoute(route: Route) extends BaseRoute

object IngredientRoute extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val ingredientFormat = jsonFormat1(Ingredient)

  def apply(neo4jRepository: Neo4jRepository) = {
    val route = path("ingredient") {
      extractExecutionContext { implicit executor =>
        post {
          entity(as[Ingredient]) { ingredient =>
            val result = neo4jRepository.insertIngredient(ingredient)
            onComplete(result) {
              case Success(r: ResultSummary) => complete(200, s"Executed query: ${r.statement.text}")
              case Failure(e) => complete(500, e)
            }
          }
        } ~ get {
          val results = neo4jRepository.getAllIngredients
          onComplete(results) {
            case Success(ings) => complete(ings.toList)
            case Failure(e) => complete(500, e)
          }
        }
      }
    }
    new IngredientRoute(route)
  }
}