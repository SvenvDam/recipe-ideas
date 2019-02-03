package backend

import backend.model.Ingredient
import backend.query.{GetIngredientsQuery, InsertIngredientQuery}
import neotypes.Driver
import neotypes.implicits._
import org.neo4j.driver.v1.summary.ResultSummary

import scala.concurrent.Future

class Neo4jRepository(driver: Driver[Future]) {
  def getAllIngredients: Future[Seq[Ingredient]] = {
    driver.readSession { session =>
      GetIngredientsQuery.query.query[Ingredient].list(session)
    }
  }

  def postIngredient(ingredient: Ingredient): Future[ResultSummary] = {
    val insertIngredientQuery = InsertIngredientQuery(ingredient)
    driver.writeSession { session =>
      insertIngredientQuery.query.query[ResultSummary].execute(session)
    }
  }
}
