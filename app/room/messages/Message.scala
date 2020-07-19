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
    (value \ "type").asOpt[String].flatMap {
      case BroadcastJoinMessage.format => value.asOpt[BroadcastJoinMessage]
      case BroadcastLeaveMessage.format => value.asOpt[BroadcastLeaveMessage]
      case BroadcastChatMessage.format => value.asOpt[BroadcastChatMessage]
      case BroadcastDrawMessage.format => value.asOpt[BroadcastDrawMessage]
      case BroadcastUpdatePenMessage.format => value.asOpt[BroadcastUpdatePenMessage]
      case _ => Some(ResponseMessage.notOk)
    }.getOrElse(ResponseMessage.notOk)
  }

  implicit def object2json(event: Message[_, _]): JsValue = {
    event match {
      case event: BroadcastJoinMessage => Json.toJson(event)
      case event: BroadcastLeaveMessage => Json.toJson(event)
      case event: BroadcastChatMessage => Json.toJson(event)
      case event: BroadcastDrawMessage => Json.toJson(event)
      case event: BroadcastUpdatePenMessage => Json.toJson(event)
      case event: BroadcastErrorMessage => Json.toJson(event)
    }
  }
}

/**
 * Server => Client
 * なんでかわからないエラー
 */
case object BroadcastUnknownErrorMessage extends BroadcastErrorMessage(BroadcastErrorData("BroadcastUnknownError"))