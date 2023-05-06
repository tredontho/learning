package chapter3

enum List[+A]:
  case Nil
  case Cons(head: A, tail: List[A])

object List {
  def sum(ints: List[Int]): Int = ints match
    case Nil => 0
    case Cons(x, xs) => x + sum(xs)

  def product(ds: List[Double]): Double = ds match
    case Nil => 1.0
    case Cons(0.0, _) => 0.0
    case Cons(x, xs) => x * product(xs)

  def apply[A](as: A*): List[A] =
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail*))

  def tail[A](l: List[A]): List[A] = l match
    case Cons(_, t) => t
    case Nil => Nil

  def setHead[A](l: List[A], a: A): List[A] = l match
    case Cons(_, t) => Cons(a, t)
    case Nil => Cons(a, Nil)

  @scala.annotation.tailrec
  def drop[A](l: List[A], n: Int): List[A] = l match
    case Nil => Nil
    case Cons(_, xs) => if (n > 0) drop(xs, n - 1) else l

  @scala.annotation.tailrec
  def dropWhile[A](l: List[A], f: A => Boolean): List[A] = l match
    case Nil => Nil
    case Cons(x, xs) => if (f(x)) dropWhile(xs, f) else l

  def init[A](l: List[A]): List[A] =
    l match {
      case Nil => Nil
      case Cons(_, Nil) => Nil
      case Cons(x, xs) => Cons(x, init(xs))
    }

  // Provided by the book
  def foldRight[A, B](as: List[A], acc: B, f: (A, B) => B): B = as match
    case Nil => acc
    case Cons(x, xs) => f(x, foldRight(xs, acc, f))

  /* 3.7
   * Product cannot be implemented with short-circuiting via `foldRight` due to
   * the fact that foldRight does not actually call `f` until it reaches the end
   * of the list
   */

  /* 3.8
   * Passing Nil and Cons to `foldRight` will result in the same input list
   * being returned
   */

  /* 3.9
   * Compute the length of a list using foldRight
   */
  def lengthViaFoldRight[A](as: List[A]): Int =
    foldRight[A, Int](as, 0, (_, acc) => acc + 1)

  /* 3.10
   * Write tail-recursive foldLeft
   */
  @scala.annotation.tailrec
  def foldLeft[A, B](as: List[A], acc: B, f: (B, A) => B): B = as match
    case Nil => acc
    case Cons(x, xs) => foldLeft(xs, f(acc, x), f)

  /* 3.11
   * Write `sum`, `product`, `length` using `foldLeft`
   */

  def sumViaFoldLeft(as: List[Int]) = foldLeft[Int, Int](as, 0, _ + _)
  def productViaFoldLeft(as: List[Int]) = foldLeft[Int, Int](as, 1, _ * _)
  def lengthViaFoldLeft[A](as: List[A]) =
    foldLeft[A, Int](as, 1, (acc, _) => acc + 1)

  /* Write a function that returns the reverse of a list (i.e., given List(1,2,3),
   * it returns List(3,2,1)). See if you can write it using a fold.
   */
  def reverse[A](as: List[A]): List[A] =
    foldLeft[A, List[A]](as, Nil, (acc, x) => Cons(x, acc))
}
