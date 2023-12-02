package sudoku;

import sudoku.GameController.BoardDimension;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * JPanel class that displays a sudoku board
 */
public class GameBoardPanel extends JPanel {
    private BoardDimension boardDimension;
    private ArrayList<ArrayList<JPanel>> boxPanels;

    public GameBoardPanel(BoardDimension boardDimension) {
        this.boardDimension = boardDimension;
        this.boxPanels = new ArrayList<>();

        this.setLayout(new GridLayout(boardDimension.boxSize, boardDimension.boxSize));
        this.setPreferredSize(new Dimension(400, 400));

        for (int i = 0; i < boardDimension.boxSize; i++) {
            ArrayList<JPanel> boxRow = new ArrayList<>();
            for (int j = 0; j < boardDimension.boxSize; j++) {
                JPanel box = new JPanel();
                box.setLayout(new GridLayout(boardDimension.boxSize, boardDimension.boxSize));
                box.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

                boxRow.add(box);
                this.add(box);
            }
            boxPanels.add(boxRow);
        }
    }

    public void addCells(GameBoardModel model) {
        ArrayList<ArrayList<Cell>> cells = (ArrayList<ArrayList<Cell>>) model.getBoard();

        for (int row = 0; row < boardDimension.gridSize; ++row) {
            for (int col = 0; col < boardDimension.gridSize; ++col) {
                Cell cell = cells.get(row).get(col);

                boxPanels.get(row / boardDimension.boxSize).get(col / boardDimension.boxSize).add(cell);
            }
        }
    }
}
