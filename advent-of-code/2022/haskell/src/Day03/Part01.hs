module Day03.Part01 where

import Data.Char (ord)
import Data.List (intersect)

solve :: FilePath -> IO String
solve file = do
    input <- readFile file
    pure . show $ solution input

solution :: String -> Int
solution = sum . map (charToPriority . head . uncurry intersect . splitInTwain) . lines

splitInTwain :: [a] -> ([a], [a])
splitInTwain xs = splitAt mid xs
  where
    mid = length xs `div` 2

charToPriority :: Char -> Int
charToPriority x
    | 'a' <= x && x <= 'z' = ord x - ord 'a' + 1
    | 'A' <= x && x <= 'Z' = ord x - ord 'A' + 27
    | otherwise = error $ "Invalid char encountered: " ++ show x
