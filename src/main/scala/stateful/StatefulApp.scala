package cyapex
package stateful

import StatefulActor.StatefulCommand
import org.apache.pekko.actor.typed.ActorSystem
import org.apache.pekko.actor.typed.scaladsl.AskPattern.Askable
import org.apache.pekko.util.Timeout

import scala.concurrent.ExecutionContext
import scala.concurrent.duration.DurationInt
import scala.util.{Failure, Success}

object StatefulApp extends App {
  implicit val ec: ExecutionContext = scala.concurrent.ExecutionContext.global
  val statefulSystem: ActorSystem[StatefulCommand] = ActorSystem(StatefulActor(0), "StatefulSystem")

  implicit val timeout: Timeout = 3.seconds
  implicit val scheduler = statefulSystem.scheduler


  // Send multiple greetings and observe the counter in replies
  for (i <- 1 to 5) {
    val result = statefulSystem.ask(ref => StatefulActor.StatefulGreet(s"User-$i", ref))
    result.onComplete {
      case Success(StatefulActor.StatefulGreeted(message)) => println(s"Stateful reply: $message")
      case Failure(ex) => println(s"Failed: $ex")
    }
  }
}

