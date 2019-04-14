package backend

import backend.model.Implicits._
import backend.model.{Ingredient, RawRecipe, Recipe}
import backend.query._
import neotypes.Driver
import neotypes.implicits._
import org.neo4j.driver.v1.summary.ResultSummary

import scala.collection.immutable.SortedSet
import scala.concurrent.ExecutionContext.Implicits._
import scala.concurrent.Future

class Neo4jRepository(driver: Driver[Future]) {

  //TODO: make Seq[Ingredient]
  def getAllIngredients: Future[SortedSet[Ingredient]] = {
    driver.readSession { session =>
      session.transact { tx =>
        GetAllIngredientsQuery.query
          .query[Ingredient]
          .list(tx)
          .map(SortedSet[Ingredient])
      }
    }
  }

  def insertIngredient(ingredient: Ingredient): Future[ResultSummary] = {
    val insertIngredientQuery = InsertIngredientQuery(ingredient)
    driver.writeSession { session =>
      session.transact { tx =>
        insertIngredientQuery.query.query[ResultSummary].execute(tx)
      }
    }
  }

  //TODO: make Seq[Recipe]
  def getAllRecipes: Future[SortedSet[Recipe]] = {
    driver.readSession { session =>
      session.transact { tx =>
        GetAllRecipesQuery.query
          .query[RawRecipe]
          .list(tx)
          .map(_.map(Recipe.apply))
          .map(SortedSet[Recipe])
      }
    }
  }

  def insertRecipe(raw: RawRecipe): Future[ResultSummary] = {
    val insertRecipeQuery = InsertRecipeQuery(raw)
    driver.writeSession { session =>
      session.transact { tx =>
        insertRecipeQuery.query.query[ResultSummary].execute(tx)
      }
    }
  }
}