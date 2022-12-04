module Day02.Part02 where

import Data.List (find)
import Data.Maybe (fromJust)
import Day02.Part01 (Shape (..), beats, charToShape, parseLine, scoreShape)

solve :: FilePath -> IO String
solve file = do
    input <- readFile file
    pure . show $ solution input

solution :: String -> Int
solution = sum . map (scoreRound . parseLine) . lines

scoreRound :: (Char, Char) -> Int
scoreRound (opponent, resultIndicator) = scoreResult result + scoreShape play
  where
    result = charToResult resultIndicator
    play = choosePlay result (charToShape opponent)

data Result = Win | Loss | Draw deriving (Eq)

scoreResult :: Result -> Int
scoreResult Win = 6
scoreResult Draw = 3
scoreResult Loss = 0

charToResult :: Char -> Result
charToResult 'X' = Loss
charToResult 'Y' = Draw
charToResult 'Z' = Win
charToResult x = error $ "Illegal character: " <> show x

choosePlay :: Result -> Shape -> Shape
choosePlay Draw x = x
choosePlay Loss x = fromJust $ find (x `beats`) [Rock, Paper, Scissors]
choosePlay Win x = fromJust $ find (`beats` x) [Rock, Paper, Scissors]
