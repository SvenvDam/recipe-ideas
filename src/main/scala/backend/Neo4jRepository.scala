package backend

import backend.model.Ingredient

import scala.concurrent.Future

class Neo4jRepository {
  def getIngredients: Future[Seq[Ingredient]] = ???

  def postIngredient(ingredient: Ingredient): Future[Ingredient] = ???
}
