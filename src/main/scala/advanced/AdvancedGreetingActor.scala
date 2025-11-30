package cyapex
package advanced

import org.apache.pekko.actor.TypedActor.context
import org.apache.pekko.actor.typed.{ActorRef, Behavior}
import org.apache.pekko.actor.typed.scaladsl.Behaviors

object AdvancedGreetingActorActor {

  sealed trait AdvancedCommand
  final case class MorningGreet(name: String, replyTo: ActorRef[Greeted]) extends AdvancedCommand
  final case class AfternoonGreet(name: String, replyTo: ActorRef[Greeted]) extends AdvancedCommand
  final case class CustomGreet(name: String, prefix: String, replyTo: ActorRef[Greeted]) extends AdvancedCommand

  final case class Greeted(message:String) extends AdvancedCommand

  def apply():Behavior[AdvancedCommand] = Behaviors.receive{(context, message) =>
    message match {
      case MorningGreet(name, replyTo) =>
        val msg = s"Good Morning $name!"
        context.log.info(msg)
        replyTo ! Greeted(msg)
        Behaviors.same

      case AfternoonGreet(name, replyTo) =>
        val afternoonMessage = s"Good Afternoon $name!"
        context.log.info(afternoonMessage)
        replyTo ! Greeted(afternoonMessage)
        Behaviors.same

      case CustomGreet(name, prefix, replyTo) =>
        val customMessage = s"$name $prefix"
        context.log.info(customMessage)
        replyTo ! Greeted(customMessage)
        Behaviors.same

    }
  }

}
