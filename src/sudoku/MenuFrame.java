package sudoku;

import sudoku.GameController.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;

/**
 * Frame responsive for displaying menu
 */
public class MenuFrame extends JFrame implements ActionListener {
    private JComboBox<BoardDimension> dimensionSelect;
    private JComboBox<Difficulty> difficultySelect;
    private JButton newGameButton;
    private JMenuItem loadMenuItem;

    /**
     * Constructor that initializes the frame
     */
    public MenuFrame() {
        super("Sudoku - New Game");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(600, 600));
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        this.setResizable(false);

        ImageIcon icon = new ImageIcon("logo.png");
        this.setIconImage(icon.getImage());

        initComponents();
        pack();
    }

    /**
     * Adds the components to the frame
     */
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
        JMenuBar menuBar = new JMenuBar();

        //File
        JMenu fileMenu = new JMenu("File");

        loadMenuItem = new JMenuItem("Load Game");
        loadMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.META_DOWN_MASK));
        loadMenuItem.addActionListener(this);

        fileMenu.add(loadMenuItem);

        menuBar.add(fileMenu);

        this.setJMenuBar(menuBar);
    }

    /**
     * Action handling
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //new game
        if (e.getSource().equals(newGameButton)) {
            SudokuGame.newGame((BoardDimension) dimensionSelect.getSelectedItem(), (Difficulty) difficultySelect.getSelectedItem());
        }
        //load
        else if (e.getSource().equals(loadMenuItem)) {
            JFileChooser fileChooser =  GameFrame.initFileChooser();
            int response = fileChooser.showOpenDialog(null);

            if (response == JFileChooser.APPROVE_OPTION) {
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                SudokuGame.loadGame(file);
            }
        }
    }
}
