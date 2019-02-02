package backend.query

import backend.model.Ingredient

case class InsertIngredientQuery(query: String) extends CypherQuery

object InsertIngredientQuery extends QueryUtils {
  def apply(ingredient: Ingredient) ={
    val name = ingredient.name
    val query = s"CREATE (${formatName(name)}:Ingredient {name: '$name'})"
    new InsertIngredientQuery(query)
  }
}
