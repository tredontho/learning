package dev.tredontho.leetcode.problem

import dev.tredontho.leetcode.Problem

object P0005 extends Problem[String, String] {

  override def name: String = "Longest Palindromic Substring"

  override def description: String =
    "Given a string s, return the longest palindromic substring in s."

  override def solve(input: String): String = {
    val xs = input.toList
    val palindromes = (for {
      ys <- xs.tails.filter(_.nonEmpty)
      zs <- ys.inits.filter(w => w.nonEmpty && palindrome(w).isDefined)
    } yield (zs))
    palindromes.maxBy(_.length).mkString
  }

  def palindrome[A](s: List[A]): Option[List[A]] =
    if (s == s.reverse) Some(s) else None
}
