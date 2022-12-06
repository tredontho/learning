{-# LANGUAGE TypeApplications #-}

module Day05.Part01 where

import Data.Bifunctor (bimap)
import Data.Char (digitToInt)
import qualified Data.IntMap.Strict as M
import Data.List (transpose)
import Data.Void (Void)
import Text.Megaparsec
import Text.Megaparsec.Char (digitChar, spaceChar, string)
import Util (splitIntoGroups)

type Parser = Parsec Void String

instructionParser :: Parser Instruction
instructionParser = do
    skipCount 1 $ string "move"
    singleSpace
    q <- some digitChar
    singleSpace
    skipCount 1 $ string "from"
    singleSpace
    s <- digitChar
    singleSpace
    skipCount 1 $ string "to"
    singleSpace
    t <- digitChar
    return Instruction{quantity = read @Int q, source = digitToInt s, target = digitToInt t}

singleSpace :: Parser ()
singleSpace = skipCount 1 spaceChar

parseInstruction :: String -> Instruction
parseInstruction = either (error . errorBundlePretty) id . parse instructionParser ""

solve :: FilePath -> IO String
solve file = do
    input <- readFile file
    pure $ solution input

solution :: String -> String
solution = map head . M.elems . state . eval . uncurry Crates . bimap (M.fromList . map ((\(x : xs) -> (digitToInt x, reverse xs)) . filter (/= ' ')) . filter (\(x : _) -> '1' <= x && '9' >= x) . transpose . reverse) (map parseInstruction) . toPair . splitIntoGroups null . lines

toPair :: (Show a) => [[a]] -> ([a], [a])
toPair [x, y] = (x, y)
toPair xs = error $ "Invalid input: " ++ show xs

data Crates = Crates
    { state :: M.IntMap [Char]
    , instructions :: [Instruction]
    }

data Instruction = Instruction
    { quantity :: Int
    , source :: Int
    , target :: Int
    }

eval :: Crates -> Crates
eval x@Crates{instructions = []} = x
eval c = eval (evalStep c)

evalStep :: Crates -> Crates
evalStep x@Crates{instructions = []} = x
evalStep Crates{state = s, instructions = (Instruction{quantity = q, source = src, target = t} : is)} = Crates{state = updateState s q src t, instructions = is}

updateState :: M.IntMap [Char] -> Int -> Int -> Int -> M.IntMap [Char]
updateState s q src t = M.adjust (const updatedTargetStack) t $ M.adjust (const updatedSourceStack) src s
  where
    sourceStack = s M.! src
    targetStack = s M.! t
    (updatedSourceStack, updatedTargetStack) = transfer q (sourceStack, targetStack)

transfer :: Int -> ([a], [a]) -> ([a], [a])
transfer 0 final = final
transfer n (x : xs, ys) = transfer (n - 1) (xs, x : ys)
transfer _ ([], _) = error "Cannot transfer from an empty stack"
