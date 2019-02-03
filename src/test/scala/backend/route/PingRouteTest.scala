package backend.route

import akka.http.scaladsl.testkit.ScalatestRouteTest
import backend.Neo4jRepository
import org.scalatest.mockito.MockitoSugar
import org.scalatest.{FunSuiteLike, Matchers}

class PingRouteTest
  extends FunSuiteLike
    with ScalatestRouteTest
    with Matchers
    with MockitoSugar {
  test("it should return 'pong' on ping endpoint") {
    val repo = mock[Neo4jRepository]
    val route = PingRoute(repo).route
    Get("/ping") ~> route ~> check {
      responseAs[String] shouldEqual "pong"
      status.intValue shouldEqual 200
    }
  }
}