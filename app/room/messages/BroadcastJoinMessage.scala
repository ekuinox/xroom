package room.messages

import play.api.libs.json.{Json, OFormat}

case class BroadcastJoinData(username: String)

case object BroadcastJoinData {
  implicit val format: OFormat[BroadcastJoinData] = Json.format[BroadcastJoinData]
}

/**
 * 新しくルームに参加したユーザを周知させる
 * Server => Client
 * @param data
 */
case class BroadcastJoinMessage(data: BroadcastJoinData) extends Message[BroadcastJoinData]("BroadcastJoin", data)

object BroadcastJoinMessage {
  implicit val format: OFormat[BroadcastJoinMessage] = Json.format[BroadcastJoinMessage]
  val Type: String = "BroadcastJoin"
}
