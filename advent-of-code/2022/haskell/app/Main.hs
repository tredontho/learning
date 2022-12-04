module Main where

import qualified Day01.Part01 as D01P01
import qualified Day01.Part02 as D01P02

main :: IO ()
main = do
    day01

day01 :: IO ()
day01 = do
    putStrLn "Day 01"
    putStrLn . ("Part 01: " <>) =<< D01P01.solve "data/Day01-01.txt"
    putStrLn . ("Part 02: " <>) =<< D01P02.solve "data/Day01-01.txt"
