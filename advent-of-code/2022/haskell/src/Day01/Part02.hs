{-# LANGUAGE TypeApplications #-}
module Day01.Part02 where

import Day01.Part01 hiding (solve, solution)
import Util (splitIntoGroups)

import Data.List (sortBy)

solution :: String -> Int
solution = sum . take 3 . sortBy (flip compare) . countCalories . map (map $ read @Int) . splitIntoGroups null . lines

solve :: FilePath -> IO String
solve file = do
  input <- readFile file
  pure . show $ solution input
