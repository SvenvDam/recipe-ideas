package backend.model

import neotypes.implicits.{AbstractResultMapper, AbstractValueMapper}

object Implicits {
  implicit val ingredientOrdering = new Ordering[Ingredient] {
    def compare(l: Ingredient, r: Ingredient) = l.name compare r.name
  }

  implicit val recipeOrdering = new Ordering[Recipe] {
    def compare(l: Recipe, r: Recipe) = l.name compare r.name
  }

  implicit val rawRecipeOrdering = new Ordering[RawRecipe] {
    def compare(l: RawRecipe, r: RawRecipe) = l.name compare r.name
  }

  implicit object RawRecipeValueMapper extends AbstractValueMapper[RawRecipe](f => {
    val ingredients = f
      .asNode
      .get("ingredients")
      .toString

    RawRecipe(
      cleanAccolades(f.asNode.get("name").toString),
      cleanAccolades(f.asNode.get("duration").toString),
      cleanAccolades(f.asNode.get("directions").toString),
      parseListString(ingredients).toList)
  })

  //TODO: find neater way to do this
  def parseListString(raw: String): Seq[String] = {
    raw
      .replace("[", "")
      .replace("]", "")
      .split(",")
      .map(cleanAccolades)
  }

  def cleanAccolades(raw: String): String = {
    raw
      .replace("\"", "")
      .replace("\'", "")
  }

  implicit object RawRecipeResultMapper extends AbstractResultMapper[RawRecipe]
}
