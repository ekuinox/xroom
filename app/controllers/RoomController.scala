package controllers

import akka.actor.ActorSystem
import akka.stream.Materializer
import akka.stream.scaladsl.{ Flow, Keep, Source }
import javax.inject._
import play.api._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.streams.ActorFlow
import play.api.mvc._
import play.api.libs.json.JsValue

import room._
import room.actors._
import room.events._

@Singleton
class RoomController @Inject()(cc: ControllerComponents) (implicit system: ActorSystem, materializer: Materializer, roomClient: RoomClient) extends AbstractController(cc) {

  def index(roomId: String) = Action {
    Ok (views.html.index (roomId) )
  }

  def ws(roomId: String) = WebSocket.accept[JsValue, JsValue] { request =>

    val identifier = request.cookies("PLAY_SESSION").value

    val room = roomClient.chatRoom(roomId)

    val userInput = ActorFlow.actorRef[JsValue, Event](out => RequestActor.props(out, identifier))

    val userOutPut = ActorFlow.actorRef[Event, JsValue](out => ResponseActor.props(out, identifier))

    userInput.viaMat(room.bus)(Keep.right).viaMat(userOutPut)(Keep.right)

  }

}
