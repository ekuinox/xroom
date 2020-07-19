package room.messages

import play.api.libs.json.{Json, OFormat}

/**
 * サーバのレスポンス
 * とりあえずokか返すだけ
 */
case class ResponseData(ok: Boolean)

case object ResponseData {
  implicit val format: OFormat[ResponseData] = Json.format[ResponseData]
}

/**
 * Server => Client
 */
case class ResponseMessage(data: ResponseData) extends Message[ResponseData](ResponseMessage.Type, data)

object ResponseMessage {
  implicit val format: OFormat[ResponseMessage] = Json.format[ResponseMessage]
  val Type: String = "Response"

  def ok: ResponseMessage = ResponseMessage(ResponseData(true))

  def notOk: ResponseMessage = ResponseMessage(ResponseData(false))
}
