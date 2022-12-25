package dev.tredontho.aoc

import scala.io.Source

/** Common behavior each day's solution could use
  */
object Common {

  /** Identify function, idk if scala has something like this
    */
  def id[A](x: A) = x

  /** Loads the contents of a file into a list containing each line of that file
    */
  def loadLines(filename: String): List[String] = {
    val bufferedSource = Source.fromFile(filename)
    val lines = bufferedSource.getLines.toList
    bufferedSource.close
    lines
  }

  /** Loads the contents of a file, returning the full contents. The lines of
    * the input will have the newline separator replaced with the `separator`
    * argument, which defaults to `"\n"`
    */
  def load(filename: String, separator: String = "\n"): String = {
    val bufferedSource = Source.fromFile(filename)
    val contents = bufferedSource.getLines.mkString(separator)
    bufferedSource.close
    contents
  }
}
