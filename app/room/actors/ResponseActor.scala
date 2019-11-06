package room.actors

import akka.actor.{ Actor, ActorRef, PoisonPill, Props }
import play.api.libs.functional.syntax._
import play.api.libs.json._
import room._
import room.events._
import room.events.{Join, Leave, Talk, Error}

case class ResForm(user: String, text: String)

class ResponseActor(out: ActorRef, identifier: String) extends Actor {


  override def receive: Receive = {
    case join: Join => out ! Json.toJson(join)
    case leave: Leave => out ! Json.toJson(leave)
    case talk: Talk => out ! Json.toJson(talk)
    case error: Error => {
      if (error.to == identifier)
        out ! Json.toJson(Error("Bad Request"))
    }
    case msg: Any => println(msg.toString)
  }

  override def postStop(): Unit = super.postStop()

  def handleMessage(event: Event): JsValue = {
    event match {
      case join: Join => Json.toJson(join)
      case leave: Leave => Json.toJson(leave)
      case talk: Talk => Json.toJson(talk)
      case _ => Json.toJson(Error("Bad Request"))
    }
  }
}

object ResponseActor {
  def props(out: ActorRef, identifier: String): Props = Props(new ResponseActor(out, identifier))
}