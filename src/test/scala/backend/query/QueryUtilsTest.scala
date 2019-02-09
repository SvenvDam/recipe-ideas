package backend.query

import org.scalatest.FunSuiteLike
import org.scalatest.Matchers._

class QueryUtilsTest extends FunSuiteLike {
  object Util extends QueryUtils

  test("it should format node names correctly") {
    Util.formatName("Red wine") shouldEqual "red_wine"
  }
}
