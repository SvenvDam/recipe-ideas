package backend.query

import backend.model.Ingredient
import org.scalatest.{FunSuiteLike, Matchers}

class InsertIngredientQueryTest extends FunSuiteLike with Matchers {
  test("it should construct from Ingredient correctly") {
    val carrot = Ingredient("Carrot")
    val carrotQuery = InsertIngredientQuery(carrot)

    carrotQuery.query shouldEqual "CREATE (carrot:Ingredient {name: 'Carrot'})"
  }
}
