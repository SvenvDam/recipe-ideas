CREATE (Garlic:Ingredient {name: 'Garlic'})
CREATE (Onion:Ingredient {name: 'Onion'})
CREATE (Beef:Ingredient {name: 'Beef'})
CREATE (Pasta:Ingredient {name: 'Pasta'})
CREATE (Tomato:Ingredient {name: 'Tomato'})
CREATE (Beef_stock:Ingredient {name: 'Beef stock'})
CREATE (Sherry:Ingredient {name: 'Sherry'})
CREATE (Butter:Ingredient {name: 'Butter'})
CREATE (Olive_oil:Ingredient {name: 'Olive oil'})
CREATE (Red_wine:Ingredient {name: 'Red wine'})
CREATE (White_wine:Ingredient {name: 'White wine'})
CREATE (Pulpo:Ingredient {name: 'Pulpo'})
CREATE (Smoked_paprika:Ingredient {name: 'Smoked paprika'})
CREATE (Bay_leaf:Ingredient {name: 'Bay leaf'})

CREATE (French_onion_soup:Recipe {name: 'French onion soup', duration: 'Long'})
CREATE (Spaghetti_with_meatballs:Recipe {name: 'Spaghetti with matballs', duration: 'Medium'})
CREATE (Braised_pulpo:Recipe {name: 'Braised pulpo', duration: 'Long'})

CREATE
  (French_onion_soup)-[:Contains]->(Onion),
  (French_onion_soup)-[:Contains]->(Beef_stock),
  (French_onion_soup)-[:Contains]->(Butter),
  (French_onion_soup)-[:Contains]->(Sherry),

  (Spaghetti_with_meatballs)-[:Contains]->(Garlic),
  (Spaghetti_with_meatballs)-[:Contains]->(Onion),
  (Spaghetti_with_meatballs)-[:Contains]->(Tomato),
  (Spaghetti_with_meatballs)-[:Contains]->(Beef),
  (Spaghetti_with_meatballs)-[:Contains]->(Pasta),
  (Spaghetti_with_meatballs)-[:Contains]->(Olive_oil),
  (Spaghetti_with_meatballs)-[:Contains]->(Red_wine),

  (Braised_pulpo)-[:Contains]->(Onion),
  (Braised_pulpo)-[:Contains]->(Garlic),
  (Braised_pulpo)-[:Contains]->(Pulpo),
  (Braised_pulpo)-[:Contains]->(Olive_oil),
  (Braised_pulpo)-[:Contains]->(Smoked_paprika),
  (Braised_pulpo)-[:Contains]->(White_wine),
  (Braised_pulpo)-[:Contains]->(Bay_leaf)


