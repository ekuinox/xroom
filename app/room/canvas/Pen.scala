package room.canvas

import play.api.libs.json.{Json, OFormat}

case class Pen(color: String = "#000000", active: Boolean = false)

object Pen {
  implicit val format: OFormat[Pen] = Json.format[Pen]
}
