package room.messages

import play.api.libs.json.{JsValue, Json, OFormat}

/**
 * Server <=> Client のメッセージ型
 * @param `type` Nameの実態
 * @param data メッセージ本体
 * @tparam T メッセージ本体の型
 * @tparam Name 識別するための型
 */
class Message[T](`type`: String, data :T)

object Message {
  /**
   * Todo: 暗黙型変換しているのも頂けないし、Parseに失敗した場合に無理やり適当なメッセージに収めようとしているのは違うと思う
   */
  implicit def json2object(value: JsValue): Message[_] = {
    (value \ "type").asOpt[String].flatMap {
      case BroadcastJoinMessage.Type => value.asOpt[BroadcastJoinMessage]
      case BroadcastLeaveMessage.Type => value.asOpt[BroadcastLeaveMessage]
      case BroadcastChatMessage.Type => value.asOpt[BroadcastChatMessage]
      case BroadcastDrawMessage.Type => value.asOpt[BroadcastDrawMessage]
      case BroadcastUpdatePenMessage.Type => value.asOpt[BroadcastUpdatePenMessage]
      case RequestChatMessage.Type => value.asOpt[RequestChatMessage]
      case RequestDrawMessage.Type => value.asOpt[RequestDrawMessage]
      case _ => Some(ResponseMessage.notOk)
    }.getOrElse(ResponseMessage.notOk)
  }

  /**
   * Todo: toJsonを持つものをcaseで囲ってtoJsonしたいけどどうしたらいい
   */
  implicit def object2json(event: Message[_]): JsValue = {
    event match {
      case event: BroadcastJoinMessage => Json.toJson(event)
      case event: BroadcastLeaveMessage => Json.toJson(event)
      case event: BroadcastChatMessage => Json.toJson(event)
      case event: BroadcastDrawMessage => Json.toJson(event)
      case event: BroadcastUpdatePenMessage => Json.toJson(event)
      case event: BroadcastErrorMessage => Json.toJson(event)
      case event: RequestChatMessage => Json.toJson(event)
      case event: RequestDrawMessage => Json.toJson(event)
      case event: ResponseMessage => Json.toJson(event)
      case event: BroadcastErrorMessage => Json.toJson(event)
    }
  }
}
