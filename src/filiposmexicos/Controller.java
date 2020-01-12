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
        this.model.playerMove(button.x, button.y);
    }

    private void resetBoard() {
        this.model.resetBoard();
    }
}
