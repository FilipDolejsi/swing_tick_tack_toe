package filiposmexicos.model;

/**
 * State of the application -- Keeps track of the data
 */
public class Model {
    public static final int SIZE = 3;
    private final Player[][] grid;
    private final Player playerX;
    private final Player playerO;
    private Player activePlayer;
    private int movesSoFar = 0;

    public Model() {
        this.playerX = new Player("x");
        this.playerO = new Player("o");
        this.activePlayer = this.playerX;
        grid = new Player[SIZE][SIZE];
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    /**
     * switch players
     */
    public void switchActivePlayer() {
        if (activePlayer.equals(playerX)) {
            activePlayer = playerO;
        } else {
            activePlayer = playerX;
        }
    }

    public void playerMove(Player player, int x, int y) {
        if (grid[x][y] == null) {
            this.movesSoFar++;
        }
        grid[x][y] = player;
    }

    public void playerMove(int x, int y) {
        this.playerMove(activePlayer, x, y);
        switchActivePlayer();
    }

    public Player getPlayerMoved(int x, int y) {
        return grid[x][y];
    }

    public boolean isWinner(Player player) {
        // test whether the player wins in any column
        for (int x = 0; x < SIZE; x++) {
            int columnSum = 0;
            for (int y = 0; y < SIZE; y++) {
                //if all of them are the same, return true otherwise break the loop.
                if (player.equals(grid[x][y])) {
                    columnSum++;
                } else {
                    break;
                }
            }
            if (columnSum == SIZE) {
                System.out.println(String.format("Player %s wins in column %d", player, x));
                return true;
            }
        }
        // test whether the player wins in any row
        for (int y = 0; y < SIZE; y++) {
            int rowSum = 0;
            for (int x = 0; x < SIZE; x++) {
                //if all of them are the same, return true otherwise break the loop.
                if (player.equals(grid[x][y])) {
                    rowSum++;
                } else {
                    break;
                }
            }
            if (rowSum == SIZE) {
                System.out.println(String.format("Player %s wins in row %d", player, y));
                return true;
            }
        }
        {// test whether the player wins in any negative diagonal
            int diagonalSum = 0;
            for (int y = 0; y < SIZE; y++) {
                //if all of them are the same, return true otherwise break the loop.
                if (player.equals(grid[y][y])) {
                    diagonalSum++;
                } else {
                    break;
                }
            }

            if (diagonalSum == SIZE) {
                System.out.println(String.format("Player %s wins in a negative diagonal", player));
                return true;
            }
        }
        {
            // test whether the player wins in any positive diagonal
            int diagonalSum = 0;
            for (int i = 0; i < SIZE; i++) {
                //if all of them are the same, return true otherwise break the loop.
                if (player.equals(grid[i][SIZE-1-i])) {
                    diagonalSum++;
                } else {
                    break;
                }
            }

            if (diagonalSum == SIZE) {
                System.out.println(String.format("Player %s wins in a positive diagonal", player));
                return true;
            }
        }
        return false;
    }

    public Player getPlayerX() {
        return this.playerX;
    }

    public Player getPlayerO() {
        return this.playerO;
    }

    public boolean isDraw() {
        return this.movesSoFar == SIZE*SIZE && !this.isWinner(playerX) && !this.isWinner(playerO);
    }

    public void resetBoard() {
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                grid[x][y]=null;
            }
        }
        movesSoFar = 0;
    }
}

