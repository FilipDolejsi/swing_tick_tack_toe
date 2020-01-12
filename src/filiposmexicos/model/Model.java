package filiposmexicos.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

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
    private final PropertyChangeSupport propertyChangeSupport;
    public static final String ACTIVE_PLAYER_CHANGED = "ACTIVE_PLAYER_CHANGED";
    public static final String GRID_CELL_UPDATED = "GRID_CELL_UPDATED";
    public static final String GAME_ENDED_WITH_WIN = "GAME_ENDED_WITH_WIN";
    public static final String GAME_ENDED_WITH_DRAW = "GAME_ENDED_WITH_DRAW";

    public Model() {
        this.playerX = new Player("x");
        this.playerO = new Player("o");
        this.activePlayer = this.playerX;
        grid = new Player[SIZE][SIZE];
        propertyChangeSupport = new PropertyChangeSupport(this);
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    /**
     * switch players
     */
    public void switchActivePlayer() {
        Player origActivePlayer = activePlayer;

        if (activePlayer.equals(playerX)) {
            activePlayer = playerO;
        } else {
            activePlayer = playerX;
        }

        propertyChangeSupport.firePropertyChange(ACTIVE_PLAYER_CHANGED, origActivePlayer, activePlayer);
    }

    public void playerMove(Player player, int x, int y) {
        if (grid[x][y] == null) {
            this.movesSoFar++;
        }
        final GridCellState origGridCellState = new GridCellState(x, y, grid[x][y]);
        grid[x][y] = player;
        final GridCellState newGridCellState = new GridCellState(x, y, grid[x][y]);
        propertyChangeSupport.firePropertyChange(GRID_CELL_UPDATED, origGridCellState, newGridCellState);

        if (player != null && isWinner(player)) {
            propertyChangeSupport.firePropertyChange(GAME_ENDED_WITH_WIN, 0, 1);
        }

        if (isDraw()) {
            propertyChangeSupport.firePropertyChange(GAME_ENDED_WITH_DRAW, 0, 1);
        }
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
                if (player.equals(grid[i][SIZE - 1 - i])) {
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
        return this.movesSoFar == SIZE * SIZE && !this.isWinner(playerX) && !this.isWinner(playerO);
    }

    public void resetBoard() {
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                playerMove(null, x, y);
            }
        }
        movesSoFar = 0;
    }

    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        this.propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
    }

    public static final class GridCellState {
        private final int x;
        private final int y;
        private final Player player;

        GridCellState(int x, int y, Player player) {
            this.x = x;
            this.y = y;
            this.player = player;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public Player getPlayer() {
            return player;
        }
    }
}

