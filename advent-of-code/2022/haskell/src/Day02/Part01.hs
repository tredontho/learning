{-# LANGUAGE TypeApplications #-}

module Day02.Part01 where

import Data.Void (Void)
import Text.Megaparsec
import Text.Megaparsec.Char (spaceChar, upperChar)

solve :: FilePath -> IO String
solve file = do
    input <- readFile file
    pure . show $ solution input

solution :: String -> Int
solution = sum . map (scoreRound . parseLine) . lines

type Parser = Parsec Void String

lineParser :: Parser (Char, Char)
lineParser = do
    opponent <- upperChar
    _ <- spaceChar
    recommended <- upperChar
    return (opponent, recommended)

parseLine :: String -> (Char, Char)
parseLine = either (error . errorBundlePretty) id . parse lineParser ""

data Shape = Paper | Scissors | Rock deriving (Show, Eq)

charToShape :: Char -> Shape
charToShape 'A' = Rock
charToShape 'X' = Rock
charToShape 'B' = Paper
charToShape 'Y' = Paper
charToShape 'C' = Scissors
charToShape 'Z' = Scissors
charToShape x = error $ "Illegal character: " <> show x

scoreRound :: (Char, Char) -> Int
scoreRound (opponent, recommended) = scorePlay (charToShape opponent, charToShape recommended)

scorePlay :: (Shape, Shape) -> Int
scorePlay (opponent, recommended) = scoreResult (opponent, recommended) + scoreShape recommended

beats :: Shape -> Shape -> Bool
beats Rock Scissors = True
beats Scissors Paper = True
beats Paper Rock = True
beats _ _ = False

scoreResult :: (Shape, Shape) -> Int
scoreResult (opponent, recommended)
    | opponent == recommended = 3
    | opponent `beats` recommended = 0
    | otherwise = 6

scoreShape :: Shape -> Int
scoreShape Rock = 1
scoreShape Paper = 2
scoreShape Scissors = 3
