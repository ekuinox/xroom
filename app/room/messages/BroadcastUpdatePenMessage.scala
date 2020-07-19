package room.messages

import play.api.libs.json.{Json, OFormat}
import room.canvas.Pen

case class BroadcastUpdatePenData(username: String, pen: Pen)

case object BroadcastUpdatePenData {
  implicit val format: OFormat[BroadcastUpdatePenData] = Json.format[BroadcastUpdatePenData]
}

/**
 * Server => Broadcast
 * 新しいPenの状態を送る
 * @param data メッセージ本体
 */
case class BroadcastUpdatePenMessage(data: BroadcastUpdatePenData) extends Message[BroadcastUpdatePenData]("BroadcastUpdatePen", data)

object BroadcastUpdatePenMessage {
  implicit val format: OFormat[BroadcastUpdatePenMessage] = Json.format[BroadcastUpdatePenMessage]
  val Type: String = "BroadcastUpdatePen"
}
