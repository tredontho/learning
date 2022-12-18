module Day07.Part01 where

import Control.Monad (void)
import Data.Void
import Text.Megaparsec
import Text.Megaparsec.Char (alphaNumChar, char, spaceChar, string)
import Text.Megaparsec.Char.Lexer (decimal)

type Parser = Parsec Void String

lsResultParser :: Parser Node
lsResultParser = try dirParser <|> fileParser

dirParser :: Parser Node
dirParser = do
    void $ string "dir"
    singleSpace
    dirName <- some alphaNumChar
    pure $ Directory dirName []

fileParser :: Parser Node
fileParser = do
    fileSize <- decimal
    singleSpace
    fileName <- many alphaNumChar
    pure $ File fileName fileSize

commandParser :: Parser Command
commandParser = do
    void $ char '$'
    singleSpace
    try lsParser <|> cdParser

lsParser :: Parser Command
lsParser = do
    void $ string "ls"
    pure ListDirectory

cdParser :: Parser Command
cdParser = do
    void $ string "cd"
    dirName <- some alphaNumChar
    pure $ ChangeDirectory dirName

singleSpace :: Parser ()
singleSpace = skipCount 1 spaceChar

type Name = String
type Size = Int

-- | A filesystem is simply a tree structure where nodes have a type (file or directory)
data Filesystem = Filesytem
    { currentDir :: Node
    , allNodes :: [Node]
    }
    deriving (Show)

{- | A node in a filesystem is either a directory containing other nodes, or a file
   Both types have names, files further have a size associated with them
-}
data Node = Directory Name [Node] | File Name Size deriving (Show)

data Command = ChangeDirectory Name | ListDirectory

calculateSize :: Node -> Int
calculateSize (Directory _ xs) = sum $ map calculateSize xs
calculateSize (File _ s) = s

-- We need to build up info about the filesystem based on the results of navigating the filesystem
-- When we execute a `cd`, we need to add the target directory to the list of directories in our filesystem, if it does not already exist. Special handling must be applied for `..` and `/`, which do not add new directories but change to the parent and root directires, respectively.

{-
executeCommand :: Filesystem -> Command -> Filesystem
executeCommand fs cmd = case cmd of
  (ChangeDirectory dir) -> changeDir fs (currentDir fs) dir
  ListDirectory ->

changeDir :: Filesystem -> Node -> Name -> Filesystem
changeDir = _
-}
