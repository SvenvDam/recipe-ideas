package backend.routes

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.Directives._
import spray.json._

trait JsonRoute extends SprayJsonSupport with DefaultJsonProtocol {

  import JsonRoute._

  implicit val messageFormat = jsonFormat1(Message)

  val jsonRoute = path("json") {
    post {
      entity(as[Message]) { msg =>
        val msgContent = msg.msg
        complete(s"Received message: $msgContent")
      }
    }
  }
}

object JsonRoute {

  case class Message(msg: String)

}
