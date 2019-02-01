package backend.routes

import akka.http.scaladsl.model.{HttpEntity, HttpResponse, MediaTypes}
import akka.http.scaladsl.testkit.ScalatestRouteTest
import backend.Neo4jRepository
import backend.model.Ingredient
import com.typesafe.scalalogging.LazyLogging
import org.mockito.Mockito._
import org.scalatest.mockito.MockitoSugar
import org.scalatest.{FunSuiteLike, Matchers}

import scala.concurrent.Future

class IngredientRouteTest
  extends FunSuiteLike
    with ScalatestRouteTest
    with Matchers
    with MockitoSugar
    with LazyLogging {

  val repo = mock[Neo4jRepository]
  val route = IngredientRoute(repo).route

  test("it should retrieve multiple results") {
   when(repo.getIngredients).thenReturn(
     Future.successful(Seq(Ingredient("carrot"), Ingredient("onion"))))

    Get("/ingredient") ~> route ~> check {
      response shouldEqual HttpResponse(
        entity = HttpEntity(MediaTypes.`application/json`,
          """[{"name":"carrot"},{"name":"onion"}]"""))
    }
    verify(repo).getIngredients
  }

  test("it should input a new ingredient") {
    when(repo.postIngredient(Ingredient("carrot"))).thenReturn(
      Future.successful(Ingredient("carrot"))
    )

    Post("/ingredient", HttpEntity(MediaTypes.`application/json`, """{"name": "carrot"}""")) ~> route ~> check {
      response shouldEqual HttpResponse(
        entity = HttpEntity(MediaTypes.`application/json`,
          """{"name":"carrot"}"""))
    }
    verify(repo).postIngredient(Ingredient("carrot"))
  }

  test("it should return 500 on neo4j failure") {
    when(repo.getIngredients).thenReturn(
      Future.failed(new Exception("test exception")))

    Get("/ingredient") ~> route ~> check {
      status.intValue shouldEqual 500
    }
  }
}