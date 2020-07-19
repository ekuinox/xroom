package room.messages

import play.api.libs.json.{Json, OFormat}

case class BroadcastLeaveData(username: String)

case object BroadcastLeaveData {
  implicit val format: OFormat[BroadcastLeaveData] = Json.format[BroadcastLeaveData]
}

/**
 * ルームから退出したユーザを周知させる
 * Server => Client
 * @param data
 */
case class BroadcastLeaveMessage(data: BroadcastLeaveData) extends Message[BroadcastLeaveData]("BroadcastLeave", data)

object BroadcastLeaveMessage {
  implicit val format: OFormat[BroadcastLeaveMessage] = Json.format[BroadcastLeaveMessage]
  val Type: String = "BroadcastLeave"
}
