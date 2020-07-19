package room.messages

import play.api.libs.json.{Json, OFormat}

case class BroadcastChatData(username: String, text: String)

/**
 * Server => Broadcast
 * @param data メッセージ本体
 */
case class BroadcastChatMessage(override val data: BroadcastChatData) extends Message[BroadcastChatData, _]("BroadcastChat", data)

object BroadcastChatMessage {
  implicit val format: OFormat[BroadcastChatMessage] = Json.format[BroadcastChatMessage]
  val Type: String = "BroadcastChat"
}
