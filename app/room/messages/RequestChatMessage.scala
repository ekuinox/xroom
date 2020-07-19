package room.messages

import play.api.libs.json.{Json, OFormat}

case class RequestChatData(text: String)

case object RequestChatData {
  implicit val format: OFormat[RequestChatData] = Json.format[RequestChatData]
}

/**
 * Client => Server
 */
case class RequestChatMessage(data: RequestChatData) extends Message[RequestChatData](RequestChatMessage.Type, data)

object RequestChatMessage {
  implicit val format: OFormat[RequestChatMessage] = Json.format[RequestChatMessage]
  val Type: String = "RequestChat"
}
