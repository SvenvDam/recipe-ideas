package backend

import backend.model.Ingredient
import backend.query.GetIngredientsQuery
import neotypes.Driver
import neotypes.implicits._

import scala.concurrent.Future


class Neo4jRepository(driver: Driver[Future]) {
  def getIngredients: Future[Seq[Ingredient]] = {
    driver.readSession {session =>
      GetIngredientsQuery.query.query[Ingredient].list(session)
    }
  }

  def postIngredient(ingredient: Ingredient): Future[Ingredient] = ???
}
