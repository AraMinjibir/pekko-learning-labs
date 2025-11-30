# pekko-learning-labs


Hands-on eercises for Apache Pekko Typed actors (Scala 2.13).


This repository contains multiple small exercises demonstrating typed actors experience:


- **CounterActor** — simple counter with Increment and GetCurrentValue (ask pattern)
- **GreeterActor** — basic greeting with replies
- **StatefulGreeterActor** — counts how many greetings it sent (behavior carries state)
- **AdvancedGreeterActor** — demonstrates multiple greeting types (morning, afternoon, custom)


## Quick start


1. Install SBT.
2. Clone repository.
3. Run an example with `sbt "runMain example.CounterApp"` or choose any other main class.


Example:


```bash
sbt "runMain example.CounterApp"