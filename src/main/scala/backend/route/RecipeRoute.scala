package backend.route

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import backend.Neo4jRepository
import backend.model._
import com.typesafe.scalalogging.LazyLogging
import org.neo4j.driver.v1.summary.ResultSummary
import spray.json._

import scala.util.{Failure, Success}

case class RecipeRoute(route: Route) extends BaseRoute

object RecipeRoute extends SprayJsonSupport with DefaultJsonProtocol with LazyLogging {

  implicit val ingredientFormat = jsonFormat1(Ingredient)
  implicit val recipeFormat = jsonFormat4(Recipe)

  def apply(neo4jRepository: Neo4jRepository) = {
    val route = path("recipe") {
      extractExecutionContext { implicit executor =>
        post {
          entity(as[Recipe]) { recipe =>
            val result = neo4jRepository.postRecipe(recipe)
            onComplete(result) {
              case Success(r: ResultSummary) => complete(200, s"Executed query: ${r.statement.text}")
              case Failure(e) => complete(500, e)
            }
          }
        } ~ get {
          val results = neo4jRepository.getAllRecipes
          onComplete(results) {
            case Success(recipes) => complete(recipes)
            case Failure(e) => complete(500, e)
          }
        }
      }
    }
    new RecipeRoute(route)
  }
}
