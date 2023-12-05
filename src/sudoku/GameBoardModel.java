package sudoku;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static sudoku.GameController.*;


/**
 * Model storing the current state of the board
 */
public class GameBoardModel implements Serializable {
    private final BoardDimension boardDimension;
    private final Difficulty difficulty;

    /**
     * 2-dimensional ArrayList storing the cells
     */
    private final ArrayList<ArrayList<Cell>> board;

    /**
     * Constructor that fills the board with blank cells
     * @param boardDimension Dimension of the board
     * @param difficulty Difficulty of the board
     */
    public GameBoardModel(BoardDimension boardDimension, Difficulty difficulty) {
        this.boardDimension = boardDimension;
        this.difficulty = difficulty;
        board = new ArrayList<>();

        //fills the board with blank cells
        for (int r = 1; r <= boardDimension.gridSize; r++) {
            ArrayList<Cell> row = new ArrayList<>();
            for (int c = 1; c <= boardDimension.gridSize; c++) {
                Cell cell = new Cell(0, false);
                row.add(cell);
            }
            board.add(row);
        }
    }

    /**
     * Returns the dimension of the board
     * @return Dimension
     */
    public BoardDimension getBoardDimension() {
        return boardDimension;
    }

    /**
     * Returns the difficulty of the board
     * @return Dimension
     */
    public Difficulty getDifficulty() {
        return difficulty;
    }

    /**
     * Returns the board
     * @return board
     */
    public List<ArrayList<Cell>> getBoard() {
        return board;
    }

    /**
     * Returns the cell at the specified position
     * @param row Row of the cell
     * @param col Column of the cell
     * @return The cell
     */
    public Cell getCell(int row, int col) {
        return board.get(row).get(col);
    }

    /**
     * Sets the cell in a specified position to the specified cell
     * @param row Row at which the cell is to be set
     * @param col Column at which the cell is to be set
     * @param cell sudoku.Cell to be set
     */
    public void setCell(int row, int col, Cell cell) {
        ArrayList<Cell> selectedRow = board.get(row);
        selectedRow.set(col, cell);
        board.set(row, selectedRow);
    }

    /**
     * Returns the cells row-by-row in one list
     * @return The array containing all cells
     */
    public List<Cell> getCells() {
        ArrayList<Cell> cells = new ArrayList<>();

        for (ArrayList<Cell> row : board) {
            cells.addAll(row);
        }
        return cells;
    }

    /**
     * Prints the current state of the model to the console (for developer purposes only)
     */
    public void print() {
        for (int row = 0; row < boardDimension.gridSize; row++) {
            ArrayList<Cell> currentRow = board.get(row);
            for (int col = 0; col < boardDimension.gridSize; col++) {
                System.out.print(currentRow.get(col).getText());
                if (currentRow.get(col).getText().isEmpty()) {
                    System.out.print(" ");
                }
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println("--------");
    }

    /**
     * Counts how many cells are in the row with the given value
     * @param row Selected row
     * @param value Value to be checked
     * @return Number of occurrence
     */
    public int countValueInRow(int row, int value) {
        int count = 0;
        ArrayList<Cell> selectedRow = board.get(row);
        for (int col = 0; col < boardDimension.gridSize; col++) {
            if (!selectedRow.get(col).getText().isEmpty() && Integer.parseInt(selectedRow.get(col).getText()) == value ) {
                count++;
            }
        }
        return count;
    }

    /**
     * Counts how many cells are in the column with the given value
     * @param col Selected column
     * @param value Value to be checked
     * @return Number of occurrence
     */
    public int countValueInColumn(int col, int value) {
        int count = 0;
        for (int row = 0; row < boardDimension.gridSize; row++) {
            ArrayList<Cell> currentRow = board.get(row);
            if (!currentRow.get(col).getText().isEmpty() && Integer.parseInt(currentRow.get(col).getText()) == value) {
                count++;
            }
        }
        return count;
    }

    /**
     * Counts how many cells are in the box with the given value
     * @param row Selected row
     * @param col Selected column
     * @param value Value to be checked
     * @return Number of occurrence
     */
    public int countValueInBox(int row, int col, int value) {
        int count = 0;
        int localBoxRow = row - (row % boardDimension.boxSize);
        int localBoxCol = col - (col % boardDimension.boxSize);

        for (int i = localBoxRow; i < localBoxRow + boardDimension.boxSize; i++) {
            for (int j = localBoxCol; j < localBoxCol + boardDimension.boxSize; j++) {
                if (!getCell(i, j).getText().isEmpty() && Integer.parseInt(getCell(i, j).getText()) == value) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Checks if the given value can be placed at the specified index
     * @param row Selected row
     * @param col Selected column
     * @param value Value to be checked
     * @param expected Expected number of occurrence of the value in rows, columns, and boxes
     * @return True if the placement is valid, otherwise false
     */
    public boolean isValidPlacement(int row, int col, int value, int expected) {
        return countValueInRow(row, value) == expected &&
                countValueInColumn(col, value) == expected &&
                countValueInBox(row, col, value) == expected;
    }
}
