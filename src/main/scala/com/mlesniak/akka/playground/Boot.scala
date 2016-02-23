package com.mlesniak.akka.playground

import akka.actor.{ActorSystem, Props}

object Boot extends App {
  // Create system and base actors.
  implicit val system = ActorSystem("system")
  system.actorOf(Props[MainActor], MainActor.name)

  // Example of service registry.
  val main = system.actorSelection("/user/" + MainActor.name)
  main ! "start"
}
