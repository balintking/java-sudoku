package sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class GameFrame extends JFrame implements ActionListener {
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenu gameMenu;
    private JMenuItem loadMenuItem;
    private JMenuItem saveMenuItem;
    private JMenuItem newGameMenuItem;
    public GameFrame(GameBoardPanel panel) {
        super("Sudoku");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(800, 600));
        this.setResizable(true);

        //TODO: icon
        //ImageIcon icon = new ImageIcon("");
        //this.setIconImage(icon.getImage());
        init(panel);
    }

    private void init(GameBoardPanel panel) {
        buildMenuBar();
        add(panel);
        pack();
    }

    /**
     * Builds the menu bar on top of the frame
     */
    private void buildMenuBar() {
        menuBar = new JMenuBar();

        //File
        fileMenu = new JMenu("File");

        loadMenuItem = new JMenuItem("Load Game");
        loadMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.META_DOWN_MASK));
        loadMenuItem.addActionListener(this);
        saveMenuItem = new JMenuItem("Save Game");
        saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.META_DOWN_MASK));
        saveMenuItem.addActionListener(this);

        fileMenu.add(loadMenuItem);
        fileMenu.add(saveMenuItem);

        menuBar.add(fileMenu);


        //Game
        gameMenu = new JMenu("Game");

        newGameMenuItem = new JMenuItem("New Game");
        newGameMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.META_DOWN_MASK));
        newGameMenuItem.addActionListener(this);

        gameMenu.add(newGameMenuItem);

        menuBar.add(gameMenu);

        this.setJMenuBar(menuBar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JMenuItem item) {
            if (item.equals(loadMenuItem)) {
                System.out.println("load");
                //TODO: implement load file
            } else if (item.equals(saveMenuItem)) {
                System.out.println("save");
                //TODO: implement save file
            } else if (item.equals(newGameMenuItem)) {
                SudokuGame.showMenu();
            }
        }
    }
}
