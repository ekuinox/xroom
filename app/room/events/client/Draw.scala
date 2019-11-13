package room.events.client

import play.api.libs.json.{Json, OFormat}
import room.canvas.Position

case class Draw(position: Position, eventType: String = Draw.Type) extends Event

object Draw {
  implicit val format: OFormat[Draw] = Json.format[Draw]
  val Type: String = "Draw"
}
