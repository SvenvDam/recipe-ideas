package backend.query

import backend.model.{Ingredient, Recipe}
import backend.model.Implicits._

import scala.collection.immutable.SortedSet

object InitializeExampleDbQuery extends CypherQuery {
  val query =
    """
      |CREATE (garlic:Ingredient {name: 'Garlic'})
      |CREATE (onion:Ingredient {name: 'Onion'})
      |CREATE (beef:Ingredient {name: 'Beef'})
      |CREATE (pasta:Ingredient {name: 'Pasta'})
      |CREATE (tomato:Ingredient {name: 'Tomato'})
      |CREATE (beef_stock:Ingredient {name: 'Beef stock'})
      |CREATE (sherry:Ingredient {name: 'Sherry'})
      |CREATE (butter:Ingredient {name: 'Butter'})
      |CREATE (olive_oil:Ingredient {name: 'Olive oil'})
      |CREATE (red_wine:Ingredient {name: 'Red wine'})
      |CREATE (white_wine:Ingredient {name: 'White wine'})
      |CREATE (pulpo:Ingredient {name: 'Pulpo'})
      |CREATE (smoked_paprika:Ingredient {name: 'Smoked paprika'})
      |CREATE (bay_leaf:Ingredient {name: 'Bay leaf'})
      |
      |CREATE (french_onion_soup:Recipe {name: 'French onion soup', duration: 'Long', directions: '...', ingredients: ['Onion', 'Beef stock', 'Butter', 'Sherry']})
      |CREATE (spaghetti_with_meatballs:Recipe {name: 'Spaghetti with meatballs', duration: 'Medium', directions: '...', ingredients: ['Garlic', 'Onion', 'Tomato', 'Beef', 'Pasta', 'Olive oil', 'Red wine']})
      |CREATE (braised_pulpo:Recipe {name: 'Braised pulpo', duration: 'Long', directions: '...', ingredients: ['Onion', 'Garlic', 'Pulpo', 'Olive oil', 'Smoked paprika', 'White wine', 'Bay leaf']})
      |
      |CREATE
      |  (french_onion_soup)-[:Contains]->(onion),
      |  (french_onion_soup)-[:Contains]->(beef_stock),
      |  (french_onion_soup)-[:Contains]->(butter),
      |  (french_onion_soup)-[:Contains]->(sherry),
      |
      |  (spaghetti_with_meatballs)-[:Contains]->(garlic),
      |  (spaghetti_with_meatballs)-[:Contains]->(onion),
      |  (spaghetti_with_meatballs)-[:Contains]->(tomato),
      |  (spaghetti_with_meatballs)-[:Contains]->(beef),
      |  (Spaghetti_with_meatballs)-[:Contains]->(pasta),
      |  (spaghetti_with_meatballs)-[:Contains]->(olive_oil),
      |  (spaghetti_with_meatballs)-[:Contains]->(red_wine),
      |
      |  (braised_pulpo)-[:Contains]->(onion),
      |  (braised_pulpo)-[:Contains]->(garlic),
      |  (braised_pulpo)-[:Contains]->(pulpo),
      |  (braised_pulpo)-[:Contains]->(olive_oil),
      |  (braised_pulpo)-[:Contains]->(smoked_paprika),
      |  (braised_pulpo)-[:Contains]->(white_wine),
      |  (braised_pulpo)-[:Contains]->(bay_leaf)
    """.stripMargin

  val ingredients = SortedSet(
    Ingredient("Garlic"),
    Ingredient("Onion"),
    Ingredient("Beef"),
    Ingredient("Pasta"),
    Ingredient("Tomato"),
    Ingredient("Beef stock"),
    Ingredient("Sherry"),
    Ingredient("Butter"),
    Ingredient("Olive oil"),
    Ingredient("Red wine"),
    Ingredient("White wine"),
    Ingredient("Pulpo"),
    Ingredient("Smoked paprika"),
    Ingredient("Bay leaf"))

  val recipes = SortedSet(
    Recipe("French onion soup", "Long", "...",
      Set(Ingredient("Onion"), Ingredient("Beef stock"), Ingredient("Butter"), Ingredient("Sherry"))),
    Recipe("Spaghetti with meatballs", "Medium", "...",
      Set(Ingredient("Onion"), Ingredient("Garlic"), Ingredient("Tomato"), Ingredient("Beef"), Ingredient("Olive oil"), Ingredient("Red wine"))),
    Recipe("Braised pulpo", "Long", "...",
      Set(Ingredient("Onion"), Ingredient("Garlic"), Ingredient("Pulpo"), Ingredient("Smoked paprika"), Ingredient("White wine"), Ingredient("Bay leaf")))
  )
}
