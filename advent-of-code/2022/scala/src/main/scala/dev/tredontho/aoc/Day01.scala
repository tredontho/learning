package dev.tredontho.aoc

import Common._

case class Elf(calories: Int)

object Day01 extends DefaultDay[List[String], List[Elf], Int] {
  override def solution: DaySolution[List[String], List[Elf], Int, Int] = {
    val parser = (lines: List[String]) => {
      sepBy((x: String) => (x.isEmpty))(lines)
        .map(_.map(_.toInt))
        .map(x => Elf(x.sum))
    }
    val partOneSolver = (elves: List[Elf]) => {
      elves.sortWith((a, b) => a.calories > b.calories).head.calories
    }
    val partTwoSolver = (elves: List[Elf]) => {
      print(elves)
      elves
        .sortWith((a, b) => a.calories > b.calories)
        .take(3)
        .map(_.calories)
        .sum
    }
    DaySolution(parser, partOneSolver, partTwoSolver)
  }

  override def loadFile(filename: String): List[String] = loadLines(filename)
  override val name: String = "day01"

  /** Takes a list and groups it into chunks separated by elemetnts for which
    * the predicate evalutes to True (removing said element)
    */

  def sepBy[A](pred: A => Boolean)(list: List[A]): List[List[A]] = {

    @scala.annotation.tailrec
    def go(xs: List[A], acc: List[List[A]]): List[List[A]] = {
      xs match {
        case Nil => acc
        case _ => {
          val (chunk, rest) = xs.span(pred.andThen(!_))
          rest match {
            case Nil => acc :+ chunk
            case _ => go(rest.tail, acc :+ chunk)
          }
        }
      }
    }
    go(list, Nil)
  }
}
