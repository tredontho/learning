package dev.tredontho.leetcode.problem

import dev.tredontho.leetcode.Problem

import scala.collection.immutable.Queue

object P0005 extends Problem[String, String] {

  override def name: String = "Longest Palindromic Substring"

  override def description: String =
    "Given a string s, return the longest palindromic substring in s."

  override def solve(input: String): String = {
    helper(LazyList(input))
  }

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

  def isPalindrome(s: String): Boolean = {
    if (s.nonEmpty) {
      val l = s.length / 2
      val (h, t) = s.splitAt(l)
      h == t.reverse.take(l) // handles odd-length strings
    } else false
  }

  @annotation.tailrec
  def helper(q: LazyList[String]): String = {
    if (q.isEmpty) ""
    else {
      val (x, newQueue) = (q.head, q.tail)
      if (x.isEmpty) helper(newQueue)
      else if (isPalindrome(x)) x
      else if (x.length == 1) helper(newQueue)
      else helper(newQueue.appendedAll(LazyList(x.init, x.tail)))
    }
  }

}
