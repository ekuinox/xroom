package room.actors

import akka.actor._
import play.api.libs.functional.syntax._
import play.api.libs.json._
import room._
import room.events._
import room.events.{Join, Leave, Talk, Error}

case class RequestData(event: Event, triggerUserIdentifier: String)

class RequestActor(out: ActorRef, identifier: String) extends Actor {

  override def receive: Receive = {
    case msg: JsValue => {
      val response = RequestData(handleMessage(msg), identifier)
      out ! response
    }
    case _ => {
      out ! RequestData(BadRequestError, identifier)
    }
  }

  override def preStart() = println(s"Start ${out.path}")

  override def postStop() = println(s"Stop ${out.path}")

  def handleMessage(event: Event): Event = {
    event match {
      case join: Join => join
      case leave: Leave => leave
      case talk: Talk => talk
      case _ => BadRequestError
    }
  }
}

object RequestActor {
  def props(out: ActorRef, identifier: String): Props = Props(new RequestActor(out, identifier))
}
