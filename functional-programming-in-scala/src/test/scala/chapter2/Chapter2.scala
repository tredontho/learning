package chapter2

import org.scalatest.flatspec._
import org.scalatest.matchers.should._

class Chapter2Spec extends AnyFlatSpec with Matchers {
  "The Chapter2 object's fib function" should "calculate the first 10 fib numbers correctly" in {
    val result = List(1,2,3,4,5,6,7,8,9,10).map(Chapter2.fib(_))
    result shouldEqual List(1,1,2,3,5,8,13,21,34,55)
  }
}
