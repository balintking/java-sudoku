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
        //load cells
//        model.setCell(1, 1, new Cell(1, true));
//        model.setCell(1, 2, new Cell(2, true));
//        model.setCell(1, 3, new Cell(3, true));
//        model.setCell(2, 1, new Cell(4, true));
//        model.setCell(2, 2, new Cell(5, true));
//        model.setCell(2, 3, new Cell(6, true));
//        model.setCell(3, 1, new Cell(7, true));
//        model.setCell(3, 2, new Cell(8, true));
//        model.setCell(3, 3, new Cell(9, true));

        generateBoard();
        System.out.println(model.getCells());
        panel.addCells();
    }

    /**
     * Generates new board based on the chosen gridSize and difficulty level
     */
    public void generateBoard() {
        fillDiagonal();
    }

    /**
     * Generates random int value between 1 and the specified value
     * @param num Upper bound (inclusive)
     * @return Random number
     */
    public static int randomGenerator(int num) {
        return random.nextInt(num) + 1;
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
                    value = randomGenerator(boardSize.maxValue);
                } while (model.isValueInBox(row + i, col + j, value));
                model.setCell(row + i, col + j, new Cell(value, true));
            }
        }
    }
}
