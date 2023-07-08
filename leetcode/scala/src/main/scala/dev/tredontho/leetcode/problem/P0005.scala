package dev.tredontho.leetcode.problem

import dev.tredontho.leetcode.Problem

object P0005 extends Problem[String, String] {

  override def name: String = "Longest Palindromic Substring"

  override def description: String =
    "Given a string s, return the longest palindromic substring in s."

  override def solve(input: String): String = {
    input.tails.map(x => longestPalindrome(x.inits)).maxBy(_.length)
  }

  /*
   * No more lists, keep it as strings
   * Can we cut the substrings in half for palindrome check?
   */

  def isPalindrome(s: String): Boolean = {
    if (s.nonEmpty) {
      val l = s.length / 2
      val (h, t) = s.splitAt(l)
      h == t.reverse.take(l) // handles odd-length strings
    } else false
  }

  def longestPalindrome[F[String] <: IterableOnce[String]](
      xs: F[String]
  ): String = {
    xs.reduceRight((x, y) =>
      if (x.length > y.length && isPalindrome(x)) x else y
    )
  }

}
