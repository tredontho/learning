module Codeforces.Problem.P0001ATest where

import Codeforces.Problem (Problem (solve))
import Codeforces.Problem.P0001A
import Test.HUnit

tests :: Test
tests = test [test1]

test1 :: Test
test1 = TestCase (assertEqual "example" 4 (solve p0001A (6, 6, 4)))
