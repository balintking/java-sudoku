package sudoku;

import sudoku.GameController.BoardDimension;
import sudoku.GameController.Difficulty;

import javax.swing.*;
import java.io.*;
import java.util.logging.Logger;

/**
 * Main class
 */
public class SudokuGame {
    private static JFrame menuFrame;
    private static JFrame gameFrame;

    private static GameController gameController;

    public static final Logger logger = Logger.getLogger(SudokuGame.class.getName());

    /**
     * Starting point of the game
     * @param args args
     */
    public static void main(String[] args) {
        menuFrame = new MenuFrame();
        menuFrame.setVisible(true);
    }

    /**
     * Returns the maximum value of the current game
     * @return Maximum value
     */
    public static int getMaxValue() {
        return gameController.getModel().getBoardDimension().maxValue;
    }

    /**
     * Changes screen to game
     */
    public static void showGame() {
        if (menuFrame.isVisible()) {
            menuFrame.setVisible(false);
            gameFrame.setVisible(true);
        }
    }

    /**
     * Changes screen to menu
     */
    public static void showMenu() {
        if (gameFrame.isVisible()) {
            gameFrame.setVisible(false);
            menuFrame.setVisible(true);
        }
    }

    /**
     * Creates new game
     * @param boardDimension Dimension of the new board
     * @param difficulty Difficulty of the new game
     */
    public static void newGame(BoardDimension boardDimension, Difficulty difficulty) {
        gameController = new GameController(boardDimension, difficulty);
        gameController.newGame();
        gameFrame = new GameFrame(gameController.getModel().getBoardDimension(), gameController.getModel().getDifficulty(), gameController.getPanel());
        showGame();
    }

    /**
     * Loads the state of a saved game from file
     * @param file Game save
     */
    public static void loadGame(File file) {
        GameBoardModel model = null;
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            model = (GameBoardModel) objectInputStream.readObject();

        } catch (FileNotFoundException ex) {
            logger.info("Load unsuccessful: File not found");
        } catch (IOException ex) {
            logger.info("Load unsuccessful: IOException");
        } catch (ClassNotFoundException e) {
            logger.info("Load unsuccessful: Class not found");
        }

        if (model != null) {
            gameController = new GameController(model);
            gameFrame = new GameFrame(gameController.getModel().getBoardDimension(), gameController.getModel().getDifficulty(), gameController.getPanel());
            showGame();
        } else {
            logger.info("Load unsuccessful");
        }
    }

    /**
     * Saves the game state to file
     * @param file Save file
     */
    public static void saveGame(File file) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(gameController.getModel());

        } catch (FileNotFoundException ex) {
            logger.info("Save unsuccessful: File not found");
        } catch (IOException ex) {
            logger.info("Save unsuccessful: IOException");
        }
    }

    /**
     * Checks the given solution to the puzzle
     * @return Int representing result of the check (0 - empty cells, -1 - invalid, 1 - valid)
     */
    public static int checkSolution() {
        if(!gameController.isAllCellFilled()) {
            return 0;
        } else if (!gameController.isBoardValid()) {
            return -1;
        }
        return 1;
    }
}
