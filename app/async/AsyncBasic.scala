package com.particeep.test.async

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{ Failure, Success }

/**
 * You have 2 webservices, we want to compute the sum of the 2 webservice call.
 *
 * You need to write only the compute function.
 * For instance : compute(1) should return 1099 (1098 + 1)
 *
 * It's part of the exercise to handle error cases
 */
object AsyncBasic {
  def compute(id: String) = {
    def parallely[A, B](f1: Future[A], f2: Future[B]): Future[(A, B)] = f1 zip f2
    val numberOfCalls: Future[String Either Int] = parallely(Webservice1.call(id), Webservice2.call(id)).map {
      case (Some(number1), Right(number2)) => Right(number1 + number2)
      case (Some(number), Left(_))         => Right(number)
      case (_, Right(number))              => Right(number)
      case (_, Left(noValue))              => Left(noValue)
    }
    numberOfCalls.recover { case e: Exception => Left(s"Can not compute the calls: $e") }
  }
}

object Webservice1 {
  private[this] val result = Map(
    "1"  -> 1,
    "2"  -> 21,
    "5"  -> 4,
    "10" -> 1987
  )

  def call(id: String): Future[Option[Int]] = Future(result.get(id))
}

object Webservice2 {
  private[this] val result = Map(
    "1"  -> 1098,
    "3"  -> 218777,
    "9"  -> 434,
    "10" -> Int.MaxValue
  )

  def call(id: String): Future[Either[String, Int]] = Future {
    result.get(id) match {
      case Some(x) => Right(x)
      case None    => Left("No value")
    }
  }
}
