package sudoku;

import sudoku.GameController.BoardDimension;
import sudoku.GameController.Difficulty;

import javax.swing.*;
import java.io.*;

public class SudokuGame {
    private static JFrame menuFrame;
    private static JFrame gameFrame;

    private static GameController gameController;

    public static void main(String[] args) {
        menuFrame = new MenuFrame();
        menuFrame.setVisible(true);
    }

    public static void showGame() {
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

    public static void newGame(BoardDimension boardDimension, Difficulty difficulty) {
        gameController = new GameController(boardDimension, difficulty);
        gameController.newGame();
        gameFrame = new GameFrame(gameController.getModel().getBoardDimension(), gameController.getModel().getDifficulty(), gameController.getPanel());
        showGame();
    }

    public static void loadGame(File file) {
        GameBoardModel model = null;
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            model = (GameBoardModel) objectInputStream.readObject();

        } catch (FileNotFoundException ex) {
            System.out.println("Load unsuccessful: File not found");
        } catch (IOException ex) {
            System.out.println("Load unsuccessful: IOException");
        } catch (ClassNotFoundException e) {
            System.out.println("Load unsuccessful: Class not found");
        }

        if (model != null) {
            gameController = new GameController(model);
            gameFrame = new GameFrame(gameController.getModel().getBoardDimension(), gameController.getModel().getDifficulty(), gameController.getPanel());
            showGame();
        } else {
            System.out.println("Load unsuccessful");
        }
    }

    public static void saveGame(File file) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(gameController.getModel());

        } catch (FileNotFoundException ex) {
            System.out.println("Save unsuccessful: File not found");
        } catch (IOException ex) {
            System.out.println("Save unsuccessful: IOException");
            ex.printStackTrace(System.out);
        }
    }

    public static boolean isAllCellFilled() {
        return gameController.isAllCellFilled();
    }
}
