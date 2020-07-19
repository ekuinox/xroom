package room.messages

import play.api.libs.json.{Json, OFormat}
import room.canvas.Position

case class DrawData(username: String, position: Position)

/**
 * Server <=> Client
 * 描画情報を送信する
 * @param data メッセージ本体
 */
case class DrawMessage(override val data: DrawData) extends Message[DrawData, _]("Draw", data)

object DrawMessage {
  implicit val format: OFormat[DrawMessage] = Json.format[DrawMessage]
  val Type: String = "Draw"
}
