package sudoku;

import javax.swing.*;

/**
 * Represents a single cell on the GameBoard
 */
public class Cell extends JTextField {

    /**
     * The value stored in the cell
     */
    private int value;

    /**
     * Tells if the value of the cell is given, and so that it can not be edited
     */
    private boolean isGiven;


    /**
     * Constructor for Cell
     *
     * @param value The value stored in the cell
     * @param isGiven Tells if the value of the cell is given, and so that it can not be edited
     */
    public Cell(int value, boolean isGiven) {
        super(value == 0 ? "" : Integer.toString(value), 1);
        this.value = value;
        this.isGiven = isGiven;
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
