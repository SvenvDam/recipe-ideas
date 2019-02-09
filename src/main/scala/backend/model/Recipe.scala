package backend.model

case class Recipe(name: String, duration: String, directions: String, ingredients: Seq[Ingredient])
