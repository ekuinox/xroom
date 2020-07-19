package room.messages

import play.api.libs.json.{Json, OFormat}

/**
 * サーバのレスポンス
 * とりあえずokか返すだけ
 */
case class ResponseData(ok: Boolean)

/**
 * Server => Client
 */
case class ResponseMessage(override val data: ResponseData) extends Message[ResponseData, _](ResponseMessage.Type, data)

object ResponseMessage {
  implicit val format: OFormat[ResponseMessage] = Json.format[ResponseMessage]
  val Type: String = "Response"

  def ok: ResponseMessage = ResponseMessage(ResponseData(true))

  def notOk: ResponseMessage = ResponseMessage(ResponseData(false))
}
