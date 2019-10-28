import play.sbt.PlayRunHook
import sbt._
import scala.sys.process.Process

object Webpack {
  def apply(base: File): PlayRunHook = {
    object WebpackProcess extends PlayRunHook {
      override def beforeStarted(): Unit = {
        Process(Seq("cmd", "/c", "yarn", "webpack", "--watch"), new File(base.getPath + "/front")).run
      }
    }
    WebpackProcess
  }
}
