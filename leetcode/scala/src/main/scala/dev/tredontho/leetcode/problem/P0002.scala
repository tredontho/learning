package dev.tredontho.leetcode.problem

import dev.tredontho.leetcode.Problem
import P0002Helper._

object P0002Helper {
  class ListNode(_x: Int = 0, _next: ListNode = null) {
    var next: ListNode = _next
    var x: Int = _x

    override def toString: String = toList(this).toString
  }

  def toList(l: ListNode): List[Int] = {
    def go(node: ListNode, acc: List[Int]): List[Int] = node match {
      case null => acc
      case _ => go(node.next, node.x :: acc)
    }
    go(l, Nil).reverse
  }

  def fromList(l: List[Int]): ListNode = {
    def go(xs: List[Int], acc: ListNode): ListNode = xs match {
      case y :: ys => go(ys, new ListNode(y, acc))
      case Nil => acc
    }
    go(l.reverse, null)
  }
}

object P0002 extends Problem[(ListNode, ListNode), ListNode]{

  override def name: String = "Add Two Numbers"

  override def description: String = """
  |You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
  |
  |You may assume the two numbers do not contain any leading zero, except the number 0 itself.
  """.stripMargin.trim



  override def solve(input: (ListNode, ListNode)): ListNode = fromList(addLists(toList(input._1), toList(input._2)))

  def addLists(xs: List[Int], ys: List[Int]): List[Int] = {
    def go(xs: List[Int], ys: List[Int], carry: Int, acc: List[Int]): List[Int] = (xs, ys) match {
      case (Nil, Nil) => if (carry == 0) acc else carry :: acc
      case (x_ :: x_s, Nil) => go(x_s, Nil, (x_ + carry) / 10, (x_ + carry) % 10 :: acc)
      case (Nil, y_ :: y_s) => go(Nil, y_s, (y_ + carry) / 10, (y_ + carry) % 10 :: acc)
      case (x_ :: x_s, y_ :: y_s) => go(x_s, y_s, (x_ + y_ + carry) / 10, (x_ + y_ + carry) % 10 :: acc)
    }
    go(xs, ys, 0, Nil).reverse
  }



}
