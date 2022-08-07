package chapter2

object Chapter2 extends App {
  def fib(n: Int): Int = {
    def go(n: Int, x: Int, y: Int): Int =
      if (n <= 0) x
      else go(n - 1, y, x + y)

    go(n, 0, 1)
  }
}
