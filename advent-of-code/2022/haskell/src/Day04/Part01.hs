{-# LANGUAGE TypeApplications #-}

module Day04.Part01 where

import Data.Void (Void)
import Text.Megaparsec
import Text.Megaparsec.Char (char, digitChar)

solve :: FilePath -> IO String
solve file = do
    input <- readFile file
    pure . show $ solution input

solution :: String -> Int
solution = length . filter (\(x, y) -> x `fullyContains` y || y `fullyContains` x) . map parseLine . lines

type Parser = Parsec Void String

lineParser :: Parser (Range, Range)
lineParser = do
    l1 <- some digitChar
    _ <- char '-'
    u1 <- some digitChar
    _ <- char ','
    l2 <- some digitChar
    _ <- char '-'
    u2 <- some digitChar
    return (Range (read @Int l1) (read @Int u1), Range (read @Int l2) (read @Int u2))

parseLine :: String -> (Range, Range)
parseLine = either (error . errorBundlePretty) id . parse lineParser ""

data Range = Range
    { lowerBound :: Int
    , upperBound :: Int
    }

-- | `fullyContains x y` returns `True` if all of y is contained in x
fullyContains :: Range -> Range -> Bool
fullyContains x y = lowerBound x <= lowerBound y && upperBound x >= upperBound y
