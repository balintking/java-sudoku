package sudoku;

import sudoku.GameController.BoardSize;

import java.util.ArrayList;
import java.util.List;

/**
 * Model storing the actual state of the board
 */
public class GameBoardModel {
    private BoardSize boardSize;

    /**
     * 2-dimensional ArrayList, where the cells are stored
     */
    private ArrayList<ArrayList<Cell>> board;

    public GameBoardModel(BoardSize boardSize) {
        this.boardSize = boardSize;
        board = new ArrayList<>();

        //fills the board with blank cells
        for (int r = 1; r <= boardSize.gridSize; r++) {
            ArrayList<Cell> row = new ArrayList<>();
            for (int c = 1; c <= boardSize.gridSize; c++) {
                Cell cell = new Cell(0, false);
                row.add(cell);
            }
            board.add(row);
        }
    }

    /**
     * Copy Constructor
     * @param board2 The board to copy
     */
    public GameBoardModel(GameBoardModel board2) {
        this.boardSize = board2.boardSize;
        this.board = new ArrayList<>();

        for (int row = 0; row < boardSize.gridSize; row++) {
            ArrayList<Cell> currentRow = board2.board.get(row);
            ArrayList<Cell> newRow = new ArrayList<>();

            for (int col = 0; col < boardSize.gridSize; col++) {
                Cell currentCell = currentRow.get(col);

                newRow.add(currentCell);
            }
            board.add(newRow);
        }
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
     *
     * @param row Row at which the cell is to be set
     * @param col Column at which the cell is to be set
     * @param cell Cell to be set
     */
    public void setCell(int row, int col, Cell cell) {
        ArrayList<Cell> selectedRow = board.get(row);
        selectedRow.set(col, cell);
        board.set(row, selectedRow);
    }

    public List<ArrayList<Cell>> getBoard() {
        return board;
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
        for (int row = 0; row < boardSize.gridSize; row++) {
            ArrayList<Cell> currentRow = board.get(row);
            for (int col = 0; col < boardSize.gridSize; col++) {
                System.out.print(currentRow.get(col).getValue() + " ");
            }
            System.out.println();
        }
    }

    /**
     * Checks if the given value is present in the row
     * @param row Selected row
     * @param value Value to be checked
     * @return True if it is present, otherwise false
     */
    public boolean isValueInRow(int row, int value) {
        ArrayList<Cell> selectedRow = board.get(row);
        for (int col = 0; col < boardSize.gridSize; col++) {
            if (selectedRow.get(col).getValue() == value) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the given value is present in the column
     * @param col Selected column
     * @param value Value to be checked
     * @return True if it is present, otherwise false
     */
    public boolean isValueInColumn(int col, int value) {
        for (int row = 0; row < boardSize.gridSize; row++) {
            ArrayList<Cell> currentRow = board.get(row);
            if (currentRow.get(col).getValue() == value) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the given value is present in the box
     * @param row Starting row of the box
     * @param col Starting column of the box
     * @param value Value to be checked
     * @return True if it is present, otherwise false
     */
    public boolean isValueInBox(int row, int col, int value) {
        int localBoxRow = row - (row % boardSize.boxSize);
        int localBoxCol = col - (col % boardSize.boxSize);

        for (int i = localBoxRow; i < localBoxRow + boardSize.boxSize; i++) {
            for (int j = localBoxCol; j < localBoxCol + boardSize.boxSize; j++) {
                if (getCell(i, j).getValue() == value) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if the given value can be placed at the specified index
     * @param row Selected row
     * @param col Selected column
     * @param value Value to be checked
     * @return True if the placement is valid, otherwise false
     */
    public boolean isValidPlacement(int row, int col, int value) {
        return !isValueInRow(row, value) &&
                !isValueInColumn(col, value) &&
                !isValueInBox(row, col, value);
    }
}
