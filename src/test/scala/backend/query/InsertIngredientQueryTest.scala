package backend.query

import backend.model.Ingredient
import org.scalatest.FunSuiteLike
import org.scalatest.Matchers._

class InsertIngredientQueryTest extends FunSuiteLike {
  test("it should construct from Ingredient correctly") {
    val carrot = Ingredient("Carrot")
    val carrotQuery = InsertIngredientQuery(carrot)

    carrotQuery.query shouldEqual "CREATE (carrot:Ingredient {name: 'Carrot'})"
  }
}
