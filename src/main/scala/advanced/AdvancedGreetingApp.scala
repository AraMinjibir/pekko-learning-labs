package cyapex
package advanced

import cyapex.advanced.AdvancedGreetingActorActor.AdvancedCommand
import org.apache.pekko.actor.typed.ActorSystem
import org.apache.pekko.actor.typed.scaladsl.AskPattern.Askable
import org.apache.pekko.util.Timeout

import scala.concurrent.ExecutionContext
import scala.concurrent.duration.DurationInt
import scala.util.{Failure, Success}

object AdvancedGreetingApp extends  App{

  implicit val ec: ExecutionContext = scala.concurrent.ExecutionContext.global

  val advancedSystem: ActorSystem[AdvancedCommand] = ActorSystem(AdvancedGreetingActorActor(), "AdvancedSystem")

  implicit val timeout: Timeout = 3.seconds
  implicit val scheduler = advancedSystem.scheduler
  // Morning greeting
  val m = advancedSystem.ask(ref => AdvancedGreetingActorActor.MorningGreet("Ara", ref))
  m.onComplete {
    case Success(AdvancedGreetingActorActor.Greeted(msg)) => println(s"Morning: $msg")
    case Failure(ex) => println(s"Failed morning: $ex")
  }


  // Afternoon greeting
  val a = advancedSystem.ask(ref => AdvancedGreetingActorActor.AfternoonGreet("Minjibir", ref))
  a.onComplete {
    case Success(AdvancedGreetingActorActor.Greeted(msg)) => println(s"Afternoon: $msg")
    case Failure(ex) => println(s"Failed afternoon: $ex")
  }

  // Custom greeting
  val c = advancedSystem.ask(ref => AdvancedGreetingActorActor.CustomGreet("Abubakar", "Barka!", ref))
  c.onComplete {
    case Success(AdvancedGreetingActorActor.Greeted(msg)) => println(s"Custom: $msg")
    case Failure(ex) => println(s"Failed custom: $ex")
  }
}

