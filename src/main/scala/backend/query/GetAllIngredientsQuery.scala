package backend.query

object GetAllIngredientsQuery extends CypherQuery {
  val query = "MATCH (i:Ingredient) RETURN i"
}
