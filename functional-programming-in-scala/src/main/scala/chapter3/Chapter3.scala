package chapter3

sealed trait List[+A]
case object Nil extends List[Nothing]
case class Cons[+A](head: A, tail: List[A]) extends List[A]

object List {
  def sum(ints: List[Int]): Int = ints match {
    case Nil => 0
    case Cons(x,xs) => x + sum(xs)
  }

  def product(ds: List[Double]): Double = ds match {
    case Nil => 1.0
    case Cons(0.0, _) => 0.0
    case Cons(x, xs) => x * product(xs)
  }

  def apply[A](as: A*): List[A] =
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))

  def tail[A](l: List[A]): List[A] = l match {
    case Cons(_, t) => t
    case Nil => Nil
  }

  def setHead[A](l: List[A], a: A): List[A] = l match {
    case Cons(_, t) => Cons(a, t)
    case Nil => Cons(a, Nil)
  }

  @scala.annotation.tailrec
  def drop[A](l: List[A], n: Int): List[A] = l match {
    case Nil => Nil
    case Cons(_, xs) => if (n > 0) drop(xs, n - 1) else l
  }

  @scala.annotation.tailrec
  def dropWhile[A](l: List[A], f: A => Boolean): List[A] = l match {
    case Nil => Nil
    case Cons(x, xs) => if (f(x)) dropWhile(xs, f) else l
  }

  def init[A](l: List[A]): List[A] = {
    l match {
      case Nil => Nil
      case Cons(_, Nil) => Nil
      case Cons(x, xs) => Cons(x, init(xs))
    }
    // def go(l: List[A], acc: List[A]): List[A] = l match {
    //   case Cons(_, Nil) => Cons(acc, Nil)
    //   case Cons(x, xs) => go(xs, Cons(acc, x)
    // }
  }

}
