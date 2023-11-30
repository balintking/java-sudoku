package sudoku;

public class Main {
    public static void main(String[] args) {
        ApplicationFrame applicationFrame = new ApplicationFrame();

        //menu (select options, load game)
        int gridSize = 3;
        GameController.Difficulty difficulty = GameController.Difficulty.NORMAL;

        GameController gameController = new GameController(gridSize, difficulty);
        gameController.newGame();
        applicationFrame.add(gameController.getPanel());
        applicationFrame.pack();
    }

    /*
     main game control
     display menu and board
     */

}
