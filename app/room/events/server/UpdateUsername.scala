package room.events.server

import play.api.libs.json.{Json, OFormat}

case class UpdateUsername(oldUsername: String, newUsername: String, eventType: String = UpdateUsername.Type) extends Event

object UpdateUsername {
  implicit val format: OFormat[UpdateUsername] = Json.format[UpdateUsername]
  val Type: String = "UpdateUsername"
}
