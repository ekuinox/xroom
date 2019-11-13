package room.events.client

import play.api.libs.json.{Json, OFormat}

case class UpdateUsername(username: String, eventType: String = UpdateUsername.Type) extends Event

object UpdateUsername {
  implicit val format: OFormat[UpdateUsername] = Json.format[UpdateUsername]
  val Type: String = "UpdateUsername"
}
