package room.messages

import play.api.libs.json.{Json, OFormat}
import room.canvas.Position

case class BroadcastDrawData(username: String, position: Position)

/**
 * Server => Broadcast
 * 描画情報を送信する
 * @param data メッセージ本体
 */
case class BroadcastDrawMessage(override val data: BroadcastDrawData) extends Message[BroadcastDrawData, _]("BroadcastDraw", data)

object BroadcastDrawMessage {
  implicit val format: OFormat[BroadcastDrawMessage] = Json.format[BroadcastDrawMessage]
  val Type: String = "BroadcastDraw"
}
