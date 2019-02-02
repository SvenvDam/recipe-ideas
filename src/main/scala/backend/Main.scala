package backend

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import backend.config.ApplicationConfig._
import org.neo4j.driver.v1.GraphDatabase
import neotypes.implicits._

import scala.concurrent.{ExecutionContext, Future}

object Main {
  implicit val system: ActorSystem = ActorSystem("recipe-ideas")
  implicit val executor: ExecutionContext = system.dispatcher
  implicit val materializer: ActorMaterializer = ActorMaterializer()

  val driver = GraphDatabase.driver(dbUri).asScala[Future]
  val neo4jRepository = new Neo4jRepository(driver)
  val routes = new Router(neo4jRepository).routes


  def main(args: Array[String]): Unit =
    Http().bindAndHandle(routes, host, port)
}

