package room.messages

import play.api.libs.json.{Json, OFormat}

case class ErrorData(message: String)

/**
 * Server <=> Client
 * エラーを伝え合うメッセージ
 * @param data メッセージ本体
 */
case class ErrorMessage(override val data: ErrorData) extends Message[ErrorData, _]("Error", data)

object ErrorMessage {
  implicit val format: OFormat[ErrorMessage] = Json.format[ErrorMessage]
  val Type: String = "Error"
}