{-# LANGUAGE OverloadedStrings #-}
module Day01 where

import Data.List (findIndex)
import Data.Text (Text)
import qualified Data.Text as T
import qualified Data.Text.IO as TIO
import Data.Text.Encoding (decodeUtf8)
import qualified Data.ByteString as BS
import Paths_aoc2015 (getDataFileName)

import Data.Functor ((<&>))
import Data.Maybe (fromJust)

day01, day01_1, day01_2 :: IO ()
day01 = do
  day01_1
  day01_2

day01_1 = do
  input <- (getDataFileName "day01.txt" >>= BS.readFile) <&> decodeUtf8
  print $ solve01_1 input

-- For the first part, it's straightforward to convert '(' to -1, ')' to 1, and then sum them all up
solve01_1 :: Text -> Int
solve01_1 = T.foldl' (\x y -> x + convert y) 0

day01_2 = do
  input <- getDataFileName "day01.txt" >>= TIO.readFile
  print $ solve01_2 input

-- For the second part, we really only care about when the value is first negative
-- Note: The list should start at index 1, so we have to add 1 to our result
solve01_2 :: Text -> Int
solve01_2 input = (+1) $ fromJust $ findIndex (< 0) $ scanl1 (+) $ map convert (T.unpack input)

convert :: Char -> Int
convert '(' = 1
convert ')' = -1
convert x = error $ "Didn't expect: " <> show x


