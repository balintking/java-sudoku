package sudoku;

import sudoku.GameController.BoardSize;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/*
displays the board
 */
public class GameBoardPanel extends JPanel {
    private BoardSize boardSize;
    private GameBoardModel model;

    public GameBoardPanel(BoardSize boardSize, GameBoardModel model) {
        this.boardSize = boardSize;
        this.model = model;

        this.setLayout(new GridLayout(boardSize.gridSize, boardSize.gridSize));
    }

    public void addCells() {
        ArrayList<Cell> cells = (ArrayList<Cell>) model.getCells();

        for (Cell cell : cells) {
            this.add(cell);
        }
    }
}
