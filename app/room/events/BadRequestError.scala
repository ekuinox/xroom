package room.events

import play.api.libs.json.{Json, OFormat}

object BadRequestError extends Error("Bad Request Error", "BadRequestError") {
  implicit val format: OFormat[Error] = Json.format[Error]
  val Type: String = "BadRequestError"
}
