package room.events.client

import play.api.libs.json.{Json, OFormat}
import room.events.client.Event

case class Talk(text: String, eventType: String = Talk.Type) extends Event

object Talk {
  implicit val format: OFormat[Talk] = Json.format[Talk]
  val Type: String = "Talk"
}
