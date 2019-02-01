package backend.model

case class Recipe(name: String, duration: Duration, directions: String, ingredients: Seq[Ingredient])
