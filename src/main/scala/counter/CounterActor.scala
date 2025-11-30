package cyapex
package counter


import org.apache.pekko.actor.typed.scaladsl.Behaviors
import org.apache.pekko.actor.typed.{ActorRef, Behavior}

sealed trait CounterCommand
case object IncreatmentCounter extends  CounterCommand
case class GetCurrentValue(replyTo: ActorRef[Int]) extends CounterCommand

object CounterActor {
  //  Initial behaviour with starting count

  def apply(): Behavior[CounterCommand] = counterBehavior(0)

  //  Initial behavior with state
  private def counterBehavior(currentCount: Int): Behavior[CounterCommand] = {
    Behaviors.receive{(context, message) =>
      message match {
        case IncreatmentCounter =>
          val newCount = currentCount + 1
          context.log.info(s"Counter incremented with: $newCount")
          counterBehavior(newCount)  //return new behavior with updated state

        case GetCurrentValue(replyTo) =>
          context.log.info(s"Current count requested: $currentCount")
          replyTo ! currentCount   //send the currentCount back
          Behaviors.same         //keep the state the same

      }
    }

  }
}
