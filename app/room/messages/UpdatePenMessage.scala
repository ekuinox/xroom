package room.messages

import play.api.libs.json.{Json, OFormat}
import room.canvas.Pen

case class UpdatePenData(username: String, pen: Pen)

/**
 * Server <=> Client
 * 新しいPenの状態を送る
 * @param data メッセージ本体
 */
case class UpdatePenMessage(override val data: UpdatePenData) extends Message[UpdatePenData, _]("UpdatePen", data)

object UpdatePenMessage {
  implicit val format: OFormat[UpdatePenMessage] = Json.format[UpdatePenMessage]
  val Type: String = "UpdatePen"
}
