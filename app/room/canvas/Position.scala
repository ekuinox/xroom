package room.canvas

import play.api.libs.json.{Json, OFormat}

case class Position(x: Float = 0.0f, y: Float = 0.0f)

object Position {
  implicit val format: OFormat[Position] = Json.format[Position]
}
