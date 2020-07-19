package room.messages

import play.api.libs.json.{Json, OFormat}
import room.canvas.Position

case class RequestDrawData(position: Position)

case object RequestDrawData {
  implicit val format: OFormat[RequestDrawData] = Json.format[RequestDrawData]
}

/**
 * Client => Server
 * 描画情報を送信する
 * @param data メッセージ本体
 */
case class RequestDrawMessage(data: RequestDrawData) extends Message[RequestDrawData](RequestChatMessage.Type, data)

object RequestDrawMessage {
  implicit val format: OFormat[RequestDrawMessage] = Json.format[RequestDrawMessage]
  val Type: String = "RequestDraw"
}
