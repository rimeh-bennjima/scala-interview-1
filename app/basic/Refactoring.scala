package com.particeep.test.basic

import scala.annotation.tailrec

/**
 * What is the complexity of the function ?
 *
 * Refactor it to be a better function
 */
object Refactoring {

  case class File(
    name:     String,
    category: String
  )

  def getCategories(files: List[File]): List[String] = {
    val categories: List[String] = List()
    @tailrec
    def build(result: List[String], files: List[File]): List[String] = {
      files match {
        case File(_, category) :: xs if category.nonEmpty && !result.contains(category) => build(result :+ category, xs)
        case File(_, _) :: xs                                                           => build(result, xs)
        case Nil                                                                        => result
      }
    }
    build(categories, files)
  }
}
