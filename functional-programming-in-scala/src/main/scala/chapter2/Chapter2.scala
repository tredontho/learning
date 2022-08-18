package chapter2

object Chapter2 extends App {
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
}
