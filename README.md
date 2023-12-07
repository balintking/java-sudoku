# java-sudoku
Sudoku desktop application in Java 

> This project was developed for the Programming 3 course at BME.
> 
> Author: Bálint Király
 
# Overview

- [About the Game](#About-the-Game)
- [Getting Started](#Getting-Started)
- [User Guide](#User-Guide)
- [Documentation](#Documentation)

# About the Game

### Description
Sudoku, also known as Su Doku, popular form of number game. In its simplest and most common configuration, sudoku consists of a 9 × 9 grid with numbers appearing in some of the squares. The object of the puzzle is to fill the remaining squares, using all the numbers 1–9 exactly once in each row, column, and the nine 3 × 3 subgrids. Sudoku is based entirely on logic, without any arithmetic involved, and the level of difficulty is determined by the quantity and positions of the original numbers. The puzzle, however, raised interesting combinatorial problems for mathematicians, two of whom proved in 2005 that there are 6,670,903,752,021,072,936,960 possible sudoku grids.
[(Source)](https://www.britannica.com/topic/sudoku)

### My version
My version of the game closely follows the original with the addition of several customizable features to enhance the player's experience.

At the beginning the Player have the option to set game modes in a menu.

There are three grid sizes:
- 6x6
- 9x9 (original)
- 12x12

Three difficulty levels are available for selection:
- easy
- normal
- hard

The chosen difficulty level affects the number of pre-filled cells in the puzzle. 

Once the player has made their selections, the game board will be generated based on the chosen grid size and difficulty level. The board is ready for the player to solve.

During the game player can save the game and load it back at any time.

# Getting Started

Make sure you have Java installed on your system.

Compile:

```bash
javac -d ./build ./src/sudoku/*.java
```

Run:

```bash
java -cp ./build sudoku.SudokuGame
```

### Alternatively you can run the .jar file
(Please note that the .jar file might not be updated to the latest version)

You can find the .jar file in `out/artifacts/java_sudoku_jar` directory.

```bash
java -jar ./out/artifacts/java_sudoku_jar/java-sudoku.jar
```

# User Guide

## Starting the game

### Creating a New Game

1. Select your preferred board size (`small`, `medium`, `large`).
2. Choose the difficulty level (`easy`, `normal`, `hard`).
3. Click on `New Game` to start the game.

### Loading a Saved Game
1. Press `Cmd + L` or select _File > Load_ from the menu bar.
2. Choose a previously saved game file.

## Gameplay

The board consists of pre-filled and blank cells. Pre-filled cells are colored and cannot be edited. Fill in all blank cells with valid numbers; the game prevents entry of invalid values.

Save your game state anytime with `cmd + S` or _File > Save_.

Load a saved game using `cmd + L` or _File > Load_.

To start a new game, use `cmd + N` or _Game > New Game_ from the menu bar.

## Checking Your Solution

After completing the game, verify your solution by clicking `Check Solution` at the bottom of the window.

The game will inform you if the solution is correct. If so, you can start a new game directly from the dialog box.

## Keyboard Shortcuts

`Cmd + N`: Create a new game.

`Cmd + S`: Save the game.

`Cmd + L`: Load a saved game.


# Documentation

Run this script to open documentation:

```bash
open ./documentation/sudoku/package-summary.html
```

Run this script to open class diagram:

```bash
open ./documentation/diagram.png
```