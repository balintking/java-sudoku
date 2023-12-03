package sudoku;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;

public class CellTypeListener implements KeyListener, Serializable {
    @Override
    public void keyTyped(KeyEvent e) {
        Cell cell = (Cell) e.getSource();
        char c = e.getKeyChar();

        switch (SudokuGame.maxValue()) {
            case 4 -> {
                if (c < '1' || c > '4') {
                    e.consume();
                    return;
                }

                if (!cell.isGiven()) {
                    cell.setText("");
                }
            }
            case 16 -> {
                if (cell.getText().equals("1")) {
                    if (c < '0' || c > '6') {
                        e.consume();
                    }
                } else if (c < '1' || c > '9') {
                    e.consume();
                } else if (!cell.isGiven()) {
                    cell.setText("");
                }
            }
            default -> {                            //case 9
                if (c < '1' || c > '9') {
                    e.consume();
                    return;
                }

                if (!cell.isGiven()) {
                    cell.setText("");
                }
            }
        }
    }


    @Override
    public void keyPressed(KeyEvent e) {
        //not used
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // not used
    }
}
