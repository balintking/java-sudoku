package sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static sudoku.GameController.*;

public class MenuFrame extends JFrame implements ActionListener {
    JComboBox<BoardDimension> dimensionSelect;
    JComboBox<Difficulty> difficultySelect;
    JButton newGameButton;
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

    @Override
    public void actionPerformed(ActionEvent e) {
        SudokuGame.showGame((BoardDimension) dimensionSelect.getSelectedItem(), (Difficulty) difficultySelect.getSelectedItem());
    }
}
