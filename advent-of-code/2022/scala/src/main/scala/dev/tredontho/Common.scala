package dev.tredontho

import scala.io.Source

/** Common behavior each day's solution should support
 */
trait Solver {
  final def load(filename: String): List[String] = {
    val bufferedSource = Source.fromFile(filename)
    val lines = bufferedSource.getLines.toList
    bufferedSource.close
    lines
  }
  def solve(lines: List[String]): Solution
}

case class Solution(partOne: String, partTwo: String)
