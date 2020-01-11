package filiposmexicos;

import filiposmexicos.model.Model;
import filiposmexicos.model.Player;

import javax.swing.*;

/**
 * Controls the behaviour of the players, who is playing and other stuff.
 */
public class Controller {


    private final Model model;

    public Controller(Model model) {
        this.model = model;
    }

    public void buttonPressed(TicTakToeButton button) {
        final Player activePlayer = this.model.getActivePlayer();
        button.setText(activePlayer.getSymbol());
        button.setEnabled(false);
        this.model.playerMove(button.x, button.y);
        final boolean won = model.isWinner(activePlayer);
        if (won) {
            JOptionPane.showMessageDialog(null, activePlayer.getSymbol() + " has won the game");
            resetBoard();
        } else if (model.isDraw()) {
            JOptionPane.showMessageDialog(null, "Nobody has won the game :)");
        }

    }

    private void resetBoard() {
        this.model.resetBoard();
    }
}
