package com.particeep.test.basic

/**
 * This is basic language questions so don't use external library or build in function
 */
object BasicScala {

  /**
   * Encode parameter in url format
   *
   * Example:
   *
   * input  : Map("sort_by" -> "name", "order_by" -> "asc", "user_id" -> "12")
   * output : "?sort_by=name&order_by=asc&user_id=12"
   *
   * input  : Map()
   * output : ""
   */
  def encodeParamsInUrl(params: Map[String, String]): String = {
    val url = params.foldLeft[String]("") { case (acc, string) =>
      acc.:++(s"""${string._1}=${string._2}&""")
    }
    if(url.nonEmpty) url.dropRight(1).patch(0, "?", 0) else url
  }

  /**
   * Test if a String is an email
   */
  def isEmail(maybeEmail: String): Boolean = """(\w+)@([\w\.]+)""".r.unapplySeq(maybeEmail).isDefined

  /**
   * Compute i ^ n
   *
   * Example:
   *
   * input : (i = 2, n = 3) we compute 2^3 = 2x2x2
   * output : 8
   *
   * input : (i = 99, n = 38997)
   * output : 1723793299
   */
  def power(i: Int, n: Int): Int = {
    def compute(result: Int, numberOfOccurence: Int): Int = {
      if(numberOfOccurence == 0) result
      else
        compute(result * i, numberOfOccurence - 1)
    }
    compute(1, n)
  }
}
