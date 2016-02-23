package com.mlesniak.akka.playground

import akka.actor.{Actor, ActorLogging}

case class NumberCompute(val id: Int)

class NumberActor extends Actor with ActorLogging {
  override def receive: Receive = {
    case NumberCompute(id) => {
      log.info(id + " computing and sending response")
      val response = (Math.random() * 100).toInt
      sender ! response
    }
  }
}
