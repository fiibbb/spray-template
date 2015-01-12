package com.example

import akka.actor.Actor
import spray.routing.HttpService

class DemoService2Actor extends Actor with DemoService2 with DBTest {

  def actorRefFactory = context

  def receive = {
    case _ => {
      println("DemoService2Actor received message")
      sender() ! serviceName

    }
  }

}

trait DemoService2 extends HttpService {
  val serviceName = "DemoService2"
}
