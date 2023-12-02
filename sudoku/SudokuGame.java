package sudoku;

public class SudokuGame {
    public static void main(String[] args) {
        ApplicationFrame applicationFrame = new ApplicationFrame();

        //menu (select options, load game)
        GameController.BoardDimension boardDimension = GameController.BoardDimension.MEDIUM;
        GameController.Difficulty difficulty = GameController.Difficulty.NORMAL;


        GameController gameController = new GameController(boardDimension, difficulty);
        gameController.newGame();
        applicationFrame.add(gameController.getPanel());
        applicationFrame.pack();
    }
}
