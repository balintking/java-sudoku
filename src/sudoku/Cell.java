package sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.io.Serializable;

/**
 * Represents a single cell on the GameBoard
 */
public class Cell extends JTextField implements Serializable {

    /**
     * Tells if the value of the cell is given, and so that it can not be edited
     */
    private final boolean isGiven;

    /**
     * Background color for given cells;
     */
    private static final int GIVEN_COLOR = 0xf2f2fc;

    private static final KeyListener cellTypeListener = new CellInputValidator();

    /**
     * Constructor for sudoku.Cell
     * @param initValue The value stored in the cell
     * @param isGiven Tells if the value of the cell is given, and so that it can not be edited
     */
    public Cell(int initValue, boolean isGiven) {
        super(isGiven ? Integer.toString(initValue): "", 1);
        this.isGiven = isGiven;

        setHorizontalAlignment(SwingConstants.CENTER);
        setPreferredSize(new Dimension(30, 30));
        setBorder(BorderFactory.createStrokeBorder(new Stroke(0.5f)));

        Font font = new Font(Font.SANS_SERIF,  Font.PLAIN, 15);
        setFont(font);

        addKeyListener(cellTypeListener);

        if (isGiven()) {
            setEditable(false);
            setFont(getFont().deriveFont(Font.BOLD));
            setBackground(new Color(GIVEN_COLOR));
        }
    }

    /**
     * Serializable child of BasicStroke
     */
    static class Stroke extends java.awt.BasicStroke implements Serializable {
        public Stroke(float width) {
            super(width);
        }
    }

    /**
     * Returns if the cell is given
     * @return True if is given, otherwise false
     */
    public boolean isGiven() {
        return isGiven;
    }
}
