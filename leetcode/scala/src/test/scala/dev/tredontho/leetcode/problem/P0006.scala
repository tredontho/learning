package dev.tredontho.leetcode.problem

import weaver.SimpleIOSuite

object P0006Suite extends SimpleIOSuite {

  pureTest("numRows = 1") {
    expect(P0006.solve(("thisisatest",1)) == "thisisatest")
  }

  pureTest("numRows = 2") {
    expect(P0006.solve(("thisisatest",2)) == "tiiaethssts")
  }

  pureTest("numRows = 3") {
    expect(P0006.solve(("PAYPALISHIRING",3)) == "PAHNAPLSIIGYIR")
  }

}
