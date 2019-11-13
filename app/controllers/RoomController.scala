package controllers

import akka.actor.ActorSystem
import akka.stream.Materializer
import akka.stream.scaladsl.{Flow, Keep, Source}
import javax.inject._
import play.api._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.streams.ActorFlow
import play.api.mvc._
import play.api.libs.json.JsValue
import room._
import room.actors._
import room.events._
import room.events.server.Event

import scala.concurrent.Future

@Singleton
class RoomController @Inject()(cc: ControllerComponents) (implicit system: ActorSystem, materializer: Materializer, roomClient: RoomClient) extends AbstractController(cc) {

  def index(roomId: String) = Action {
    Ok(views.html.index(roomId))
  }

  def ws(roomId: String) = WebSocket.acceptOrResult[JsValue, JsValue] { request =>

    val identifier = request.cookies("PLAY_SESSION").value

    val room = roomClient.chatRoom(roomId)

    Future.successful(room.participants.get(identifier) match {
      case None =>
        val userInput = ActorFlow.actorRef[JsValue, Event](out => RequestActor.props(out, identifier, roomId))

        val userOutPut = ActorFlow.actorRef[Event, JsValue](out => ResponseActor.props(out, identifier, roomId))

        Right(userInput.viaMat(room.bus)(Keep.right).viaMat(userOutPut)(Keep.right))
      case Some(_) =>
        Left(Forbidden) // this session already connected
    })
  }

}
