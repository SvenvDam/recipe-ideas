package backend

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import backend.model.Ingredient
import backend.query.InitializeExampleDb
import neotypes.implicits._
import org.neo4j.driver.v1.GraphDatabase
import org.neo4j.harness.TestServerBuilders
import org.scalatest.{BeforeAndAfterAll, FunSuiteLike, Matchers}

import scala.concurrent.duration._
import scala.concurrent.{Await, ExecutionContext, Future}

class Neo4jRepositoryTest extends FunSuiteLike with Matchers with BeforeAndAfterAll {
  import Neo4jRepositoryTest._

  override def afterAll(): Unit = driver.close()

  test("it should retrieve all ingredients from db") {
    Await.result(repository.getIngredients, 10.seconds) shouldEqual InitializeExampleDb.ingredientSeq
  }
}

object Neo4jRepositoryTest {
  implicit val system: ActorSystem = ActorSystem("neo4JRepositoryTest")
  implicit val executor: ExecutionContext = system.dispatcher
  implicit val materializer: ActorMaterializer = ActorMaterializer()

  val testDbUri = TestServerBuilders.newInProcessBuilder().newServer().boltURI().toString
  val driver = GraphDatabase.driver(testDbUri)
    .asScala[Future]
  val initializeResult = driver
    .writeSession { session =>
      InitializeExampleDb.query.query[Ingredient].list(session)
    }.collect {
    case s: Seq[Ingredient] =>
    case _ => throw new RuntimeException("Test DB could not be instantiated")
  }
  Await.ready(initializeResult, 10.seconds)

  val repository = new Neo4jRepository(driver)
}
