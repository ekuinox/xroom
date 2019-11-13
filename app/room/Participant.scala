package room

import play.api.libs.json.{Json, OFormat}
import room.canvas.Palette

case class Participant(username: String, palette: Palette = Palette())

object Participant {
  implicit val format: OFormat[Participant] = Json.format[Participant]
}
