package room.messages

import play.api.libs.json.{Json, OFormat}

case class BroadcastJoinData(username: String)

/**
 * 新しくルームに参加したユーザを周知させる
 * Server => Client
 * @param data
 */
case class BroadcastJoinMessage(override val data: BroadcastJoinData) extends Message[BroadcastJoinData, _]("BroadcastJoin", data)

object BroadcastJoinMessage {
  implicit val format: OFormat[BroadcastJoinMessage] = Json.format[BroadcastJoinMessage]
  val Type: String = "BroadcastJoin"
}
