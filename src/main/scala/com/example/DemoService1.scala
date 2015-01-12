package com.example

import akka.actor.Actor
import spray.routing.HttpService

class DemoService1Actor extends Actor with DemoService1 with DBTest {

  def actorRefFactory = context

  def receive = {
    case _ => {
      println("demoservice1actor received message")
      sender() ! serviceName
      insert("hello")
    }
  }
}

trait DemoService1 extends HttpService {
  val serviceName = "DemoService1"
}
