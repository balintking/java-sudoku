package sudoku;

public class Main {
    public static void main(String[] args) {
        ApplicationFrame applicationFrame = new ApplicationFrame();

        //menu (select options, load game)
        GameController.BoardSize boardSize = GameController.BoardSize.MEDIUM;
        GameController.Difficulty difficulty = GameController.Difficulty.NORMAL;

        GameController gameController = new GameController(boardSize, difficulty);
        gameController.newGame();
        applicationFrame.add(gameController.getPanel());
        applicationFrame.pack();
    }
}
