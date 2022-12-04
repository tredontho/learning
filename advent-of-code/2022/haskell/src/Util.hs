module Util where

-- Not sure if this is in a library
-- Takes a list of elements and splits into groups around some value
splitIntoGroups :: (a -> Bool) -> [a] -> [[a]]
splitIntoGroups _ [] = []
splitIntoGroups p xs = group : splitIntoGroups p rest
  where
    (group, rest) = case break p xs of
        (y, _ : ys) -> (y, ys)
        (y, []) -> (y, [])
