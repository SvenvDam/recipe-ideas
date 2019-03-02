package backend.model

case class Recipe(
    name: String,
    duration: String,
    directions: String,
    ingredients: Set[Ingredient])

object Recipe {
  def apply(raw: RawRecipe) =
    new Recipe(
      raw.name,
      raw.duration,
      raw.directions,
      raw.ingredients.map(s => Ingredient(s)).toSet)
}
