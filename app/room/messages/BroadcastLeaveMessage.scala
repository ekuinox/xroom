package room.messages

import play.api.libs.json.{Json, OFormat}

case class LeaveData(username: String)

/**
 * ルームから退出したユーザを周知させる
 * Server => Client
 * @param data
 */
case class LeaveMessage(override val data: LeaveData) extends Message[LeaveData, _]("Leave", data)

object LeaveMessage {
  implicit val format: OFormat[LeaveMessage] = Json.format[LeaveMessage]
  val Type: String = "Leave"
}
