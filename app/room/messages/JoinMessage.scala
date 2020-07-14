package room.messages

import play.api.libs.json.{Json, OFormat}

case class JoinData(username: String)

/**
 * 新しくルームに参加したユーザを周知させる
 * Server => Client
 * @param data
 */
case class JoinMessage(override val data: JoinData) extends Message[JoinData, _]("Join", data)

object JoinMessage {
  implicit val format: OFormat[JoinMessage] = Json.format[JoinMessage]
  val Type: String = "Join"
}
