module Day03.Part02 where

import Data.List (intersect)
import Day03.Part01 (charToPriority)

solve :: FilePath -> IO String
solve file = do
    input <- readFile file
    pure . show $ solution input

solution :: String -> Int
solution = sum . map (charToPriority . head . foldl1 intersect) . group 3 . lines

group :: Int -> [a] -> [[a]]
group _ [] = []
group n xs = first : group n rest
  where
    (first, rest) = splitAt n xs
