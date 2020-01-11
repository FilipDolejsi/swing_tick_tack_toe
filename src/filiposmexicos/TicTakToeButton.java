package filiposmexicos;

import javax.swing.*;

public class TicTakToeButton extends JButton {
    public int x;
    public int y;

    public void setGridCoordinates(int column, int row) {
        x=column-1;
        y=row-1;
    }
}
