package backend

import backend.model.Ingredient
import backend.query.CypherQuery

import scala.concurrent.Future

class Neo4jRepository {
  def getIngredients: Future[Seq[Ingredient]] = ???

  def postIngredient(ingredient: Ingredient): Future[Ingredient] = ???

  def execute[T](query: CypherQuery): Future[T] = ???
}
