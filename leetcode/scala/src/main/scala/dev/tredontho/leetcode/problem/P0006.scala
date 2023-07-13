package dev.tredontho.leetcode.problem

import dev.tredontho.leetcode.Problem

object P0006 extends Problem[(String, Int), String] {

  override def name: String = "Zigzag Conversion"

  override def description: String =
    """The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
      |
      |P   A   H   N
      |A P L S I I G
      |Y   I   R
      |And then read line by line: "PAHNAPLSIIGYIR"
      |
      |Write the code that will take a string and make this conversion given a number of rows:
      |
      |string convert(string s, int numRows);""".stripMargin

  override def solve(input: (String, Int)): String = ???

// Some example cases:
// numRows = 1
//   Don't do anything
// numRows = 2
//   even indices and then odd indices, i.e. BREAKINGBAD becomes BEKNBDRAIGA, visually
//   B E K N B D
//    R A I G A
//  numRows > 2
//    Now it gets complicated with diagonals, i.e. BREAKINGBAD for numRows = 3 would be
//    B   K   B
//    R A I G A
//    E   N   D
//
//    Can we find a pattern by looking at indices instead of letters?
//
//    [0,1,2,3,4,5,6,7,8,9] with numRows = 3 becomes
//
//    0 4 8
//    13579
//    2 6
//
//    [0,1,2,3,4,5,6,7,8,9] with numRows = 4 becomes
//
//    0  6
//    1 57
//    24 8
//    3  9
//
//    [0,1,2,3,4,5,6,7,8,9,a,b,c,d,e,f] with numRows = 5 becomes
//
//    0   8
//    1  79  f
//    2 6 a e
//    35  bd
//    4   c
//
//    So for n = 3, we have 3 lists:
//      [4*k | k in [0..] and 4*k < input.length]
//      [4*k + 1 | k in [0..] and 4*k + 1 < input.length] "zipped" with
//        [4*k - 1 | k in [1..] and 4 * k - 1 < input.length]
//      [4*k + 2 | k in [0..] and 4*k + 2 < input.length]
//     or alternatively
//
//      [2*(2*k)]
//      [2*k + 1]
//      [2*(2*k) + 2]
//
//      [2*2*k | k in [0..] and 4*k < input.length]
//      [2*k + 1 | k in [0..] and 4*k + 1 < input.length] "zipped" with
//        [4*k - 1 | k in [1..] and 4 * k - 1 < input.length]
//      [4*k + 2 | k in [0..] and 4*k + 2 < input.length]
//
//    for n = 4, we have 4 lists:
//      [6*k | k in [0..] and 6*k < input.length]
//      [6*k + 1 | k in [0..] and 6*k + 1 < input.length] "zipped" with
//        [6*k - 1 | k in [1..] and 6*k - 1 < input.length] "zipped" with
//      [6*k + 2 | k in [0..] and 6*k + 2 < input.length] "zipped" with
//        [6*k - 2 | k in [1..] and 6*k - 2 < input.length] "zipped" with
//      [6*k + 3 | k in [0..] and 6*k + 3 < input.length]

}
