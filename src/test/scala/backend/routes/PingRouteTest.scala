package backend.routes

import akka.http.scaladsl.testkit.ScalatestRouteTest
import backend.Neo4jRepository
import com.typesafe.scalalogging.LazyLogging
import org.scalatest.mockito.MockitoSugar
import org.scalatest.{FunSuiteLike, Matchers}

class PingRouteTest
  extends FunSuiteLike
    with ScalatestRouteTest
    with Matchers
    with MockitoSugar
    with LazyLogging {

  val repo = mock[Neo4jRepository]
  val route = PingRoute(repo).route

  test("it should return 'pong' on ping endpoint") {

    Get("/ping") ~> route ~> check {
      responseAs[String] shouldEqual "pong"
      status.intValue shouldEqual 200
    }
  }
}