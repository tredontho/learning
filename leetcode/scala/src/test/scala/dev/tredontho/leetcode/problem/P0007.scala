package dev.tredontho.leetcode.problem

import weaver.SimpleIOSuite

object P0007Suite extends SimpleIOSuite {

  pureTest("negative number") {
    expect(P0007.solve(-123) == -321)
  }

  pureTest("number ending in 0") {
    expect(P0007.solve(100) == 1)
  }

  pureTest("number which would exceed max int size when reversed") {
    expect(P0007.solve(1534236469) == 0)
  }
}
