package filiposmexicos;

import filiposmexicos.model.Model;
import filiposmexicos.model.Player;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

public class View extends JFrame {
    //we linked the instance of the models so we can work with them
    private Controller controller;
    //state of the view
    private filiposmexicos.model.Model model;
    //different buttons put in from swing
    JPanel panel;
    private final List<TicTakToeButton> allButtons;
    private TicTakToeButton button11;
    private TicTakToeButton button13;
    private TicTakToeButton button12;
    private TicTakToeButton button31;
    private TicTakToeButton button32;
    private TicTakToeButton button21;
    private TicTakToeButton button22;
    private TicTakToeButton button23;
    private TicTakToeButton button33;

    public View() {
        //create instance of the model so it can save data somewhere in this machine
        this.model = new Model();
        //create instance of the controller
        this.controller = new Controller(this.model);

        this.model.addPropertyChangeListener(Model.GRID_CELL_UPDATED, propertyChangeEvent -> {
            final Model.GridCellState newValue = (Model.GridCellState) propertyChangeEvent.getNewValue();
            this.updateButton(newValue);
        });

        this.model.addPropertyChangeListener(Model.GAME_ENDED_WITH_WIN, propertyChangeEvent -> this.gameEndedWithWin());
        this.model.addPropertyChangeListener(Model.GAME_ENDED_WITH_DRAW, propertyChangeEvent -> this.gameEndedWithDraw());

        button11.setGridCoordinates(1, 1);
        button12.setGridCoordinates(1, 2);
        button13.setGridCoordinates(1, 3);

        button21.setGridCoordinates(2, 1);
        button22.setGridCoordinates(2, 2);
        button23.setGridCoordinates(2, 3);

        button31.setGridCoordinates(3, 1);
        button32.setGridCoordinates(3, 2);
        button33.setGridCoordinates(3, 3);

        this.allButtons = Arrays.asList(button11, button12, button13,
                button21, button22, button23,
                button31, button32, button33);

        // listen to button clicks and call controller
        this.allButtons.forEach(button -> button.addActionListener(actionEvent -> controller.buttonPressed(button)));
    }

    private void gameEndedWithWin() {
        final Player activePlayer = this.model.getActivePlayer();
        JOptionPane.showMessageDialog(this, activePlayer.getSymbol() + " has won the game");
        model.resetBoard();
    }

    private void gameEndedWithDraw() {
        JOptionPane.showMessageDialog(this, "Nobody has won the game :)");
        model.resetBoard();
    }

    private void updateButton(Model.GridCellState changedGridCellState) {
        final TicTakToeButton button = this.findButton(changedGridCellState);

        if (changedGridCellState.getPlayer() != null) {
            button.setText(changedGridCellState.getPlayer().getSymbol());
            button.setEnabled(false);
        } else {
            button.setText("");
            button.setEnabled(true);
        }
    }

    private TicTakToeButton findButton(Model.GridCellState gridCell) {
        for (int i = 0; i < this.allButtons.size(); i++) {
            final TicTakToeButton ticTakToeButton = this.allButtons.get(i);
            if (ticTakToeButton.x == gridCell.getX() && ticTakToeButton.y == gridCell.getY()) {
                return ticTakToeButton;
            }
        }
        throw new IllegalArgumentException("Wrong grid cell");
    }


}
