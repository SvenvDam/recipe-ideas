package backend.route

import akka.http.scaladsl.testkit.ScalatestRouteTest
import backend.Neo4jRepository
import org.scalatest.FunSuiteLike
import org.scalatest.Matchers._
import org.scalatest.mockito.MockitoSugar._

class PingRouteTest extends FunSuiteLike with ScalatestRouteTest {
  test("it should return 'pong' on ping endpoint") {
    val repo = mock[Neo4jRepository]
    val route = PingRoute(repo).route
    Get("/ping") ~> route ~> check {
      responseAs[String] shouldEqual "pong"
      status.intValue shouldEqual 200
    }
  }
}