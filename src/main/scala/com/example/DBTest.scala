package com.example

import org.slf4j.LoggerFactory

import scala.slick.driver.PostgresDriver.simple._

case class Person(firstName:String, lastName:String)

trait DBTest {

  val logger = LoggerFactory.getLogger("DBTest")

  val db = Database.forURL("jdbc:postgresql:test:example", driver = "org.postgresql.Driver")
  implicit val session = db.createSession

}
