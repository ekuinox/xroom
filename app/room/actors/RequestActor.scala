package room.actors

import akka.actor._
import play.api.libs.functional.syntax._
import play.api.libs.json._
import room._
import room.events._
import room.events.{Join, Leave, Talk, Error}

class RequestActor(out: ActorRef) extends Actor {

  override def receive: Receive = {
    case msg: JsValue => {
      val response = handleMessage(msg)
      out ! response
    }
    case _ => {
      out ! JsObject(Seq("error" -> JsString("bad request")))
    }
  }

  override def preStart() = println(s"Start ${out.path}")

  override def postStop() = println(s"Stop ${out.path}")

  def handleMessage(event: Event): JsValue = {
    event match {
      case join: Join => Json.toJson(join)
      case leave: Leave => Json.toJson(leave)
      case talk: Talk => Json.toJson(talk)
      case _ => JsObject(Seq("error" -> JsString("bad request")))
    }
  }
}

object RequestActor {
  def props(out: ActorRef): Props = Props(new RequestActor(out))
}
