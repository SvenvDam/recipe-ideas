package backend

import akka.http.scaladsl.testkit.ScalatestRouteTest
import com.typesafe.scalalogging.LazyLogging
import org.scalatest.{FunSuiteLike, Matchers}

class PingRouteTest
  extends FunSuiteLike
    with ScalatestRouteTest
    with Matchers
    with LazyLogging {

  import PingRouteTest._

  test("it should return 'pong' on ping endpoint") {
    Get("/ping") ~> pingRoute ~> check {
      responseAs[String] shouldEqual "pong"
      response.status.value shouldEqual "200 OK"
    }
  }
}

object PingRouteTest extends PingRoute
