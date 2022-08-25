package chapter3

import org.scalatest.flatspec._
import org.scalatest.matchers.should._

class Chapter3Spec extends AnyFlatSpec with Matchers {
  "tail" should "return the list without the first element" in {
    List.tail(List(1,2,3)) shouldEqual List(2,3)
  }

  it should "return Nil for an empty list" in {
    List.tail(Nil) shouldEqual Nil
  }
}
