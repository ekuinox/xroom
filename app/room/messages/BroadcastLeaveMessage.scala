package room.messages

import play.api.libs.json.{Json, OFormat}

case class BroadcastLeaveData(username: String)

/**
 * ルームから退出したユーザを周知させる
 * Server => Client
 * @param data
 */
case class BroadcastLeaveMessage(override val data: BroadcastLeaveData) extends Message[BroadcastLeaveData, _]("BroadcastLeave", data)

object BroadcastLeaveMessage {
  implicit val format: OFormat[BroadcastLeaveMessage] = Json.format[BroadcastLeaveMessage]
  val Type: String = "BroadcastLeave"
}
