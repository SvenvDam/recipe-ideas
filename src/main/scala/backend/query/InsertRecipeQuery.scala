package backend.query

import backend.model.{RawRecipe, Recipe}

case class InsertRecipeQuery(query: String) extends CypherQuery

object InsertRecipeQuery extends QueryUtils {
  def apply(raw: RawRecipe) = {
    val recipe = Recipe(raw)
    val ingredientListString = "[" + raw.ingredients.reduce((i1, i2) => i1 + ", " + i2) + "]"
    val recipeQuery =
      s"""CREATE (${formatName(raw.name)}:Recipe {
         |name: "${raw.name}",
         |duration: "${raw.duration}",
         |directions: "${raw.directions}",
         |ingredients: "${ingredientListString}"})""".stripMargin

    val ingredientQueries =
      recipe.ingredients
        .map(InsertIngredientQuery.apply)
        .map(q => q.query)
    val relationQueries = recipe.ingredients
      .map(ing => InsertRelationQuery(ing, recipe))
      .map(q => q.query)
    val query = Set(recipeQuery) ++ ingredientQueries ++ relationQueries reduce {
      (q1, q2) => q1 + " " + q2
    }

    new InsertRecipeQuery(query)
  }
}