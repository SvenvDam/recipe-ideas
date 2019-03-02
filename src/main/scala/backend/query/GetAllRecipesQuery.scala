package backend.query

object GetAllRecipesQuery extends CypherQuery {
  val query =
    """
      |MATCH (r:Recipe)
      |RETURN r as recipe
    """.stripMargin
}
