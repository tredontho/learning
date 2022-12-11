module Day06.Part01 where

import Data.List (sort)

solve :: FilePath -> IO Int
solve file = do
    input <- readFile file
    pure $ solution input

solution :: String -> Int
solution = findBeginning 4

{- | Finds the first subset of 4 characters which are all different, and reports
 the position of the last character in such a string from the beginning of the
 original string
-}
findBeginning :: Int -> [Char] -> Int
findBeginning n = go n
  where
    go :: Int -> [Char] -> Int
    go count xs
        | allDistinct (take n xs) = count
        | otherwise = go (count + 1) (tail xs)

allDistinct :: [Char] -> Bool
allDistinct xs = all (== False) $ zipWith (==) sorted (tail sorted)
  where
    sorted = sort xs
