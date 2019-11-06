package room.events

import play.api.libs.json.{JsValue, Json, OFormat}

abstract class Event()

object Event {
  implicit def json2object(value: JsValue): Event = {
    (value \ "eventType").asOpt[String] match {
      case Some(Join.Type) => value.asOpt[Join].getOrElse(Error("bad request"))
      case Some(Leave.Type) => value.asOpt[Leave].getOrElse(Error("bad request"))
      case Some(Talk.Type) => value.asOpt[Talk].getOrElse(Error("bad request"))
      case None => Error("bad request")
      case _ => Error("bad request")
    }
  }

  implicit def object2json(event: Event): JsValue = {
    event match {
      case event: Join => Json.toJson(event)
      case event: Leave => Json.toJson(event)
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
