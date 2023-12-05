{-# LANGUAGE TypeApplications #-}
{-# LANGUAGE ScopedTypeVariables #-}
module Main where

import Data.Char (isDigit)
import Paths_aoc2023
import Text.Megaparsec
import Data.Void (Void)
import Text.Megaparsec.Char (digitChar, string, asciiChar)
import Data.Maybe (fromJust)

main :: IO ()
main = solveA

solveA :: IO ()
solveA = do
  inputs <- readInput
  print $ solutionA inputs

readInput :: IO [String]
readInput = do
  fileA <- getDataFileName "data/day01/a.txt"
  lines <$> readFile fileA

-- For each line, we want to extract the first digit, and the last digit seen
solutionA :: [String] -> Int
solutionA = sum . map ((\xs -> read @Int [head xs, last xs]) . digits)
  where
    digits = filter isDigit

-- parse each line
parseB :: String -> Int
parseB = (\x -> read @Int [head x, last x]) . fromJust . parseMaybe partBParser

partBParser :: Parser [Char]
partBParser = many (try digitParser <|> try textualParser)

type Parser = Parsec Void String

digitParser :: Parser Char
digitParser = digitChar

textualParser :: Parser Char
textualParser = textToDigit <$> tempParser
  where
    tempParser :: Parser String
    tempParser = string "one" <|> string "two" <|> string "three" <|> string "four" <|> string "five" <|> string "six" <|> string "seven" <|> string "eight" <|> string "nine" <|> try (((:[]) <$> asciiChar) *> tempParser)



textToDigit :: [Char] -> Char
textToDigit "one" = '1'
textToDigit "two" = '2'
textToDigit "three" = '3'
textToDigit "four" = '4'
textToDigit "five" = '5'
textToDigit "six" = '6'
textToDigit "seven" = '7'
textToDigit "eight" = '8'
textToDigit "nine" = '9'

textToDigit x = error $ "wtf how do I process " ++ show x
