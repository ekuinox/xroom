package room.events

import play.api.libs.json.{Json, OFormat}

case class Talk(username: String, text: String, eventType: String = Talk.Type) extends Event

object Talk {
  implicit val format: OFormat[Talk] = Json.format[Talk]
  val Type: String = "Talk"
}
