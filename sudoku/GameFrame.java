package sudoku;

import sudoku.GameController.BoardDimension;
import sudoku.GameController.Difficulty;

import javax.imageio.ImageIO;
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
    BoardDimension boardDimension;
    Difficulty difficulty;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenu gameMenu;
    private JMenuItem saveMenuItem;
    private JMenuItem loadMenuItem;
    private JMenuItem newGameMenuItem;

    public GameFrame(BoardDimension boardDimension, Difficulty difficulty, GameBoardPanel panel) {
        super("Sudoku");

        this.boardDimension = boardDimension;
        this.difficulty = difficulty;

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(600, 600));
        this.setResizable(false);
        setLayout(new FlowLayout());

        ImageIcon icon = new ImageIcon("logo.png");
        this.setIconImage(icon.getImage());

        initComponents(panel);
        pack();
    }

    private void initComponents(GameBoardPanel panel) {
        buildMenuBar();
        add(panel);

        JPanel infoPanel = new JPanel();
        BoxLayout sideLayout = new BoxLayout(infoPanel, BoxLayout.Y_AXIS);

        JLabel boardSizeLabel = new JLabel("Grid Size: "+boardDimension.gridSize+"x"+ boardDimension.gridSize);
        JLabel difficultyLabel = new JLabel("Difficulty: "+difficulty);

        infoPanel.add(boardSizeLabel);
        infoPanel.add(difficultyLabel);

        JButton checkButton = new JButton("Check Solution");

        infoPanel.add(checkButton);

        add(infoPanel);
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
