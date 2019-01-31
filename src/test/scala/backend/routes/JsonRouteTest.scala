package backend.routes

import akka.http.scaladsl.model.{HttpEntity, MediaTypes}
import akka.http.scaladsl.testkit.ScalatestRouteTest
import akka.util.ByteString
import com.typesafe.scalalogging.LazyLogging
import org.scalatest.{FunSuiteLike, Matchers}

class JsonRouteTest
  extends FunSuiteLike
    with ScalatestRouteTest
    with Matchers
    with LazyLogging {

  import JsonRouteTest._

  test("it should handle a post request with json body correctly") {

    val jsonBody = ByteString(
      """
        |{
        |   "msg":"test"
        |}
      """.stripMargin)

    val entity = HttpEntity(MediaTypes.`application/json`, jsonBody)

    Post("/json", entity) ~> jsonRoute ~> check {
      responseAs[String] shouldEqual "Received message: test"
    }
  }
}

object JsonRouteTest extends JsonRoute
