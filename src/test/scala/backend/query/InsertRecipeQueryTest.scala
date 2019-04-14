package backend.query

import backend.model.{Ingredient, RawRecipe, Recipe}
import com.typesafe.scalalogging.LazyLogging
import org.scalatest.FunSuiteLike
import org.scalatest.Matchers._

class InsertRecipeQueryTest extends FunSuiteLike with LazyLogging {
  import InsertRecipeQueryTest._

  //TODO: implement
  test("it should construct a query from a recipe") {
    val recipeQuery = InsertRecipeQuery(testRecipe)
    logger.info(recipeQuery.query)

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
