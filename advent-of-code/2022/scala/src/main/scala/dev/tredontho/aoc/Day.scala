package dev.tredontho.aoc

trait Day[Input, PartOneOutput, PartTwoOutput] {
  def solution: DaySolution[Input, PartOneOutput, PartTwoOutput]
}

// Each Day will consist of some means to parse the input, a method for taking
// the parsed input and creating a solution for part 1, and a method for taking
// the parsed input and creating a solution for part 2.

case class DaySolution[Input, PartOneOutput, PartTwoOutput](
    parser: String => Input,
    partOneSolver: Input => PartOneOutput,
    partTwoSolver: Input => PartTwoOutput
)
