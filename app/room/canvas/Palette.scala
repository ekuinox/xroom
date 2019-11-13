package room.canvas

import play.api.libs.json.{Json, OFormat}

case class Palette(color: String = "#000000", position: Position = Position())

object Palette {
  implicit val format: OFormat[Palette] = Json.format[Palette]
}
