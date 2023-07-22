{-# LANGUAGE OverloadedStrings #-}

module Codeforces.Problem.P0001B (p0001B, main) where

import Codeforces.Problem (Problem (..))
import Data.Char (chr, ord)
import qualified Data.Text as T
import Data.Void (Void)
import Text.Megaparsec (Parsec)

p0001B :: Problem String String
p0001B =
  Problem
    { title = "Spreadsheets"
    , description =
        "In the popular spreadsheets systems (for example, in Excel) the following\
        \ numeration of columns is used. The first column has number A, the second\
        \ - number B, etc. till column 26 that is marked by Z. Then there are\
        \ two-letter numbers: column 27 has number AA, 28 - AB, column 52 is marked\
        \ by AZ. After ZZ there follow three-letter numbers, etc.\
        \ \
        \ The rows are marked by integer numbers starting with 1. The cell name is\
        \ the concatenation of the column and the row numbers. For example, BC23 is\
        \ the name for the cell that is in column 55, row 23.\
        \ \
        \ Sometimes another numeration system is used: RXCY, where X and Y are\
        \ integer numbers, showing the column and the row numbers respectfully.\
        \ For instance, R23C55 is the cell from the previous example.\
        \ \
        \ Your task is to write a program that reads the given sequence of cell\
        \ coordinates and produce each item written according to the rules of\
        \ another numeration system.\
        \ Input\
        \ \
        \ The first line of the input contains integer number n (1 ≤ n ≤ 105), the\
        \ number of coordinates in the test. Then there follow n lines, each of\
        \ them contains coordinates. All the coordinates are correct, there are no\
        \ cells with the column and/or the row numbers larger than 106.\
        \ \
        \ Output\
        \ \
        \ Write n lines, each line should contain a cell coordinates in the other\
        \ numeration system."
    , solve = undefined
    }

type LetterColumn = T.Text
type Row = Int
type Column = Int

{- Parsing will have the edge case that if the string starts with 'R', it could
 - be interpreted as either column 18 or the start of a RXCY format
 -}
type Parser = Parsec Void T.Text

coordinateParser :: Parser Coordinate
coordinateParser = undefined

data Coordinate = LetterColumns LetterColumn Row | RC Row Column

-- | Changes the format of the coordinate from one type to the other
switchFormat :: Coordinate -> Coordinate
switchFormat (LetterColumns c r) = RC r $ letterToNumeric c

prettyPrint :: Coordinate -> T.Text
prettyPrint (LetterColumns c r) = c <> showT r
prettyPrint (RC r c) = "R" <> showT r <> "C" <> showT c

showT :: (Show a) => a -> T.Text
showT = T.pack . show

letterToNumeric :: LetterColumn -> Column
letterToNumeric lc = go 0 0 $ T.unpack . T.reverse $ lc
 where
  go :: Int -> Int -> String -> Int
  go _ acc [] = acc
  go power acc (x : xs) = go (power + 1) (acc + (1 + ord x - ord 'A') * 26 ^ power) xs

numericToLetter :: Column -> LetterColumn
numericToLetter c = T.pack $ go [] c
 where
  go :: String -> Int -> String
  go acc n
    | n <= 0 = acc
    | otherwise = go (chr (ord 'A' + (n - 1) `rem` 26) : acc) ((n - 1) `div` 26)

main :: IO ()
main = do
  undefined
