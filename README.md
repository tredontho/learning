Previously I had repos scattered all over, one for each book or coding exercise website, etc. I'm hoping that combining them into a single repo will simplify some things... who knows.

# Submodules
Some of the exercises are included here as git submodules, so understanding how to use git submodules will help. I don't understand them well, so I'm recording some common tips here.

[Git Submodule](https://git-scm.com/book/en/v2/Git-Tools-Submodules)

```shell
git submodule update --init
```
or, if cloning this repo for the first time, clone with the `--recurse-submodules` flag.

It may be a good idea to set `submdule.recurse` to `true`
```shell
git config submodule.recurse true
```

Setting `push.recurseSubmodules` to either `check` or `on-demand` will help when pushing changes in the main project.

# Books/Exercises/Etc.

## Advent of Code
Working through the problems from [Advent of Code](https://adventofcode.com/) - each year should have its own directory

## Crafting Interpreters
Following along with the [Crafting Interpreters](https://craftinginterpreters.com/) book.

## Functional Programming in Scala
Working through exercises in the [Functional Programming in Scala](https://www.manning.com/books/functional-programming-in-scala) book.

## Haskell in Depth
Working through exercises/examples in the [Haskell in Depth](https://www.manning.com/books/haskell-in-depth) book.

Note: The examples for the book are available at
<https://github.com/bravit/hid-examples.git> and included in the
`haskell-in-depth` directory as a
[Git Submodule](https://git-scm.com/book/en/v2/Git-Tools-Submodules), so you'll
need to either run
```shell
git submodule update --init
```
or, if cloning this repo for the first time, clone with the `--recurse-submodules` flag.

## LeetCode
Attempts/solutions for [LeetCode](https://leetcode.com/) problems

## Optics by Example
Working through exercises from [Optics by Example](https://leanpub.com/optics-by-example)
