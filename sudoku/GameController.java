package sudoku;

public class GameController {
    private GameBoardModel model;
    private GameBoardPanel panel;

    public GameController() {
        model = new GameBoardModel();
        panel = new GameBoardPanel();
    }

    public GameBoardPanel getPanel() {
        return panel;
    }

    /*
    logic here
     */
}
