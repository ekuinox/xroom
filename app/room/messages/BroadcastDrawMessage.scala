package room.messages

import play.api.libs.json.{Json, OFormat}
import room.canvas.Position

case class BroadcastDrawData(username: String, position: Position)

case object BroadcastDrawData {
  implicit val format: OFormat[BroadcastDrawData] = Json.format[BroadcastDrawData]
}

/**
 * Server => Broadcast
 * 描画情報を送信する
 * @param data メッセージ本体
 */
case class BroadcastDrawMessage(data: BroadcastDrawData) extends Message[BroadcastDrawData]("BroadcastDraw", data)

object BroadcastDrawMessage {
  implicit val format: OFormat[BroadcastDrawMessage] = Json.format[BroadcastDrawMessage]
  val Type: String = "BroadcastDraw"
}
