package sudoku;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;

public class CellTypeListener implements KeyListener, Serializable {
    @Override
    public void keyTyped(KeyEvent e) {
        Cell cell = (Cell) e.getSource();
        char c = e.getKeyChar();

        //TODO: 16 validation
        if (!Character.isDigit(c) || c == '0') {
            e.consume();
            return;
        }
        if (!cell.isGiven()) {
            cell.setText("");
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (SudokuGame.isAllCellFilled()) {
            System.out.println("full");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
