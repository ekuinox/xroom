package room.messages

import play.api.libs.json.{Json, OFormat}

case class ChatData(username: String, text: String)

/**
 * Server <=> Client
 * @param data メッセージ本体
 */
case class ChatMessage(override val data: ChatData) extends Message[ChatData, _]("Chat", data)

object ChatMessage {
  implicit val format: OFormat[ChatMessage] = Json.format[ChatMessage]
  val Type: String = "Chat"
}
