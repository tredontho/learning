package dev.tredontho.leetcode.problem

import dev.tredontho.leetcode.Problem
import scala.util.Try
import scala.util.Success
import scala.util.Failure

object P0007 extends Problem[Int, Int] {

  override def name: String = "Reverse Integer"

  override def description: String =
    """Given a signed 32-bit integer x, return x with its digits reversed.
      |If reversing x causes the value to go outside the signed 32-bit integer range [-231, 231 - 1], then return 0.""".stripMargin

  override def solve(input: Int): Int = {
    val s = input.toString
    val reversed = if (s.head == '-') s"-${s.tail.reverse}" else s.reverse
    Try(reversed.toInt) match {
      case Success(x) => x
      case Failure(x) => 0
    }
  }
}
