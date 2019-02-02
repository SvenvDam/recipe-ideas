package backend.query

import org.scalatest.{FunSuiteLike, Matchers}

class QueryUtilsTest extends FunSuiteLike with Matchers {
  object Util extends QueryUtils

  test("it should format node names correctly") {
    Util.formatName("Red wine") shouldEqual "red_wine"
  }
}
