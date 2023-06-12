package dev.tredontho.leetcode

trait Problem[-In, +Out] {
  def name: String
  def description: String
  def solve(input: In): Out
}
