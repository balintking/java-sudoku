package sudoku;

import javax.swing.*;
import java.awt.*;

public class ApplicationFrame extends JFrame {
    public ApplicationFrame() throws HeadlessException {
        this.setTitle("Sudoku");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setVisible(true);

        //TODO: icon
        //ImageIcon icon = new ImageIcon("");
        //this.setIconImage(icon.getImage());
    }
}
