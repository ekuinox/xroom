package room.messages

import play.api.libs.json.{Json, OFormat}

case class BroadcastChatData(username: String, text: String)

case object BroadcastChatData {
  implicit val format: OFormat[BroadcastChatData] = Json.format[BroadcastChatData]
}

/**
 * Server => Broadcast
 * @param data メッセージ本体
 */
case class BroadcastChatMessage(data: BroadcastChatData) extends Message[BroadcastChatData]("BroadcastChat", data)

object BroadcastChatMessage {
  implicit val format: OFormat[BroadcastChatMessage] = Json.format[BroadcastChatMessage]
  val Type: String = "BroadcastChat"
}
