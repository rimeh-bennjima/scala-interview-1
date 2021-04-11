package com.particeep.test.async

import scala.concurrent.Future

import scala.concurrent.ExecutionContext.Implicits.global

case class CEO(id: String, first_name: String, last_name: String)
case class Enterprise(id: String, name: String, ceo_id: String)

object CEODao {
  val ceos = List(
    CEO("1", "Mark", "Zuckerberg"),
    CEO("2", "Sundar", "Pichai")
  )

  def byId(id: String): Future[Option[CEO]] = Future(ceos.find(_.id == id))
}

object EnterpriseDao {
  val enterprises = List(
    Enterprise("1", "Google", "1"),
    Enterprise("2", "Facebook", "2")
  )

  def byId(id:        String): Future[Option[Enterprise]] = Future(enterprises.find(_.id == id))
  def byCEOId(ceo_id: String): Future[Option[Enterprise]] = Future(enterprises.find(_.ceo_id == ceo_id))
}

object WhatsWrong2 {
//Review this code. What could be done better ? How would you do it ?
  /* The future can be parallely and we should do for an option type getOrElse inside get because if we have None the get handle an exception. */

  def getCEOAndEnterprise(ceo_id: Option[String]): Future[(Option[CEO], Option[Enterprise])] = {
    def parallely[A, B](f1: Future[A], f2: Future[B]): Future[(A, B)] = f1 zip f2
    parallely(CEODao.byId(ceo_id.getOrElse("")), EnterpriseDao.byCEOId(ceo_id.getOrElse("")))
  }
}
