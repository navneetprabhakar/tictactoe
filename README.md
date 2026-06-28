# Tic-Tac-Toe with Minimax AI

A console Tic-Tac-Toe game in Java with an unbeatable computer player driven by the minimax algorithm, optimized with memoization (dynamic programming).

## Overview

This is a command-line implementation of Tic-Tac-Toe that you can play against the computer or as two human players. The board size and the number of marks needed in a row to win are both configurable at startup, so it supports the classic 3×3 game as well as larger boards. The computer player evaluates the game tree with minimax and caches previously seen positions to speed up its move search, so it always plays optimally — winning when possible and forcing a draw otherwise.

## Features

- **Configurable board** — choose any `n × n` board size at startup
- **Configurable win condition** — set how many consecutive same marks are needed to win
- **Minimax AI** — the computer player searches all possible moves to play optimally
- **Memoization** — previously evaluated board states are cached to reduce repeated work
- **Flexible players** — play human vs. computer (computer can move first) or human vs. human
- **Console UI** — board rendering and move input via the terminal

## Tech Stack

- Java (standard library only; no external dependencies or build tool)

## Getting Started

### Prerequisites

- A Java Development Kit (JDK)

### Compile and Run

The sources live in `src/` in the default package:

```bash
javac -d out src/TicTacToe.java src/AIPlayer.java
java -cp out TicTacToe
```

### How to Play

On launch the game prompts for:

1. The board size (`n` for an `n × n` board).
2. The number of consecutive matching marks required to win.
3. Whether Player 1 is the computer or a human.

Player 1 uses `X` and Player 2 uses `O`. When prompted, enter the row and column (both 0-indexed) for your move.

## Project Structure

```
src/
├── TicTacToe.java   # Game loop, board state, win/draw checks, and console I/O
└── AIPlayer.java    # Minimax move search with memoization
```

## References

- [Tic-tac-toe (Wikipedia)](https://en.wikipedia.org/wiki/Tic-tac-toe)
- [Minimax algorithm (Wikipedia)](https://en.wikipedia.org/wiki/Minimax)
