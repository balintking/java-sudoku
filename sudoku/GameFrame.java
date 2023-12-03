package sudoku;

import sudoku.GameController.BoardDimension;
import sudoku.GameController.Difficulty;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.lang.System.exit;

/**
 * Frame responsive for displaying one game
 */
public class GameFrame extends JFrame implements ActionListener {
    private final BoardDimension boardDimension;
    private final Difficulty difficulty;
    private JMenuItem saveMenuItem;
    private JMenuItem loadMenuItem;
    private JMenuItem newGameMenuItem;
    private JButton checkButton;

    /**
     * Constructor that initializes the frame
     * @param boardDimension Dimension of the game board
     * @param difficulty Level of Difficulty
     * @param panel Game board panel to be added to this frame
     */
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

    /**
     * Adds the components to the frame
     * @param panel Game board panel to be added
     */
    private void initComponents(GameBoardPanel panel) {
        buildMenuBar();
        add(panel);

        JPanel infoPanel = new JPanel();

        JLabel boardSizeLabel = new JLabel("Grid Size: "+boardDimension.gridSize+"x"+ boardDimension.gridSize);
        JLabel difficultyLabel = new JLabel("Difficulty: "+difficulty);

        infoPanel.add(boardSizeLabel);
        infoPanel.add(difficultyLabel);

        checkButton = new JButton("Check Solution");
        checkButton.addActionListener(this);

        infoPanel.add(checkButton);

        add(infoPanel);
    }

    /**
     * Builds the menu bar on top of the frame
     */
    private void buildMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        //File
        JMenu fileMenu = new JMenu("File");

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
        JMenu gameMenu = new JMenu("Game");

        newGameMenuItem = new JMenuItem("New Game");
        newGameMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.META_DOWN_MASK));
        newGameMenuItem.addActionListener(this);

        gameMenu.add(newGameMenuItem);

        menuBar.add(gameMenu);

        this.setJMenuBar(menuBar);
    }

    /**
     * Action handling to the game frame
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JMenuItem item) {
            //save
            if (item.equals(saveMenuItem)) {
                JFileChooser fileChooser = initFileChooser();
                int response = fileChooser.showSaveDialog(null);

                if (response == JFileChooser.APPROVE_OPTION) {
                    File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                    SudokuGame.saveGame(file);
                }
            //load
            }else if (item.equals(loadMenuItem)) {
                JFileChooser fileChooser =  initFileChooser();
                int response = fileChooser.showOpenDialog(null);

                if (response == JFileChooser.APPROVE_OPTION) {
                    File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                    SudokuGame.loadGame(file);
                }
            //new game
            } else if (item.equals(newGameMenuItem)) {
                SudokuGame.showMenu();
            }
        //check solution
        } else if (e.getSource().equals(checkButton)) {
            displayDialog();
        }
    }

    /**
     * Initializes File Chooser to the save files directory
     * @return File chooser
     */
    static JFileChooser initFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        File saveDir = null;
        try {
            saveDir = Files.createDirectories(Paths.get("./savefiles")).toFile();
        } catch (IOException ex) {
            SudokuGame.logger.info("IOException during opening save files directory");
        }
        fileChooser.setCurrentDirectory(saveDir);
        return fileChooser;
    }

    /**
     * Displays dialog after checking the given solution
     */
    private void displayDialog() {
        switch(SudokuGame.checkSolution()) {
            case -1 -> //Wrong Solution
                    JOptionPane.showMessageDialog(null, "Uh-oh! The solution is not valid. Keep refining your Sudoku skills!", "Wrong Solution", JOptionPane.ERROR_MESSAGE);
            case 0 -> //Incomplete Board
                    JOptionPane.showMessageDialog(null, "You have to fill in all cells.", "Incomplete Board", JOptionPane.WARNING_MESSAGE);
            case 1 -> {
                //Puzzle Solved
                int answer = JOptionPane.showConfirmDialog(null, "Victory! The Sudoku puzzle has been triumphantly solved. How about another game?", "Puzzle Solved", JOptionPane.YES_NO_CANCEL_OPTION);
                switch (answer) {
                    case 0 -> //new game
                            SudokuGame.showMenu();
                    case 1 -> //exit
                            exit(0);
                    default -> { //do nothing
                    }
                }
            }
            default -> throw new IllegalStateException("Unexpected value: " + SudokuGame.checkSolution());
        }
    }
}
