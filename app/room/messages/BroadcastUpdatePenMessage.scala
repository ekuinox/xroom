package room.messages

import play.api.libs.json.{Json, OFormat}
import room.canvas.Pen

case class BroadcastUpdatePenData(username: String, pen: Pen)

/**
 * Server => Broadcast
 * 新しいPenの状態を送る
 * @param data メッセージ本体
 */
case class BroadcastUpdatePenMessage(override val data: BroadcastUpdatePenData) extends Message[BroadcastUpdatePenData, _]("BroadcastUpdatePen", data)

object BroadcastUpdatePenMessage {
  implicit val format: OFormat[BroadcastUpdatePenMessage] = Json.format[BroadcastUpdatePenMessage]
  val Type: String = "BroadcastUpdatePen"
}
