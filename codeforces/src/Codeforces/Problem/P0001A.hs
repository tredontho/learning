{-# LANGUAGE OverloadedStrings #-}
module Codeforces.Problem.P0001A(p0001A) where

import Codeforces.Problem(Problem(..))

p0001A :: Problem (Int, Int, Int) Int
p0001A = Problem
  { title = "Theater Square"
  , description =  "Theatre Square in the capital city of Berland has a\
                   \ rectangular shape with the size n × m meters. On the\
                   \ occasion of the city's anniversary, a decision was taken\
                   \ to pave the Square with square granite flagstones. Each\
                   \ flagstone is of the size a × a.\n\n\
                   \ What is the least number of flagstones needed to pave the\
                   \ Square? It's allowed to cover the surface larger than the\
                   \ Theatre Square, but the Square has to be covered. It's not\
                   \ allowed to break the flagstones. The sides of flagstones\
                   \ should be parallel to the sides of the Square.\n\
                   \ Input: The input contains three positive integer numbers\
                   \ in the first line: n,  m and a (1 ≤  n, m, a ≤ 109).\
                   \ Output: Write the needed number of flagstones."
  , solve = \(n,m,a) -> ceiling ((fromIntegral n / fromIntegral a) :: Double) * ceiling ((fromIntegral m / fromIntegral a) :: Double)
  }
