package dev.tredontho.leetcode.problem

import dev.tredontho.leetcode.Problem
import scala.collection.immutable.HashSet

object P0003 extends Problem[String, Int] {

  override def name: String = "Longest Substring Without Repeating Characters"

  override def description: String =
    "Given a string s, find the length of the longest substring without repeating characters."

  override def solve(input: String): Int = {
    mapTails(input.toList, (s: List[Char]) => prefix(s).length).maxOption
      .getOrElse(0)
  }

  def mapTails[A, B](xs: List[A], f: List[A] => B): List[B] = {
    def go(xs: List[A], acc: List[B]): List[B] = xs match {
      case Nil => acc
      case _ => go(xs.tail, f(xs) :: acc)

    }
    go(xs, Nil).reverse
  }

  def prefix[A](s: List[A]): List[A] = {
    def go(s: List[A], seen: HashSet[A], acc: List[A]): List[A] = s match {
      case Nil => acc
      case x :: xs =>
        if (acc.isEmpty) go(xs, seen + x, List(x))
        else if (seen.contains(s.head)) acc
        else go(xs, seen + x, x :: acc)
    }
    go(s, HashSet.empty, Nil).reverse
  }

}
