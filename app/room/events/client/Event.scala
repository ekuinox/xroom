package room.events.client

import play.api.libs.json.{JsValue, Json, OFormat}

abstract class Event()

object Event {
  implicit def json2object(value: JsValue): Event = {
    (value \ "eventType").asOpt[String] match {
      case Some(Talk.Type) => value.asOpt[Talk].getOrElse(Error(""))
      case Some(UpdateUsername.Type) => value.asOpt[UpdateUsername].getOrElse(Error(""))
      case _ => Error("")
    }
  }

  implicit def object2json(event: Event): JsValue = {
    event match {
      case event: Talk => Json.toJson(event)
      case event: Error => Json.toJson(event)
    }
  }
}

case class Error(message: String, eventType: String = Error.Type) extends Event

object Error {
  implicit val format: OFormat[Error] = Json.format[Error]
  val Type: String = "Error"
}
