package room.canvas

import play.api.libs.json.{Json, OFormat}

case class Palette(pen: Pen = Pen(), position: Position = Position())

object Palette {
  implicit val format: OFormat[Palette] = Json.format[Palette]
}
