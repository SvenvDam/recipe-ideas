package backend.route

import akka.http.scaladsl.model.{HttpEntity, HttpResponse, MediaTypes}
import akka.http.scaladsl.testkit.ScalatestRouteTest
import backend.Neo4jRepository
import backend.model.{Ingredient, Recipe}
import com.typesafe.scalalogging.LazyLogging
import org.scalatest.mockito.MockitoSugar._
import org.scalatest.FunSuiteLike
import org.scalatest.Matchers._
import org.mockito.Mockito.{verify, when}
import org.neo4j.driver.internal.summary.InternalResultSummary
import org.neo4j.driver.v1.Statement
import org.neo4j.driver.v1.summary.ResultSummary

import scala.concurrent.Future

class RecipeRouteTest extends FunSuiteLike with ScalatestRouteTest with LazyLogging {
  import RecipeRouteTest._

  val repo = mock[Neo4jRepository]
  val route = RecipeRoute(repo).route

  test("it should parse a posted recipe correctly") {
    Post("/recipe", HttpEntity(MediaTypes.`application/json`, testRecipeJson)) ~> route
    verify(repo).postRecipe(testRecipe)
  }

  test("it should trigger recipe insertion in DB and notify requester") {
    when(repo.postRecipe(testRecipe)).thenReturn(Future.successful(resultSummary("test-query")))
    Post("/recipe", HttpEntity(MediaTypes.`application/json`, testRecipeJson)) ~> route ~> check {
      status.intValue shouldBe 200
      response shouldEqual HttpResponse(entity = HttpEntity("Executed query: test-query"))
    }
  }

  test("it should notify requester when insertion fails") {
    when(repo.postRecipe(testRecipe)).thenReturn(Future.failed(new Exception("test-exception")))
    Post("/recipe", HttpEntity(MediaTypes.`application/json`, testRecipeJson)) ~> route ~> check {
      status.intValue shouldBe 500
    }
  }

  test("it should retrieve all recipes and notify requester") {
    when(repo.getAllRecipes).thenReturn(Future.successful(Seq(testRecipe)))
    Get("/recipe") ~> route ~> check {
      status.intValue shouldBe 200
      response shouldBe HttpResponse(entity = HttpEntity(MediaTypes.`application/json`, "[" + testRecipeJson + "]"))
    }
  }

  test("it should notify requester when retrieval fails") {
    when(repo.getAllRecipes).thenReturn(Future.failed(new Exception("test-exception")))
    Get("/recipe") ~> route ~> check {
      status.intValue shouldBe 500
    }
  }
}

object RecipeRouteTest {
  val pasta = """{"name": "pasta"}"""
  val tomato = """{"name": "tomato"}"""
  val beef = """{"name": "beef"}"""
  val testRecipeJson =
    s"""
      |{
      |   "name": "lasagne",
      |   "duration": "long",
      |   "directions": "lorem_ipsum",
      |   "ingredients": [$pasta, $tomato, $beef]
      |}
    """.stripMargin.replace("\n", "").replace(" ", "")

  val testRecipe = Recipe(
    "lasagne",
    "long",
    "lorem_ipsum",
    Seq(Ingredient("pasta"), Ingredient("tomato"), Ingredient("beef"))
  )

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
