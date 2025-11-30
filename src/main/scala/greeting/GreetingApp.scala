package cyapex
package greeting

import GreetingActor.GreetingCommand
import org.apache.pekko.actor.typed.ActorSystem
import org.apache.pekko.actor.typed.scaladsl.AskPattern.Askable
import org.apache.pekko.util.Timeout

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._
import scala.util.{Failure, Success}


object GreetingApp extends App {

  val greetingSystem: ActorSystem[GreetingCommand] = ActorSystem(GreetingActor(), "GreetingSystem")
  implicit val timeout: Timeout = 3.seconds
  implicit val scheduler = greetingSystem.scheduler
  implicit val ec: ExecutionContext = greetingSystem.executionContext

  // Ask the actor to greet someone
  val result = greetingSystem.ask(replyTo => GreetingActor.Greet("Ara", replyTo))

  result.onComplete{
    case Success(GreetingActor.Greeted(message)) => println(s"Actor responded: $message")
    case Failure(exception) => println(exception)
  }
}
