package com.mlesniak.akka.playground

import akka.actor.{ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.Await
import scala.concurrent.duration._

object Boot extends App {
  // Create system and base actors.
  implicit val system = ActorSystem("system")
  implicit val timeout = Timeout(1024 seconds)
  system.actorOf(Props[MainActor], MainActor.name)

  // Example of service registry.
  // We should have a global object which handles /user/ - prefixing.
  val main = system.actorSelection("/user/" + MainActor.name)
  val result = main ? "start"

  val sum = Await.result(result, timeout.duration)
  println("*** sum = " + sum)
}
