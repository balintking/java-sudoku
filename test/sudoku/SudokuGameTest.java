package sudoku;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuGameTest {
    @BeforeEach
    void setUp() {
        SudokuGame.newGame(GameController.BoardDimension.SMALL, GameController.Difficulty.EASY);
    }

    @Test
    void checkSolution() {
        assertEquals(0, SudokuGame.checkSolution());
    }
}