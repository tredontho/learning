module Main where

import Day01 (day01, day01_1, day01_2)
import System.Environment (getArgs)


main :: IO ()
main = do
  args <- getArgs
  case args of
    "1" : "1" : _ -> day01_1
    "1" : "2" : _ -> day01_2
    "1" : _ -> day01
    _ -> error "None or invalid day number provided."
