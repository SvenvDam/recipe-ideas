package backend.query

object CleanDbQuery extends CypherQuery {
  val query = "MATCH (n) DETACH DELETE n"
}
