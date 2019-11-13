package room.actors

import akka.actor.{Actor, ActorRef, PoisonPill, Props}
import play.api.libs.functional.syntax._
import play.api.libs.json._
import room._
import room.events._
import room.events.server._

case class ResForm(user: String, text: String)

class ResponseActor(out: ActorRef, identifier: String, roomId: String) extends Actor {

  override def receive: Receive = {
    case RequestData(join: Join, _) => out ! Json.toJson(join)
    case RequestData(leave: Leave, _) => out ! Json.toJson(leave)
    case RequestData(talk: Talk, _) => out ! Json.toJson(talk)
    case RequestData(updateUsername: UpdateUsername, _) => out ! Json.toJson(updateUsername)
    case RequestData(draw: Draw, _) => out ! Json.toJson(draw)
    case RequestData(updatePen: UpdatePen, _) => out ! Json.toJson(updatePen)
    case RequestData(error: Error, triggerUserIdentifier) => {
      if (triggerUserIdentifier == identifier)
        out ! Json.toJson(error)
    }
    case msg: Any => println(msg.toString)
  }

}

object ResponseActor {
  def props(out: ActorRef, identifier: String, roomId: String): Props = Props(new ResponseActor(out, identifier, roomId))
}