

import akka.actor.ActorSystem
import akka.http.scaladsl.{Http, model}
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpMethods, HttpRequest, HttpResponse}

import java.net.URLEncoder
import scala.concurrent.Future
import scala.concurrent.duration._

object AkkaHttp5mins {

  implicit val system = ActorSystem() //Akka actors
  import system.dispatcher // "thread pool"

  val source =
    """
      |object SimpleApp {
      | val aField = 2
      |
      | def aMethod(x: Int) = x + 1
      |
      | def main(args: Array[String]): Unit = println(aField)
      |}
      |""".stripMargin

  val request = HttpRequest(
    method = HttpMethods.POST,
    uri = "https://coderlessons.com/articles/java/akka-zametki-akter-obmen-soobshcheniiami-1",
    entity = HttpEntity(
      ContentTypes.`application/x-www-form-urlencoded`, // application/json in most cases
      s"source=${URLEncoder.encode(source, "UTF-8")}&language=Scala&theme=Sunburst"// the actual data you want to send
    )
  )

  def sendRequest(): Future[String] = {
    val responseFuture: Future[HttpResponse] = Http().singleRequest(request)
    val entityFuture: Future[HttpEntity.Strict] = responseFuture.flatMap(response => response.entity.toStrict(2.seconds))
    entityFuture.map(entity => entity.data.utf8String)
  }

  def main(args: Array[String]): Unit = {
    sendRequest().foreach(println)
  }
} 