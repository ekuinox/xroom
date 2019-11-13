package room.events.server

import play.api.libs.json.{Json, OFormat}

case class Leave(username: String, eventType: String = Leave.Type) extends Event

object Leave {
  implicit val format: OFormat[Leave] = Json.format[Leave]
  val Type: String = "Leave"
}
