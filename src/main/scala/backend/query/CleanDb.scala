package backend.query

object CleanDb extends CypherQuery {
  val query = "MATCH (n) DETACH DELETE n"
}
