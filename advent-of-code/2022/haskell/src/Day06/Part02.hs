module Day06.Part02 where

import Day06.Part01 (findBeginning)

solve :: FilePath -> IO Int
solve file = do
    input <- readFile file
    pure $ solution input

solution :: String -> Int
solution = findBeginning 14
