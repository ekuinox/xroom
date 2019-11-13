package room.events.server

import play.api.libs.json.{Json, OFormat}

case class Join(username: String, eventType: String = Join.Type) extends Event

object Join {
  implicit val format: OFormat[Join] = Json.format[Join]
  val Type: String = "Join"
}
