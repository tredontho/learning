package dev.tredontho.aoc

trait Day[RawInput, ParsedInput, PartOneOutput, PartTwoOutput] {
  def solution: DaySolution[RawInput, ParsedInput, PartOneOutput, PartTwoOutput]
  def loadFile(fileName: String): RawInput
}

/** In early problems, file contents may not need any complex parsing, and the
  * output types for parts one and two will be the same
  */
trait DefaultDay[A, B] extends Day[A, A, B, B]

case class DaySolution[RawInput, Input, PartOneOutput, PartTwoOutput](
    parser: RawInput => Input,
    partOneSolver: Input => PartOneOutput,
    partTwoSolver: Input => PartTwoOutput
)
