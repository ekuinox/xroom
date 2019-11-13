package room.canvas

import play.api.libs.json.{Json, OFormat}

case class Pen(color: String = "#000000")

object Pen {
  implicit val format: OFormat[Pen] = Json.format[Pen]
}
