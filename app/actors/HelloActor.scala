package actors

import akka.actor._
import play.api.libs.json.{JsValue, Json}

object HelloActor {
  def props(out: ActorRef) = Props(new HelloActor(out))

  case class SayHello(name: String)
}

class HelloActor(out: ActorRef) extends Actor {
  import HelloActor._

  override def preStart(): Unit = {
    println("open")
  }

  override def postStop(): Unit = {
    println("close")
  }

  override def receive = {
    case request =>
      println(request.toString)
      val response = Json.parse("""{"result": "hello"}"""")
      out ! response.toString()
  }


  def handleMessage(jsValue: JsValue): JsValue = {
    println(s"handle message ${jsValue.toString()}")
    Json.parse("""{"result": "hello"}"""")
  }
}
