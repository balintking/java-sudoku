package sudoku;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {
    Cell cell;
    @BeforeEach
    void setUp() {
        cell = new Cell(9, true);
    }

    @Test
    void getText() {
        assertEquals("9", cell.getText());
    }

    @Test
    void isGiven() {
        assertTrue(cell.isGiven());
    }

    @Test
    void isEditable() {
        assertFalse(cell.isEditable());
    }
}