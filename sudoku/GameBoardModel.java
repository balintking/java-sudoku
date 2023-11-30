package sudoku;

import java.util.ArrayList;
import java.util.List;

/*
model of the board
 */
public class GameBoardModel {
    private int gridSize;

    /**
     * 2-dimensional ArrayList, where the cells are stored
     */
    private ArrayList<ArrayList<Cell>> board;

    public GameBoardModel(int gridSize) {
        this.gridSize = gridSize;
        board = new ArrayList<>();

        //fills the board with blank cells
        for (int r = 1; r <= gridSize; r++) {
            ArrayList<Cell> row = new ArrayList<>();
            for (int c = 1; c <= gridSize; c++) {
                Cell cell = new Cell(0, false);
                row.add(cell);
            }
            board.add(row);
        }
    }

    /**
     * Adds new cell to a specified position
     *
     * @param row - row at which the cell is to be inserted
     * @param col - column at which the cell is to be inserted
     * @param cell - Cell to be inserted
     */
    public void addCell(int row, int col, Cell cell) {
        ArrayList<Cell> selectedRow = board.get(row - 1);
        selectedRow.set(col - 1, cell);
        board.set(row - 1, selectedRow);
    }

    public List<ArrayList<Cell>> getBoard() {
        return board;
    }

    /**
     * Returns the cells in one list row-by-row
     * @return
     */
    public List<Cell> getCells() {
        ArrayList<Cell> cells = new ArrayList<>();

        for (ArrayList<Cell> row : board) {
            cells.addAll(row);
        }
        return cells;
    }
}
