package room.events.server

import play.api.libs.json.{Json, OFormat}
import room.canvas.Pen

case class UpdatePen(username: String, pen: Pen, eventType: String = UpdatePen.Type) extends Event

object UpdatePen {
  implicit val format: OFormat[UpdatePen] = Json.format[UpdatePen]
  val Type: String = "UpdatePen"
}
