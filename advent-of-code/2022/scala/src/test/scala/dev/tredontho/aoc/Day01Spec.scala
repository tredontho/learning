package dev.tredontho.aoc

import org.scalatest.flatspec.AnyFlatSpec

class Day01Spec extends AnyFlatSpec {
  val sampleInput = """1000
    |2000
    |3000
    |
    |4000
    |
    |5000
    |6000
    |
    |7000
    |8000
    |9000
    |
    |10000""".stripMargin

  "The part one solution with the sample input" should "equal 24000" in {

    assert(
      Day01.solution.partOneSolver(
        Day01.solution.parser(sampleInput.split("\n").toList)
      ) == 24000
    )
  }

  "The part two solution with the sample input" should "equal 45000" in {

    assert(
      Day01.solution.partTwoSolver(
        Day01.solution.parser(sampleInput.split("\n").toList)
      ) == 45000
    )
  }
}
