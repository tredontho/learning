package dev.tredontho.leetcode.problem

import dev.tredontho.leetcode.Problem

import scala.collection.immutable.Queue

object P0005 extends Problem[String, String] {

  override def name: String = "Longest Palindromic Substring"

  override def description: String =
    "Given a string s, return the longest palindromic substring in s."

  override def solve(input: String): String = {
    helper(Queue(input), _.init)
  }

  /*
   * No more lists, keep it as strings
   * Can we cut the substrings in half for palindrome check?
   *
   * Check the string:
   *   If it's a palindrome, we're done
   *   If it's not, check the 2 strings formed by removing the head and last elements, respectively
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
  def helper(q: Queue[String], next: String => String): String = {
    println(s"q.length = ${q.length}")
    if (q.isEmpty) ""
    else {
      val (x, newQueue) = q.dequeue
      if (isPalindrome(x)) x
      else helper(newQueue.enqueue(x.init).enqueue(x.tail), next)
    }
  }

}
