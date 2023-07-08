package dev.tredontho.leetcode.problem

import dev.tredontho.leetcode.Problem

import scala.collection.immutable.Queue

object P0005 extends Problem[String, String] {

  override def name: String = "Longest Palindromic Substring"

  override def description: String =
    "Given a string s, return the longest palindromic substring in s."

  override def solve(input: String): String = {
    helper(List(input), input, 0, "")
  }

  def log: Boolean = true

  /*
   * No more lists, keep it as strings
   * Can we cut the substrings in half for palindrome check?
   *
   * We start with a list containing a single string, the input
   * Check the list:
   *   If it's empty, return ""
   *   Take the first element:
   *     If it's a palindrome, we're done
   *     If it's not, add the 2 strings formed by removing the head and last elements, respectively, to the end of the list (i.e. treat it as a Queue)
   *   Repeat
   */

  /** Two phase pass:
    *
    * We have an initial string, `s`, and a list of candidates, `xs` Check if s
    * is palindrome Yes -> add `s` to `xs` No -> check `s.init`
    */

  def isPalindrome(s: String): Boolean = {
    if (s.nonEmpty) {
      val l = s.length / 2
      val (h, t) = s.splitAt(l)
      h == t.reverse.take(l) // handles odd-length strings
    } else false
  }

  @annotation.tailrec
  def helper(
      q: List[String],
      currentWord: String,
      accLength: Int = 0,
      acc: String = ""
  ): String = q match {
    case Nil => {
      if (currentWord.length <= 1) acc
      else
        helper(currentWord.tail.inits.toList, currentWord.tail, accLength, acc)
    }
    case (x :: xs) => {
      val xLength = x.length
      if (xLength <= accLength) helper(xs, currentWord, accLength, acc)
      else if (isPalindrome(x))
        helper(currentWord.tail.inits.toList, currentWord.tail, xLength, x)
      else helper(xs, currentWord, accLength, acc)
    }
  }
}
