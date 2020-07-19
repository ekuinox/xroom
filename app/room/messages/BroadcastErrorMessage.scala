package room.messages

import play.api.libs.json.{Json, OFormat}

case class BroadcastErrorData(message: String)

case object BroadcastErrorData {
  implicit val format: OFormat[BroadcastErrorData] = Json.format[BroadcastErrorData]
}

/**
 * Server => Broadcast
 * エラーを伝え合うメッセージ
 * @param data メッセージ本体
 */
case class BroadcastErrorMessage(data: BroadcastErrorData) extends Message[BroadcastErrorData]("BroadcastError", data)

object BroadcastErrorMessage {
  implicit val format: OFormat[BroadcastErrorMessage] = Json.format[BroadcastErrorMessage]
  val Type: String = "BroadcastError"
}