module Main (main) where

import Test.HUnit
import qualified Codeforces.Problem.P0001ATest as P0001A
import System.Exit

main :: IO ()
main = do
  count <- runTestTT $ test [P0001A.tests]
  if errors count + failures count == 0
    then exitSuccess
    else exitFailure
