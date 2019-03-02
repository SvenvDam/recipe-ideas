package backend.query

import backend.model.{Ingredient, Recipe}

case class InsertRelationQuery(query: String) extends CypherQuery

object InsertRelationQuery extends QueryUtils {
  def apply(ingredient: Ingredient, recipe: Recipe) = {
    val query = s"CREATE (${formatName(recipe.name)})-[:Contins]->(${formatName(ingredient.name)})"
    new InsertRelationQuery(query)
  }
}
