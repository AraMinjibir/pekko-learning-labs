package cyapex
package greeting

import jdk.internal.joptsimple.internal.Messages.message
import org.apache.pekko.actor.TypedActor.context
import org.apache.pekko.actor.typed.scaladsl.Behaviors
import org.apache.pekko.actor.typed.{ActorRef, Behavior}
import org.slf4j.LoggerFactory


object GreetingActor {

  sealed trait GreetingCommand
  final case class Greet(name: String, replyTo: ActorRef[Greeted]) extends GreetingCommand
  final case class Greeted(message: String) extends  GreetingCommand

  private val log = LoggerFactory.getLogger("GreetingActor")

  def apply(): Behavior[GreetingCommand] = Behaviors.receive{(context, message) =>
    message match {
      case Greet(name, replyTo) =>
        val reply = s"Hello, $name! Welcome to Apache Pekko."
        log.info(s"Greeting requested for: $name")
        replyTo ! Greeted(reply)
        Behaviors.same

    }
  }

}

