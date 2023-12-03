package sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GameFrame extends JFrame implements ActionListener {
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenu gameMenu;
    private JMenuItem saveMenuItem;
    private JMenuItem loadMenuItem;
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

        saveMenuItem = new JMenuItem("Save Game");
        saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.META_DOWN_MASK));
        saveMenuItem.addActionListener(this);
        loadMenuItem = new JMenuItem("Load Game");
        loadMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.META_DOWN_MASK));
        loadMenuItem.addActionListener(this);

        fileMenu.add(saveMenuItem);
        fileMenu.add(loadMenuItem);

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
            if (item.equals(saveMenuItem)) {
                JFileChooser fileChooser = new JFileChooser();
                File saveDir = null;
                try {
                    saveDir = Files.createDirectories(Paths.get("./savefiles")).toFile();
                } catch (IOException ex) {
                    System.out.println("Save unsuccessful: IOException");
                }
                fileChooser.setCurrentDirectory(saveDir);
                int response = fileChooser.showSaveDialog(null);

                if (response == JFileChooser.APPROVE_OPTION) {
                    File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                    SudokuGame.saveGame(file);
                }
            }else if (item.equals(loadMenuItem)) {
                JFileChooser fileChooser = new JFileChooser();
                File saveDir = null;
                try {
                    saveDir = Files.createDirectories(Paths.get("./savefiles")).toFile();
                } catch (IOException ex) {
                    System.out.println("Save unsuccessful: IOException");
                }
                fileChooser.setCurrentDirectory(saveDir);
                int response = fileChooser.showOpenDialog(null);

                if (response == JFileChooser.APPROVE_OPTION) {
                    File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                    SudokuGame.loadGame(file);
                }
            } else if (item.equals(newGameMenuItem)) {
                SudokuGame.showMenu();
            }
        }
    }
}
