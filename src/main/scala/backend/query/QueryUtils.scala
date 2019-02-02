package backend.query

trait QueryUtils {
  def formatName(raw: String): String = raw.toLowerCase.replaceAll(" ", "_")
}
