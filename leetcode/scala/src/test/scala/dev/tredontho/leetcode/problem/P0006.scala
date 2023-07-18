package dev.tredontho.leetcode.problem

import weaver.SimpleIOSuite

object P0006Suite extends SimpleIOSuite {

  pureTest("numRows = 1") {
    expect(P0006.solve(("thisisatest", 1)) == "thisisatest")
  }

  pureTest("numRows = 2") {
    expect(P0006.solve(("thisisatest", 2)) == "tiiaethssts")
  }

  pureTest("numRows = 3") {
    expect(P0006.solve(("PAYPALISHIRING", 3)) == "PAHNAPLSIIGYIR")
  }

  pureTest("numRows = 4") {
    expect(P0006.solve(("PAYPALISHIRING", 4)) == "PINALSIGYAHRPI")
  }

  pureTest("word length is same as numRows") {
    expect(P0006.solve(("P", 1)) == "P") and
      expect(P0006.solve(("PA", 2)) == "PA") and
      expect(P0006.solve(("PAY", 3)) == "PAY") and
      expect(P0006.solve(("PAYP", 4)) == "PAYP")
    expect(P0006.solve(("PAYPA", 5)) == "PAYPA")
    expect(P0006.solve(("PAYPAL", 6)) == "PAYPAL")
  }

  pureTest("word of length 1") {
    expect(P0006.solve(("P", 1)) == "P") and
      expect(P0006.solve(("P", 2)) == "P") and
      expect(P0006.solve(("P", 3)) == "P") and
      expect(P0006.solve(("P", 4)) == "P")
  }

  pureTest("word of length 2") {
    expect(P0006.solve(("PA", 1)) == "PA") and
      expect(P0006.solve(("PA", 2)) == "PA") and
      expect(P0006.solve(("PA", 3)) == "PA") and
      expect(P0006.solve(("PA", 4)) == "PA")
  }

}
