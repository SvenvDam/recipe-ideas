package backend.query

import backend.model.{Ingredient, RawRecipe, Recipe}
import org.scalatest.FunSuiteLike
import org.scalatest.Matchers._

class InsertRecipeQueryTest extends FunSuiteLike {
  import InsertRecipeQueryTest._

  test("it should construct a query form a recipe") {
    val recipeQuery = InsertRecipeQuery(testRecipe)
    println(recipeQuery.query)
  }
}

object InsertRecipeQueryTest {
  val testRecipe = RawRecipe(
    "lasagne",
    "long",
    "lorem_ipsum",
    List("pasta", "tomato", "beef")
  )
}
