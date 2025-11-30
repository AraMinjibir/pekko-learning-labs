package cyapex
package counter

import org.apache.pekko.actor.typed.ActorSystem
import org.apache.pekko.actor.typed.scaladsl.AskPattern._
import org.apache.pekko.util.Timeout
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Success, Failure}


object CounterApp extends App {

  //  Create Actor System

  implicit val timeout: Timeout = 3.seconds

  val counterSystem: ActorSystem[CounterCommand] = ActorSystem(CounterActor(), "CounterSystem")

  // Scheduler comes from the ActorSystem
  implicit val scheduler = counterSystem.scheduler

  //  Send increment count 5 times
  for(_ <- 1 to 5){
    counterSystem ! IncreatmentCounter
  }
  // Ask the actor
  val result = counterSystem.ask(ref => GetCurrentValue(ref))

  result.onComplete {
    case Success(value) => println(s"Current counter value: $value")
    case Failure(exception) => println(s"Failed to get value: $exception")
  }
}

