package sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class ApplicationFrame extends JFrame {
    JMenuBar menuBar;
    JMenu fileMenu;
    JMenu gameMenu;
    JMenuItem loadMenuItem;
    JMenuItem saveMenuItem;
    JMenuItem newGameMenuItem;
    public ApplicationFrame() throws HeadlessException {
        this.setTitle("Sudoku");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setVisible(true);

        //TODO: icon
        //ImageIcon icon = new ImageIcon("");
        //this.setIconImage(icon.getImage());

        buildMenuBar();
    }

    private void buildMenuBar() {
        menuBar = new JMenuBar();
        MenuActionListener menuActionListener = new MenuActionListener();

        //File
        fileMenu = new JMenu("File");

        loadMenuItem = new JMenuItem("Load Game");
        loadMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.META_DOWN_MASK));
        loadMenuItem.addActionListener(menuActionListener);
        saveMenuItem = new JMenuItem("Save Game");
        saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.META_DOWN_MASK));
        saveMenuItem.addActionListener(menuActionListener);

        fileMenu.add(loadMenuItem);
        fileMenu.add(saveMenuItem);

        menuBar.add(fileMenu);


        //Game
        gameMenu = new JMenu("Game");

        newGameMenuItem = new JMenuItem("New Game");
        newGameMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.META_DOWN_MASK));
        newGameMenuItem.addActionListener(menuActionListener);

        gameMenu.add(newGameMenuItem);

        menuBar.add(gameMenu);

        this.setJMenuBar(menuBar);
    }

    class MenuActionListener implements ActionListener {
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
                    System.out.println("new");
                    //TODO: implement new game
                }
            }
        }
    }
}
