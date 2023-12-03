package sudoku;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

/**
 * Represents a single cell on the GameBoard
 */
public class Cell extends JTextField implements Serializable {

    /**
     * The value stored in the cell
     */
    private int value;

    /**
     * Tells if the value of the cell is given, and so that it can not be edited
     */
    private boolean isGiven;

    private static final int GIVEN_COLOR = 0xf2f2fc;

    /**
     * Constructor for Cell
     *
     * @param value The value stored in the cell
     * @param isGiven Tells if the value of the cell is given, and so that it can not be edited
     */
    public Cell(int value, boolean isGiven) {
        super(isGiven ? Integer.toString(value): "", 1);
        this.value = value;
        this.isGiven = isGiven;
        setHorizontalAlignment(SwingConstants.CENTER);
        setPreferredSize(new Dimension(30, 30));
        setBorder(BorderFactory.createStrokeBorder(new Stroke(0.5f)));

        Font  font  = new Font(Font.SANS_SERIF,  Font.PLAIN, 15);
        setFont(font);

        if (isGiven()) {
            setEditable(false);
            setFont(getFont().deriveFont(Font.BOLD));
            setBackground(new Color(GIVEN_COLOR));
        }
    }

    static class Stroke extends java.awt.BasicStroke implements Serializable {
        public Stroke(float width) {
            super(width);
        }
    }


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isGiven() {
        return isGiven;
    }

    public void setGiven(boolean given) {
        isGiven = given;
    }
}
