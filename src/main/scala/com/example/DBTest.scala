package com.example

import org.slf4j.LoggerFactory

import scala.slick.driver.PostgresDriver.simple._

case class Person(firstName:String, lastName:String)

trait DBTest {

  val logger = LoggerFactory.getLogger("DBTest")

  val db = Database.forURL("jdbc:postgresql:test:example", driver = "org.postgresql.Driver")
  implicit val session = db.createSession

  class TestModel(tag:Tag) extends Table[(Long, String)](tag,"test_model") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def value = column[String]("value",O.NotNull)
    def * = (id,value)
  }

  val models = TableQuery[TestModel]
  models.ddl.create

  private val autoInc = models.map(m => m.value) returning models.map(_.id) into {
    case (_,id) => id
  }

  def insert(s:String)(implicit session:Session) = {
    val id = autoInc.insert(s)
  }

}
