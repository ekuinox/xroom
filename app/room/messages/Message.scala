package room.messages

import play.api.libs.json.{JsValue, Json}

/**
 * Server <=> Client のメッセージ型
 * @param `type` Nameの実態
 * @param data メッセージ本体
 * @tparam T メッセージ本体の型
 * @tparam Name 識別するための型
 */
case class Message[T, Name <: String](`type`: Name, data :T)

object Message {
  implicit def json2object(value: JsValue): Message[_, _] = {
    (value \ "type").asOpt[String] match {
      case Some(JoinMessage.format) => value.asOpt[JoinMessage].getOrElse(ErrorMessage(ErrorData("UnknownError")))
      case _ => ErrorMessage(ErrorData("UnknownError"))
    }
  }

  implicit def object2json(event: Message[_, _]): JsValue = {
    event match {
      case event: JoinMessage => Json.toJson(event)
      case event: ErrorMessage => Json.toJson(event)
    }
  }
}
