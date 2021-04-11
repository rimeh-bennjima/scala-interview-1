package com.particeep.test.basic

/**
 * Compute the avarage of the list
 *
 * ex : [1, 10, 16] -> 9
 */
object ComputeAvarage {

  def average(l: List[Double]) = {
    val (sum, length) = l.foldLeft((0.toDouble, 0.toDouble))({ case ((sum, length), x) => (x + sum, 1 + length) })
    sum / length
  }
}
