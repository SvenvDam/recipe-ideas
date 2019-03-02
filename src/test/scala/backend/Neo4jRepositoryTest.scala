package backend

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import backend.model.{Ingredient, RawRecipe, Recipe}
import backend.query.{CleanDbQuery, InitializeExampleDbQuery}
import neotypes.implicits._
import org.neo4j.driver.v1.GraphDatabase
import org.neo4j.driver.v1.summary.ResultSummary
import org.neo4j.harness.TestServerBuilders
import org.scalatest.Matchers._
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, FunSuiteLike}

import scala.concurrent.duration._
import scala.concurrent.{Await, ExecutionContext, Future}

class Neo4jRepositoryTest
  extends FunSuiteLike
    with BeforeAndAfterAll
    with BeforeAndAfterEach {

  import Neo4jRepositoryTest._

  override def beforeEach = {
    val cleanResult = driver.writeSession { session =>
      session.transact(tx => CleanDbQuery.query.query[ResultSummary].execute(tx))
    }
    Await.ready(cleanResult, 10.seconds)
    val initializeResult = driver
      .writeSession { session =>
        session.transact(tx => InitializeExampleDbQuery.query.query[ResultSummary].execute(tx))
      }
    Await.ready(initializeResult, 10.seconds)

  }

  override def afterAll = driver.close()

  test("it should retrieve all ingredients") {
    awaitResults(repository.getAllIngredients) shouldBe InitializeExampleDbQuery.ingredients
  }

  test("it should insert an ingredient") {
    val newIng = Ingredient(name = "Carrot")
    awaitResults(repository.insertIngredient(newIng)) shouldBe a[ResultSummary]
    awaitResults(repository.getAllIngredients) shouldBe InitializeExampleDbQuery.ingredients + newIng
  }

  test("it should retrieve all recipes") {
    awaitResults(repository.getAllRecipes) shouldBe InitializeExampleDbQuery.recipes
  }

  test("it should insert a recipe") {
    val rawRecipe = RawRecipe("Tomato soup", "Short", "...", List("Tomato"))
    val result = awaitResults(repository.insertRecipe(rawRecipe))
    result shouldBe a[ResultSummary]
    awaitResults(repository.getAllRecipes) shouldBe InitializeExampleDbQuery.recipes + Recipe(rawRecipe)
  }
}

object Neo4jRepositoryTest {
  implicit val system: ActorSystem = ActorSystem("neo4JRepositoryTest")
  implicit val executor: ExecutionContext = system.dispatcher
  implicit val materializer: ActorMaterializer = ActorMaterializer()

  val testDbUri = TestServerBuilders.newInProcessBuilder().newServer().boltURI().toString
  val driver = GraphDatabase.driver(testDbUri).asScala[Future]

  val repository = new Neo4jRepository(driver)

  def awaitResults[T](f: Future[T]): T =
    Await.result(f, 10.seconds)
}
