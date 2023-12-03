package sudoku;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Controller class responsible for the game logic
 */
public class GameController {
    private BoardDimension boardDimension;
    private Difficulty difficulty;

    private GameBoardModel model;
    private GameBoardPanel panel;

    private static final Random random = new Random();

    /**
     * Constants for different board sizes
     */
    public enum BoardDimension implements Serializable {
        SMALL(4, 2), MEDIUM(9, 3), LARGE(16, 4);

        int gridSize;
        int boxSize;
        int maxValue;

        BoardDimension(int gridSize, int boxSize) {
            this.gridSize = gridSize;
            this.boxSize = boxSize;
            this.maxValue = gridSize;
        }
    }

    /**
     * Constants for difficulty levels
     */
    public enum Difficulty implements Serializable {
        EASY, NORMAL, HARD
    }

    public GameController(BoardDimension boardDimension, Difficulty difficulty) {
        this.boardDimension = boardDimension;
        this.difficulty = difficulty;

        model = new GameBoardModel(boardDimension, difficulty);
        panel = new GameBoardPanel(boardDimension);
    }

    /**
     * Copy constructor - used for loading saved state
     * @param model Saved state of the game
     */
    public GameController(GameBoardModel model) {
        this.boardDimension = model.getBoardDimension();
        this.difficulty = model.getDifficulty();

        this.model = model;
        this.panel = new GameBoardPanel(boardDimension);
        panel.addCells(model);
    }

    public GameBoardModel getModel() {
        return model;
    }

    public GameBoardPanel getPanel() {
        return panel;
    }

    public void newGame() {
        generateBoard();
        panel.addCells(model);
    }

    /**
     * Generates new board based on the chosen gridSize and difficulty level
     */
    private void generateBoard() {
        fillDiagonal();
        solveBoard(model);
        removeSomeCells(boardDimension, difficulty);
    }

    /**
     * Generates random int value between 0 and the specified value
     * @param num Upper bound (exclusive)
     * @return Random number
     */
    public static int randomGenerator(int num) {
        return random.nextInt(num);
    }

    /**
     * Fills the gridSize number of diagonal (boxSize x boxSize) boxes
     */
    private void fillDiagonal() {
        for (int i = 0; i < boardDimension.gridSize; i = i + boardDimension.boxSize)

            // diagonal box start coordinates i==j
            fillBox(i, i, boardDimension.boxSize);
    }

    /**
     * Fills a box with random values
     * @param row Starting row of the box
     * @param col Starting column of the box
     * @param boxSize Width of the box
     */
    private void fillBox(int row, int col, int boxSize) {
        int value;

        for (int i = 0; i < boxSize; i++) {
            for (int j = 0; j < boxSize; j++) {
                do {
                    value = randomGenerator(boardDimension.maxValue) + 1;
                } while (model.countValueInBox(row + i, col + j, value) > 0);
                model.setCell(row + i, col + j, new Cell(value, true));
            }
        }
    }

    /**
     * Recursive backtracking algorithm for filling remaining cells
     */
    private boolean solveBoard(GameBoardModel board) {
        for (int row = 0; row < boardDimension.gridSize; row++) {
            for (int col = 0; col < boardDimension.gridSize; col++) {
                if (!board.getCell(row, col).isGiven()) {
                    for (int tryValue = 1; tryValue <= boardDimension.maxValue; tryValue++) {
                        if (board.isValidPlacement(row, col, tryValue, 0)) {
                            board.setCell(row, col, new Cell(tryValue, true));

                            if (solveBoard(board)) {
                                return true;
                            } else {
                                board.setCell(row, col, new Cell(0, false));
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Removes some cells from the model considering the board size and the difficulty
     * @param boardDimension Size of the board
     * @param difficulty Difficulty level
     */
    private void removeSomeCells(BoardDimension boardDimension, Difficulty difficulty) {
        int cellsToRemove = calculateCellsToRemove(boardDimension, difficulty);

        while (cellsToRemove > 0) {
            int randomRow = random.nextInt(boardDimension.gridSize);
            int randomCol = random.nextInt(boardDimension.gridSize);

            while (!model.getCell(randomRow, randomCol).isGiven()) {
                randomRow = random.nextInt(boardDimension.gridSize);
                randomCol = random.nextInt(boardDimension.gridSize);
            }

            model.setCell(randomRow, randomCol, new Cell(0, false));

            cellsToRemove--;
        }
    }

    /**
     * Calculates how many cells to remove on the given board size to achieve the specified difficulty level
     * @param boardDimension Size of the board
     * @param difficulty Difficulty level
     * @return The number of cells to remove
     */
    private int calculateCellsToRemove(BoardDimension boardDimension, Difficulty difficulty) {
        int cellsToRemove = 0;

        switch (boardDimension) {
            case SMALL -> {
                switch (difficulty) {
                    case EASY -> cellsToRemove = randomGenerator(4) + 8;
                    case NORMAL -> cellsToRemove = randomGenerator(4) + 4;
                    case HARD -> cellsToRemove = randomGenerator(4) + 5;
                }
            }
            case MEDIUM -> {
                switch (difficulty) {
                    case EASY -> cellsToRemove = randomGenerator(11) + 30;
                    case NORMAL -> cellsToRemove = randomGenerator(11) + 20;
                    case HARD -> cellsToRemove = randomGenerator(11) + 15;
                }
            }
            case LARGE -> {
                switch (difficulty) {
                    case EASY -> cellsToRemove = randomGenerator(31) + 80;
                    case NORMAL -> cellsToRemove = randomGenerator(31) + 50;
                    case HARD -> cellsToRemove = randomGenerator(31) + 40;
                }
            }
        }

        return cellsToRemove;
    }

    /**
     * Tells if all the cells are filled or there are empty ones
     * @return True if all cell has a value, otherwise false
     */
    public boolean isAllCellFilled() {
        ArrayList<Cell> cells = (ArrayList<Cell>) model.getCells();

        for (Cell cell: cells) {
            if (cell.getText().equals("")) {
                return false;
            }
        }
        return true;
    }

    /**
     * Tells if the placement of the values in the table is correct
     * @return True if all cell has a valid placement
     */
    public boolean isBoardValid() {
        for (int row = 0; row < boardDimension.gridSize; row++) {
            for (int col = 0; col < boardDimension.gridSize; col++) {
                int value = Integer.parseInt(model.getCell(row, col).getText());
                if (!model.isValidPlacement(row, col, value, 1)) {
                    return false;
                }
            }
        }
        return true;
    }
}
