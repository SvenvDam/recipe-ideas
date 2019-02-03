package backend

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import backend.model.Ingredient
import backend.query.{CleanDb, InitializeExampleDb}
import neotypes.implicits._
import org.neo4j.driver.v1.GraphDatabase
import org.neo4j.driver.v1.summary.ResultSummary
import org.neo4j.harness.TestServerBuilders
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, FunSuiteLike, Matchers}

import scala.concurrent.duration._
import scala.concurrent.{Await, ExecutionContext, Future}

class Neo4jRepositoryTest
  extends FunSuiteLike
    with Matchers
    with BeforeAndAfterAll
    with BeforeAndAfterEach {
  import Neo4jRepositoryTest._

  override def beforeEach = {
    val cleanResult = driver.writeSession { session =>
      CleanDb.query.query[Ingredient].list(session)
    }
    Await.ready(cleanResult, 10.seconds)
    val initializeResult = driver
      .writeSession { session =>
        InitializeExampleDb.query.query[String].list(session)
      }
    Await.ready(initializeResult, 10.seconds)
  }

  override def afterAll = driver.close()

  test("it should retrieve all ingredients") {
    awaitResults(repository.getAllIngredients) shouldEqual InitializeExampleDb.ingredientSeq
  }

  test("it should insert an ingredient") {
    val newIng = Ingredient(name = "carrot")
    awaitResults(repository.postIngredient(newIng)) shouldBe a [ResultSummary]
    awaitResults(repository.getAllIngredients) shouldEqual InitializeExampleDb.ingredientSeq :+ newIng
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
