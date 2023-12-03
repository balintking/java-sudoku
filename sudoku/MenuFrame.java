package sudoku;

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

import static sudoku.GameController.*;

public class MenuFrame extends JFrame implements ActionListener {
    JComboBox<BoardDimension> dimensionSelect;
    JComboBox<Difficulty> difficultySelect;
    JButton newGameButton;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem loadMenuItem;

    public MenuFrame() {
        super("Sudoku - New Game");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(800, 600));
        //getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        this.setResizable(true);

        initComponents();
        pack();
    }

    private void initComponents() {

        buildMenuBar();

        //filler
        JPanel filler = new JPanel();
        filler.setMaximumSize(new Dimension(800, 200));

        //selections
        JPanel selections = new JPanel(new FlowLayout());
        selections.setMaximumSize(new Dimension(800, 50));
        selections.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel dimensionLabel = new JLabel("Board Size: ", SwingConstants.TRAILING);
        dimensionSelect = new JComboBox<>(BoardDimension.values());
        dimensionLabel.setLabelFor(dimensionSelect);

        JLabel difficultyLabel = new JLabel("Difficulty: ", SwingConstants.TRAILING);
        difficultySelect = new JComboBox<>(Difficulty.values());
        difficultyLabel.setLabelFor(dimensionSelect);

        selections.add(dimensionLabel);
        selections.add(dimensionSelect);
        selections.add(difficultyLabel);
        selections.add(difficultySelect);

        //button
        newGameButton = new JButton("New Game");
        newGameButton.addActionListener(this);
        newGameButton.setMaximumSize(new Dimension(200, 50));
        newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(filler);
        add(selections);
        add(newGameButton);
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

        fileMenu.add(loadMenuItem);

        menuBar.add(fileMenu);

        this.setJMenuBar(menuBar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(newGameButton)) {
            SudokuGame.newGame((BoardDimension) dimensionSelect.getSelectedItem(), (Difficulty) difficultySelect.getSelectedItem());
        } else if (e.getSource().equals(loadMenuItem)) {
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
        }
    }
}
