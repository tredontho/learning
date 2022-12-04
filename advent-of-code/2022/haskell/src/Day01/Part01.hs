{-# LANGUAGE TypeApplications #-}
module Day01.Part01 where

import Util (splitIntoGroups)

countCalories :: [[Int]] -> [Int]
countCalories = map sum

solution :: String -> Int
solution = maximum . countCalories . map (map $ read @Int) . splitIntoGroups null . lines

solve :: FilePath -> IO String
solve file = do
  input <- readFile file
  pure . show $ solution input

