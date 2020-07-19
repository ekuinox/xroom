package room.messages

import play.api.libs.json.{Json, OFormat}

case class RequestChatData(text: String)

/**
 * Client => Server
 */
case class RequestChatMessage(override val data: RequestChatData) extends Message[RequestChatData, _](RequestChatMessage.Type, data)

object RequestChatMessage {
  implicit val format: OFormat[RequestChatMessage] = Json.format[RequestChatMessage]
  val Type: String = "RequestChat"
}
