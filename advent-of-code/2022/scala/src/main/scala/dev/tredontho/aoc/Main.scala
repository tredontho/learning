package dev.tredontho.aoc

object Main extends App {
  run(Day01)

  def run[A, B, C, D](day: Day[A, B, C, D]): (C, D) = {
    val rawInput = day.loadFile(s"data/${day.name}.txt")
    val solution = day.solution
    val parsedInput = solution.parser(rawInput)
    val partOneSolution = solution.partOneSolver(parsedInput)
    val partTwoSolution = solution.partTwoSolver(parsedInput)
    print(s"${day.name}: ($partOneSolution, $partTwoSolution)")
    (partOneSolution, partTwoSolution)
  }
}
