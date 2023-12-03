package sudoku;

import javax.swing.*;

public class SudokuGame {
    private static JFrame menuFrame;
    private static JFrame gameFrame;

    private static GameController gameController;

    public static void main(String[] args) {
        menuFrame = new MenuFrame();
        menuFrame.setVisible(true);
    }

    public static void showGame(GameController.BoardDimension boardDimension, GameController.Difficulty difficulty) {
        gameController = new GameController(boardDimension, difficulty);
        gameController.newGame();
        gameFrame = new GameFrame(gameController.getPanel());
        if (menuFrame.isVisible()) {
            menuFrame.setVisible(false);
            gameFrame.setVisible(true);
        }
    }

    public static void showMenu() {
        if (gameFrame.isVisible()) {
            gameFrame.setVisible(false);
            menuFrame.setVisible(true);
        }
    }
}
