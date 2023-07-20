module Codeforces.Problem(Problem(..)) where
import Data.Text (Text)

data Problem input output = Problem
  { title :: Text
  , description :: Text
  , solve :: input -> output
  }
