package backend.route

import akka.http.scaladsl.model.{HttpEntity, HttpResponse, MediaTypes}
import akka.http.scaladsl.testkit.ScalatestRouteTest
import backend.Neo4jRepository
import backend.model.Ingredient
import org.mockito.Mockito.{verify, when}
import org.neo4j.driver.internal.summary.InternalResultSummary
import org.neo4j.driver.v1.Statement
import org.neo4j.driver.v1.summary._
import org.scalatest.FunSuiteLike
import org.scalatest.Matchers._
import org.scalatest.mockito.MockitoSugar._

import scala.concurrent.Future

class IngredientRouteTest extends FunSuiteLike with ScalatestRouteTest {
  import IngredientRouteTest._
  val repo = mock[Neo4jRepository]
  val route = IngredientRoute(repo).route

  test("it should retrieve all ingredients") {
   when(repo.getAllIngredients).thenReturn(
     Future.successful(Seq(Ingredient("carrot"), Ingredient("onion"))))

    Get("/ingredient") ~> route ~> check {
      response shouldEqual HttpResponse(
        entity = HttpEntity(MediaTypes.`application/json`,
          """[{"name":"carrot"},{"name":"onion"}]"""))
    }
    verify(repo).getAllIngredients
  }

  test("it should input a new ingredient") {
    when(repo.postIngredient(Ingredient("carrot"))).thenReturn(
      Future.successful(resultSummary("CREATE (carrot:Ingredient {name: 'carrot'})")))

    Post("/ingredient", HttpEntity(MediaTypes.`application/json`, """{"name": "carrot"}""")) ~> route ~> check {
      response shouldEqual HttpResponse(
        entity = HttpEntity("Executed query: CREATE (carrot:Ingredient {name: 'carrot'})"))
    }
    verify(repo).postIngredient(Ingredient("carrot"))
  }

  test("it should return 500 on neo4j failure") {
    when(repo.getAllIngredients).thenReturn(
      Future.failed(new Exception("test exception")))

    Get("/ingredient") ~> route ~> check {
      status.intValue shouldEqual 500
    }
  }
}

object IngredientRouteTest {
  def resultSummary(query: String): ResultSummary = new InternalResultSummary(
    new Statement(query),
    null,
    null,
    null,
    null,
    null,
    null,
    0,
    0)
}