package room.actors

import akka.actor._
import play.api.libs.json._
import room.messages._
import room._

case class RequestData(message: Message[_, _], triggerUserIdentifier: String)

class RequestActor(out: ActorRef, identifier: String, roomId: String) extends Actor {
  override def receive: Receive = {
    case msg: JsValue => {
      val response = RequestData(handleMessage(msg), identifier)
      out ! response
    }
    case _ => {
      out ! RequestData(ResponseMessage.notOk, identifier)
    }
  }

  override def preStart(): Unit = {
    RoomClient.addParticipant(roomId, identifier, Participant(makeUsername))
    out ! RequestData(BroadcastJoinMessage(BroadcastJoinData(username)), identifier)
  }

  override def postStop(): Unit = {
    out ! RequestData(BroadcastLeaveMessage(BroadcastLeaveData(username)), identifier)
    RoomClient.removeParticipant(roomId, identifier)
  }

  def handleMessage(event: Message[_, _]): Message[_, _] = {
    event match {
      case chat: RequestChatMessage => BroadcastChatMessage(BroadcastChatData(username, chat.data.text))
      case draw: RequestDrawMessage => BroadcastDrawMessage(BroadcastDrawData(username, draw.data.position))
      case _ => ResponseMessage.notOk
    }
  }

  def username: String = RoomClient.getParticipant(roomId, identifier).get.username

  def makeUsername: String = scala.util.Random.alphanumeric.take(10).mkString

}

object RequestActor {
  def props(out: ActorRef, identifier: String, roomId: String): Props = Props(new RequestActor(out, identifier, roomId))
}
