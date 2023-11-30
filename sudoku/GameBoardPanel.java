package sudoku;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/*
displays the board
 */
public class GameBoardPanel extends JPanel {
    private int gridSize;
    private GameBoardModel model;

    public GameBoardPanel(int gridSize, GameBoardModel model) {
        this.gridSize = gridSize;
        this.model = model;

        ArrayList<Cell> cells = (ArrayList<Cell>) model.getCells();

        for (Cell cell : cells) {
            this.add(cell);
        }

        this.setLayout(new GridLayout(gridSize, gridSize));
    }
}
