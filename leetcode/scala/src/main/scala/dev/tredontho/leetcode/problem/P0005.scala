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

  def shouldLog: Boolean = true

  def log(msg: String) = if (shouldLog) println(msg)

  def p(msg: String) = log(msg)

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
  ): String = {
    p("=== NEW ITERATION ===")
    q match {
      case Nil => {
        p(s"Queue was empty")
        if (currentWord.length <= 1) {
          p(s"$currentWord is too small to continue, returning $acc")
          acc
        } else {
          p(s"Iterating on $currentWord")
          helper(
            currentWord.inits.toList,
            currentWord.tail,
            accLength,
            acc
          )
        }
      }
      case (x :: xs) => {
        p(s"Queue was not empty. Head: $x")
        p(s"currentWord is $currentWord")
        val xLength = x.length
        if (xLength <= accLength) {
          p(s"$x is smaller than $acc, skipping")
          helper(xs, currentWord, accLength, acc)
        } else if (isPalindrome(x)) {
          p(
            s"$x is a palindrome and is longer than $acc, iterating on ${currentWord.tail}"
          )
          helper(currentWord.inits.toList, currentWord.tail, xLength, x)
        } else {
          p(s"$x is not a palindrome, skipping")
          helper(xs, currentWord, accLength, acc)
        }
      }
    }
  }
}
