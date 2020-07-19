package room.actors

import akka.actor.{Actor, ActorRef, Props}
import play.api.libs.json._
import room.messages._

case class ResForm(user: String, text: String)

class ResponseActor(out: ActorRef, identifier: String, roomId: String) extends Actor {

  override def receive: Receive = {
    case RequestData(message: ResponseMessage, triggerUserIdentifier) => {
      if (triggerUserIdentifier == identifier) {
        out ! Json.toJson(message)
      }
    }
    case RequestData(join: BroadcastJoinMessage, _) => out ! Json.toJson(join)
    case RequestData(leave: BroadcastLeaveMessage, _) => out ! Json.toJson(leave)
    case RequestData(talk: BroadcastChatMessage, _) => out ! Json.toJson(talk)
    case RequestData(draw: BroadcastDrawMessage, _) => out ! Json.toJson(draw)
    case msg: Any => println(msg.toString)
  }
}

object ResponseActor {
  def props(out: ActorRef, identifier: String, roomId: String): Props = Props(new ResponseActor(out, identifier, roomId))
}