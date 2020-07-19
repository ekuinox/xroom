package room.messages

import play.api.libs.json.{Json, OFormat}
import room.canvas.Position

case class RequestDrawData(position: Position)

/**
 * Client => Server
 * 描画情報を送信する
 * @param data メッセージ本体
 */
case class RequestDrawMessage(override val data: RequestDrawData) extends Message[RequestDrawData, _](RequestChatMessage.Type, data)

object RequestDrawMessage {
  implicit val format: OFormat[RequestDrawMessage] = Json.format[RequestDrawMessage]
  val Type: String = "RequestDraw"
}
