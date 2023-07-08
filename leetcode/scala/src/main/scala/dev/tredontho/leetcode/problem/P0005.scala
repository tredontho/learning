package dev.tredontho.leetcode.problem

import dev.tredontho.leetcode.Problem

object P0005 extends Problem[String, String] {

  override def name: String = "Longest Palindromic Substring"

  override def description: String =
    "Given a string s, return the longest palindromic substring in s."

  override def solve(input: String): String = {
    val l = input.toList
    l.tails
      .filter(_.nonEmpty)
      .map { xs =>
        xs.inits
          .filter(w => w.nonEmpty && palindrome(w).isDefined)
          .maxBy(_.length)
      }
      .maxBy(_.length)
      .mkString
  }

  def idk[A](l: List[A]): List[A] = {
    l.tails
      .filter(_.nonEmpty)
      .map { xs =>
        xs.inits
          .filter(w => w.nonEmpty && palindrome(w).isDefined)
          .maxBy(_.length)
      }
      .maxBy(_.length)
  }

  def idk2[A](l: List[A]): List[A] = {
    l.tails
      .filter(_.nonEmpty)
      .map(
        _.inits
          .filter(_.nonEmpty)
          .reduceRight((x, y) =>
            if (x.length > y.length && palindrome(x).isDefined) x else y
          )
      )
      .toList
      .maxBy(_.length)
  }

  def longestPalindrome[A](l: List[List[A]]): List[A] = {
    l.reduceRight((x, y) =>
      if (x.length > y.length && palindrome(x).isDefined) x else y
    )
  }

  def palindrome[A](s: List[A]): Option[List[A]] =
    if (s == s.reverse) Some(s) else None
}
