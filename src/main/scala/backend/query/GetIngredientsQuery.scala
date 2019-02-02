package backend.query

object GetIngredientsQuery extends CypherQuery {
  val query = "MATCH (i:Ingredient) RETURN i"
}
