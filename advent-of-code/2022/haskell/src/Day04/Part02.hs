module Day04.Part02 where

import Day04.Part01 (Range (..), parseLine)

solve :: FilePath -> IO String
solve file = do
    input <- readFile file
    pure . show $ solution input

solution :: String -> Int
solution = length . filter (uncurry overlaps) . map parseLine . lines

-- (a,b) overlaps with (c,d)
--
-- if a <= d and b >= c
-- or c <= b and d >= a
overlaps :: Range -> Range -> Bool
overlaps x y =
    (lowerBound x <= upperBound y && upperBound x >= lowerBound y)
        || (lowerBound y <= upperBound x && upperBound y >= lowerBound x)
