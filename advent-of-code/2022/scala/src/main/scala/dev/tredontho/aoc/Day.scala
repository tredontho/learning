package dev.tredontho.aoc

trait Day[RawInput, ParsedInput, PartOneOutput, PartTwoOutput] {
  def solution: DaySolution[RawInput, ParsedInput, PartOneOutput, PartTwoOutput]
  def loadFile(fileName: String): RawInput

  def name: String
}

/** DefaultDay will treat the output type for both parts as the same, as this is
  * likely the common case
  */
trait DefaultDay[A, B, C] extends Day[A, B, C, C]

case class DaySolution[RawInput, ParsedInput, PartOneOutput, PartTwoOutput](
    parser: RawInput => ParsedInput,
    partOneSolver: ParsedInput => PartOneOutput,
    partTwoSolver: ParsedInput => PartTwoOutput
)
