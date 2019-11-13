package room.actors

import akka.actor._
import play.api.libs.functional.syntax._
import play.api.libs.json._
import room._
import room.events._
import room.events.client.{ Event => ClientEvent, Talk => ClientTalk, UpdateUsername => ClientUpdateUsername }
import room.events.server._
import room._
import room.Participant._

case class RequestData(event: Event, triggerUserIdentifier: String)

class RequestActor(out: ActorRef, identifier: String, roomId: String) extends Actor {
  override def receive: Receive = {
    case msg: JsValue => {
      val response = RequestData(handleMessage(msg), identifier)
      out ! response
    }
    case _ => {
      out ! RequestData(BadRequestError, identifier)
    }
  }

  override def preStart(): Unit = {
    RoomClient.addParticipant(roomId, identifier, Participant(makeUsername, identifier))
    out ! RequestData(Join(username), identifier)
  }

  override def postStop(): Unit = {
    out ! RequestData(Leave(username), identifier)
    RoomClient.removeParticipant(roomId, identifier)
  }

  def handleMessage(event: ClientEvent): Event = {
    event match {
      case talk: ClientTalk => Talk(username = RoomClient.getParticipant(roomId, identifier).get.username, text = talk.text)
      case updateUsername: ClientUpdateUsername =>
        RoomClient.addParticipant(roomId, identifier, RoomClient.getParticipant(roomId, identifier).get.copy(username = updateUsername.username))
        UpdateUsername(oldUsername = username, newUsername = updateUsername.username)
      case _ => BadRequestError
    }
  }

  def username: String = RoomClient.getParticipant(roomId, identifier).get.username

  def makeUsername: String = scala.util.Random.alphanumeric.take(10).mkString

}

object RequestActor {
  def props(out: ActorRef, identifier: String, roomId: String): Props = Props(new RequestActor(out, identifier, roomId))
}
