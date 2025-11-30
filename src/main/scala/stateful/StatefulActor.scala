package cyapex
package stateful

import org.apache.pekko.actor.TypedActor.context
import org.apache.pekko.actor.typed.scaladsl.Behaviors
import org.apache.pekko.actor.typed.{ActorRef, Behavior}

object StatefulActor {

  sealed trait StatefulCommand
  case class StatefulGreet(name: String, replyTo: ActorRef[StatefulGreeted]) extends StatefulCommand
  case class StatefulGreeted(message:String) extends StatefulCommand

  def apply(count: Int): Behavior[StatefulCommand] = Behaviors.receive {(context, message) =>
    message match {
      case StatefulGreet(name, replyTo) =>
        val newCount = count + 1
        val text = s"Greeting $newCount $name"
        context.log.info(text)
        replyTo ! StatefulGreeted(text)
        apply(newCount)
    }
  }

}

