package sudoku;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sudoku.GameController.BoardDimension;
import sudoku.GameController.Difficulty;

import static org.junit.jupiter.api.Assertions.*;

class GameBoardModelTest {
    GameBoardModel gameBoardModel;
    @BeforeEach
    void setUp() {
        gameBoardModel = new GameBoardModel(BoardDimension.SMALL, Difficulty.EASY);
        gameBoardModel.setCell(1, 2, new Cell(5, true));
    }

    @Test
    void getBoardDimension() {
        assertEquals(BoardDimension.SMALL, gameBoardModel.getBoardDimension());
    }

    @Test
    void getDifficulty() {
        assertEquals(Difficulty.EASY, gameBoardModel.getDifficulty());
    }

    @Test
    void getCell() {
        assertEquals("5", gameBoardModel.getCell(1, 2).getText());
    }

    @Test
    void countValueInRow() {
        assertEquals(1, gameBoardModel.countValueInRow(1, 5));
    }

    @Test
    void countValueInColumn() {
        assertEquals(1, gameBoardModel.countValueInColumn(2, 5));
    }

    @Test
    void countValueInBox() {
        assertEquals(1, gameBoardModel.countValueInBox(1, 2, 5));
    }

    @Test
    void isValidPlacement() {
        assertFalse(gameBoardModel.isValidPlacement(1, 1, 5, 0));
    }
}