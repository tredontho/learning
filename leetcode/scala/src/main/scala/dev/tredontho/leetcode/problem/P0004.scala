package dev.tredontho.leetcode.problem

import dev.tredontho.leetcode.Problem
import scala.math.Ordering.Implicits._

object P0004 extends Problem[(Array[Int], Array[Int]), Double] {

  override def name: String = "Median of Two Sorted Arrays"

  override def description: String =
    """|Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
       |
       |The overall run time complexity should be O(log (m+n)).""".stripMargin.trim

  override def solve(input: (Array[Int], Array[Int])): Double = median(
    merge(input._1, input._2)
  )

  def merge(l: Array[Int], r: Array[Int]): Array[Int] = {
    def go(l: Array[Int], r: Array[Int], acc: Array[Int]): Array[Int] =
      (l, r) match {
        case (Array(), Array()) => acc
        case (Array(x, _*), Array()) => acc ++ l
        case (Array(), Array(x, _*)) => acc ++ r
        case _ =>
          if (l.head <= r.head) go(l.tail, r, acc :+ l.head)
          else go(l, r.tail, acc :+ r.head)

      }
    go(l, r, Array.empty)
  }

  def median(l: Array[Int]): Float = {
    val length = l.length
    val midpoints =
      (((length - 1) / 2.0f).floor.toInt, ((length - 1) / 2.0f).ceil.toInt)

    (l(midpoints._1) + l(midpoints._2)) / 2.0f
  }

}
