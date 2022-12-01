{-# LANGUAGE TypeApplications #-}
module Day01 where

import Data.List (sortBy)

countCalories :: [[Int]] -> [Int]
countCalories = map sum

part1Solution :: String -> Int
part1Solution = maximum . countCalories . map (map $ read @Int) . splitIntoGroups null . lines

solvePart1 :: FilePath -> IO ()
solvePart1 file = do
  input <- readFile file
  print . show $ part1Solution input

-- Not sure if this is in a library
-- Takes a list of elements and splits into groups around some value
splitIntoGroups :: (a -> Bool) -> [a] -> [[a]]
splitIntoGroups _ [] = []
splitIntoGroups p xs = group : splitIntoGroups p rest
  where
    (group, rest) = case break p xs of
      (y, _ : ys) -> (y, ys)
      (y, []) -> (y, [])

part2Solution :: String -> Int
part2Solution = sum . take 3 . sortBy (flip compare) . countCalories . map (map $ read @Int) . splitIntoGroups null . lines

solvePart2 :: FilePath -> IO ()
solvePart2 file = do
  input <- readFile file
  print . show $ part2Solution input
