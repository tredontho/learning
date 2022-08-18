package chapter2

object Chapter2 {
  def fib(n: Int): Int = {
    def go(n: Int, x: Int, y: Int): Int =
      if (n <= 0) x
      else go(n - 1, y, x + y)

    go(n, 0, 1)
  }

  def isSorted[A](as: Array[A], gt: (A,A) => Boolean): Boolean = {
    def go(i: Int): Boolean =
      if (i + 1 >= as.length)
        true
      else
        if (gt(as(i),as(i + 1)))
          go(i + 1)
        else
          false
    go(0)
  }

  def curry[A, B, C](f: (A, B) => C): A => (B => C) = {
    (a: A) => b: B => f(a,b)
  }

  def uncurry[A, B, C](f: A => B => C): (A, B) => C = {
    (a: A, b: B) => f(a)(b)
  }

  def compose[A, B, C](f: B => C, g: A => B): A => C = {
    (a: A) => f(g(a))
  }
}

object Answers {
  def fib(n: Int): Int = {
    @annotation.tailrec
    def go(n: Int, current: Int, next: Int): Int =
      if (n == 0) current
      else go(n - 1, next, current + next)

    go(n, 0, 1)
  }

  def isSorted[A](as: Array[A], gt: (A,A) => Boolean): Boolean = {
    @annotation.tailrec
    def loop(n: Int): Boolean =
      if (n + 1 >= as.length) true
      else if (gt(as(n), as(n + 1))) false
      else loop(n + 1)
    loop(0)
  }

  def curry[A, B, C](f: (A, B) => C): A => (B => C) =
    a => b => f(a,b)

  def uncurry[A, B, C](f: A => B => C): (A, B) => C =
    (a, b) => f(a)(b)

  def compose[A, B, C](f: B => C, g: A => B): A => C =
    a => f(g(a))
}
