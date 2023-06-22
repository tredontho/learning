package dev.tredontho.leetcode.problem

import dev.tredontho.leetcode.Problem

object P0005 extends Problem[String, String] {

  override def name: String = "Longest Palindromic Substring"

  override def description: String =
    "Given a string s, return the longest palindromic substring in s."

  override def solve(input: String): String = {
    // Still too slow
    // mapTails(input.toList, (x: List[Char]) => x.inits.map(palindrome[Char]).flatten).flatten.filter(_.nonEmpty).maxBy(_.length).mkString
    // mapTailsFilter(input.toList, (x: List[Option[_]]) => ,(x: List[Char]) => x.inits.map(palindrome[Char])).flatten.flatten.maxBy(_.length).mkString
    // mapTailsInits(input.toList, (x: List[Char]) => palindrome[Char](x)).flatten.filter(_.nonEmpty).maxBy(_.length).mkString
    val xs = input.toList
    val palindromes = (for {
      ys <- xs.tails
      zs <- ys.inits
      if palindrome(zs).isDefined
    } yield (zs)).filter(_.nonEmpty)
    palindromes.maxBy(_.length).mkString
  }

  def mapTails[A, B](xs: List[A], f: List[A] => B): List[B] = {
    def go(xs: List[A], acc: List[B]): List[B] = xs match {
      case Nil => acc
      case _ => go(xs.tail, f(xs) :: acc)

    }
    go(xs, Nil).reverse
  }

  def mapTailsFilter[A, B](xs: List[A], p: B => Boolean, f: List[A] => B): List[B] = {
    def go(xs: List[A], acc: List[B]): List[B] = xs match {
      case Nil => acc
      case _ => {
        val res = f(xs)
        if (p(res)) go(xs.tail, res :: acc) else go(xs.tail, acc)
      }
    }
    go(xs, Nil).reverse
  }

  def palindrome[A](s: List[A]): Option[List[A]] =
    if (s == s.reverse) Some(s) else None

  /** What I want
    *
    * f(xs, yss, xs -> "racecar a" and "a racecar" <- ys ^ ^ x y Return if x is
    * empty Return Compare x with y. if unequal re
    */

}
