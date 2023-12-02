package sudoku;

import java.util.Random;

/**
 * Controller class responsible for the game logic
 */
public class GameController {
    private final BoardSize boardSize;
    private final Difficulty difficulty;

    private final GameBoardModel model;
    private final GameBoardPanel panel;

    private static final Random random = new Random();

    /**
     * Constants for different board sizes
     */
    public enum BoardSize {
        SMALL(4, 2), MEDIUM(9, 3), LARGE(16, 4);

        int gridSize;
        int boxSize;
        int maxValue;

        BoardSize(int gridSize, int boxSize) {
            this.gridSize = gridSize;
            this.boxSize = boxSize;
            this.maxValue = gridSize;
        }
    }

    /**
     * Constants for difficulty levels
     */
    public enum Difficulty {
        EASY, NORMAL, HARD
    }

    public GameController(BoardSize boardSize, Difficulty difficulty) {
        this.boardSize = boardSize;
        this.difficulty = difficulty;

        model = new GameBoardModel(boardSize);
        panel = new GameBoardPanel(boardSize, model);
    }

    public GameBoardPanel getPanel() {
        return panel;
    }

    public void newGame() {

        generateBoard();
        panel.addCells();
    }

    /**
     * Generates new board based on the chosen gridSize and difficulty level
     */
    private void generateBoard() {
        fillDiagonal();
        solveBoard(model);
        removeSomeCells(boardSize, difficulty);
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
        for (int i = 0; i < boardSize.gridSize; i = i + boardSize.boxSize)

            // diagonal box start coordinates i==j
            fillBox(i, i, boardSize.boxSize);
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
                    value = randomGenerator(boardSize.maxValue) + 1;
                } while (model.isValueInBox(row + i, col + j, value));
                model.setCell(row + i, col + j, new Cell(value, true));
            }
        }
    }

    /**
     * Recursive backtracking algorithm for filling remaining cells
     */
    private boolean solveBoard(GameBoardModel board) {
        for (int row = 0; row < boardSize.gridSize; row++) {
            for (int col = 0; col < boardSize.gridSize; col++) {
                if (!board.getCell(row, col).isGiven()) {
                    for (int tryValue = 1; tryValue <= boardSize.maxValue; tryValue++) {
                        if (board.isValidPlacement(row, col, tryValue)) {
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
     * @param boardSize Size of the board
     * @param difficulty Difficulty level
     */
    private void removeSomeCells(BoardSize boardSize, Difficulty difficulty) {
        int cellsToRemove = calculateCellsToRemove(boardSize, difficulty);

        while (cellsToRemove > 0) {
            int randomRow = random.nextInt(boardSize.gridSize);
            int randomCol = random.nextInt(boardSize.gridSize);

            while (model.getCell(randomRow, randomCol).getValue() == 0) {
                randomRow = random.nextInt(boardSize.gridSize);
                randomCol = random.nextInt(boardSize.gridSize);
            }

            model.setCell(randomRow, randomCol, new Cell(0, false));

            cellsToRemove--;
        }
    }

    /**
     * Calculates how many cells to remove on the given board size to achieve the specified difficulty level
     * @param boardSize Size of the board
     * @param difficulty Difficulty level
     * @return The number of cells to remove
     */
    private int calculateCellsToRemove(BoardSize boardSize, Difficulty difficulty) {
        int cellsToRemove = 0;

        switch (boardSize) {
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
}
