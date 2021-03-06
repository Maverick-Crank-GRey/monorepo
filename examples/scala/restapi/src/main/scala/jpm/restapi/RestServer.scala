package jpm.restapi

// Mostly based on https://github.com/theiterators/akka-http-microservice
// and https://github.com/akka/akka-http/blob/master/akka-http-tests/src/main/java/akka/http/javadsl/server/examples/petstore/PetStoreExample.java

import akka.actor.ActorSystem
import akka.event.{LoggingAdapter, Logging}
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.stream.{ActorMaterializer, Materializer}
import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory
import scala.concurrent.ExecutionContextExecutor

trait Service {
  implicit val system: ActorSystem
  implicit def executor: ExecutionContextExecutor
  implicit val materializer: Materializer
  import GreetingJsonSupport._

  def config: Config
  val logger: LoggingAdapter

  val routes = {
    logRequestResult("akka-http-microservice") {
      pathSingleSlash { get { complete {
        logger.debug("Hello")
        "Hello World!"
      } } } ~
      pathPrefix("hi") { parameters('name.?) { name => complete {
        val theName = name.getOrElse("unnamed")
        logger.debug(s"Hi $theName")
        s"Hello $theName"
      } } } ~
      pathPrefix("greeting") { post { entity(as[GreetingRequest]) { greetingRequest =>
        complete { GreetingResponse(s"Greetings of type ${greetingRequest.greeting} for ${greetingRequest.name}")}
      } } }
    }
  }
}

object RestServerMain extends App with Service {
  override implicit val system = ActorSystem()
  override implicit val executor = system.dispatcher
  override implicit val materializer = ActorMaterializer()

  override val config = ConfigFactory.load("application.conf")
  override val logger = Logging(system, getClass)

  println("Server startup")
  logger.debug("Server startup")
  Http().bindAndHandle(routes, "0.0.0.0", 8080)
  println("Server stopping")
  logger.debug("Server stopping")
}