package sudoku;

/**
 * Controller class responsible for the game logic
 */
public class GameController {
    private int gridSize;
    private Difficulty difficulty;

    private GameBoardModel model;
    private GameBoardPanel panel;

    public enum Difficulty {
        EASY, NORMAL, HARD
    }

    public GameController(int gridSize, Difficulty difficulty) {
        this.gridSize = gridSize;
        this.difficulty = difficulty;
    }

    public GameBoardPanel getPanel() {
        return panel;
    }

    public void newGame() {
        model = new GameBoardModel(gridSize);
        //load cells
        model.addCell(1, 1, new Cell(1, true));
        model.addCell(1, 2, new Cell(2, true));
        model.addCell(1, 3, new Cell(3, true));
        model.addCell(2, 1, new Cell(4, true));
        model.addCell(2, 2, new Cell(5, true));
        model.addCell(2, 3, new Cell(6, true));
        model.addCell(3, 1, new Cell(7, true));
        model.addCell(3, 2, new Cell(8, true));
        model.addCell(3, 3, new Cell(9, true));

        panel = new GameBoardPanel(gridSize, model);
    }

    /*
    logic here
     */
}
