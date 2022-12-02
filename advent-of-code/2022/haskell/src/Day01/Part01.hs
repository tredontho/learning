{-# LANGUAGE TypeApplications #-}
module Day01.Part01 where

countCalories :: [[Int]] -> [Int]
countCalories = map sum

solution :: String -> Int
solution = maximum . countCalories . map (map $ read @Int) . splitIntoGroups null . lines

solve :: FilePath -> IO String
solve file = do
  input <- readFile file
  pure . show $ solution input

-- Not sure if this is in a library
-- Takes a list of elements and splits into groups around some value
splitIntoGroups :: (a -> Bool) -> [a] -> [[a]]
splitIntoGroups _ [] = []
splitIntoGroups p xs = group : splitIntoGroups p rest
  where
    (group, rest) = case break p xs of
      (y, _ : ys) -> (y, ys)
      (y, []) -> (y, [])

