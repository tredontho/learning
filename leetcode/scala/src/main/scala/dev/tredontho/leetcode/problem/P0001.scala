package dev.tredontho.leetcode.problem

import dev.tredontho.leetcode.Problem

object P0001 extends Problem[(List[Int], Int), List[Int]] {

  override def name: String = "Two Sum"

  override def description: String = """
  Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

You can return the answer in any order.
""".trim

  override def solve(input: (List[Int], Int)): List[Int] = {
    val nums = input._1
    val target = input._2
    val result = for {
      x <- nums.zipWithIndex
      y <- nums.zipWithIndex.tail
      if x._2 < y._2
      if (x._1 + y._1) == target
    } yield List(x._2, y._2)

    result.head
  }
}
