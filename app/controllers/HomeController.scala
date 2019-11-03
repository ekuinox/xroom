package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import actors.HelloActor
import actors.HelloActor.SayHello
import akka.actor.ActorSystem
import akka.stream.Materializer
import akka.util.Timeout
import play.api.libs.json.JsValue
import play.api.libs.streams.ActorFlow

import scala.concurrent.ExecutionContext

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(cc: ControllerComponents)(implicit system: ActorSystem, materializer: Materializer) extends AbstractController(cc) {

  def index() = Action { implicit request: Request[AnyContent] =>
    val roomId = Math.floor(Math.random * 1000).toInt
    Redirect(s"/$roomId")
  }

  def socket = WebSocket.accept[String, String] { request =>
    ActorFlow.actorRef { out =>
      HelloActor.props(out)
    }
  }
}
