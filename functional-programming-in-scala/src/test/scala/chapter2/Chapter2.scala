package chapter2

import org.scalatest.flatspec._
import org.scalatest.matchers.should._

class Chapter2Spec extends AnyFlatSpec with Matchers {
  def myGT(a: Int, b: Int): Boolean = a > b
  def addFive(a: Int): Int = a + 5
  def timesTwo(a: Int): Int = a * 2
  "The Chapter2 object's fib function" should "calculate the first 10 fib numbers correctly" in {
    val result = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).map(Chapter2.fib(_))
    result shouldEqual List(1, 1, 2, 3, 5, 8, 13, 21, 34, 55)
  }

  "The Chapter2 object's isSorted function" should "return True for an Int array which is sorted" in {
    val result = Chapter2.isSorted(Array(10, 9, 8, 7), myGT)
    result shouldEqual true
  }

  it should "return false for an Int array which is not sorted" in {
    val result = Chapter2.isSorted(Array(10, 9, 11, 7), myGT)
    result shouldEqual false
  }

  "The Chapter2 object's curry function" should "allow a two paramater function to be called one element at a time" in {
    def curriedGT = Chapter2.curry(myGT)
    curriedGT(10)(9) shouldEqual myGT(10, 9)
  }

  "The Chapter2 object's uncurry function" should "be the inverse of curry" in {
    def curriedGT = Chapter2.curry(myGT)
    Chapter2.uncurry(curriedGT)(10, 9) shouldEqual myGT(10, 9)
  }

  "The Chapter2 object's compose function" should "perform the first function on the result of the second function" in {
    Chapter2.compose(timesTwo, addFive)(10) shouldEqual 30
  }

}
