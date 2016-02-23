package com.mlesniak.akka.playground

import akka.actor.{Actor, ActorLogging, Props}

object MainActor {
  val name = getClass.getName
}

class MainActor extends Actor with ActorLogging {

  import context._

  val size = 10
  var sum = 0

  override def receive: Receive = {
    case _: String => {
      log.info("Starting system")
      1 to size foreach { id =>
        actorOf(Props[NumberActor], id.toString) ! NumberCompute(id)
      }
    }

    case number: Int => {
      sum = sum + number
      log.info(number + " => " + sum)
    }
  }
}
