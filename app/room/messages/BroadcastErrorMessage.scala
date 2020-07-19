package room.messages

import play.api.libs.json.{Json, OFormat}

case class BroadcastErrorData(message: String)

/**
 * Server => Broadcast
 * エラーを伝え合うメッセージ
 * @param data メッセージ本体
 */
case class BroadcastErrorMessage(override val data: BroadcastErrorData) extends Message[BroadcastErrorData, _]("BroadcastError", data)

object BroadcastErrorMessage {
  implicit val format: OFormat[BroadcastErrorMessage] = Json.format[BroadcastErrorMessage]
  val Type: String = "BroadcastError"
}