package room.events.client

import play.api.libs.json.{Json, OFormat}
import room.canvas.Pen

case class UpdatePen(pen: Pen, eventType: String = UpdatePen.Type) extends Event

object UpdatePen {
  implicit val format: OFormat[UpdatePen] = Json.format[UpdatePen]
  val Type: String = "UpdatePen"
}
