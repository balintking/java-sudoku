package sudoku;

/**
 * Controller class responsible for the game logic
 */
public class GameController {
    private BoardSize boardSize;
    private Difficulty difficulty;

    private GameBoardModel model;
    private GameBoardPanel panel;

    /**
     * Constants of different board sizes
     */
    public enum BoardSize {
        SMALL(4, 2), MEDIUM(9, 3), LARGE(16, 4);

        int gridSize;
        int boxSize;

        BoardSize(int gridSize, int boxSize) {
            this.gridSize = gridSize;
            this.boxSize = boxSize;
        }
    }

    /**
     * Constants of difficulty levels
     */
    public enum Difficulty {
        EASY, NORMAL, HARD
    }

    public GameController(BoardSize boardSize, Difficulty difficulty) {
        this.boardSize = boardSize;
        this.difficulty = difficulty;
    }

    public GameBoardPanel getPanel() {
        return panel;
    }

    public void newGame() {
        model = new GameBoardModel(boardSize);
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

        panel = new GameBoardPanel(boardSize, model);
    }

    /*
    logic here
     */
}
