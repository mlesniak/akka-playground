package com.mlesniak.akka.playground

import akka.actor.{Actor, ActorLogging, ActorRef, Props}

case class Result(sum: Int)

object MainActor {
  val name = getClass.getName
}


class MainActor extends Actor with ActorLogging {

  import context._

  val size = 10
  var sum = 0
  var count = 0

  var bootSender: ActorRef = null

  override def receive: Receive = {
    case _: String => {
      log.info("Starting system")
      bootSender = sender
      1 to size foreach { id =>
        actorOf(Props[NumberActor], id.toString) ! NumberCompute(id)
      }
    }

    case number: Int => {
      sum = sum + number
      count = count + 1
      log.info(number + " => " + sum)

      if (count == size) {
        bootSender ! Result(sum)
      }
    }
  }
}
