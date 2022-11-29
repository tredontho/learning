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

  "setHead" should "replace the first element of a non-empty list" in {
    List.setHead(List(1,2,3), 0) shouldEqual List(0,2,3)
  }

  it should "return a singleton list if the list argument is Nil" in {
    List.setHead(Nil, 1) shouldEqual List(1)
  }

  "drop" should "remove n elements when n is positive" in {
    List.drop(List(1,2,3,4,5), 3) shouldEqual List(4,5)
  }

  it should "return the original list if n is zero" in {
    List.drop(List(1,2,3,4,5), 0) shouldEqual List(1,2,3,4,5)
  }

  it should "return the original list if n is negative" in {
    List.drop(List(1,2,3), -5) shouldEqual List(1,2,3)
  }

  it should "return Nil if n > length of list" in {
    List.drop(List(1,2,3,4,5), 6) shouldEqual Nil
  }

  "dropWhile" should "return the original list if the function always returns false" in {
    val original = List(1,2,3,4,5)
    List.dropWhile(original, (x: Int) => false) shouldEqual original
  }

  it should "return Nil if the function always returns true" in {
    val original = List(1,2,3,4,5)
    List.dropWhile(original, (x: Int) => true) shouldEqual Nil
  }

  "init" should "return a singleton list when a 2 element list is given" in {
    List.init(List(1,2)) shouldEqual List(1)
  }

  it should "return all but the last element" in {
    List.init(List(1,2,3,4,5)) shouldEqual List(1,2,3,4)
  }

  it should "return Nil given Nil" in {
    List.init(Nil) shouldEqual Nil
  }

  it should "return Nil given a singleton list" in {
    List.init(List(1)) shouldEqual Nil
  }

  "lengthViaFoldRight" should "return 0 for an empty list" in {
    List.lengthViaFoldRight(Nil) shouldEqual 0
  }

  it should "return 1 for a singleton list" in {
    List.lengthViaFoldRight(List(0)) shouldEqual 1
  }

  "sumViaFoldLeft" should "return 0 for an empty list" in {
    List.sumViaFoldLeft(Nil) shouldEqual 0
  }

  it should "return 55 for the integers from 1 to 10" in {
    List.sumViaFoldLeft(List(1,2,3,4,5,6,7,8,9,10)) shouldEqual 55
  }

  "productViaFoldLeft" should "return 1 for an empty list" in {
    List.productViaFoldLeft(Nil) shouldEqual 1
  }
  it should "return 120 for the integers from 1 to 5" in {
    List.productViaFoldLeft(List(1,2,3,4,5)) shouldEqual 120
  }
}
