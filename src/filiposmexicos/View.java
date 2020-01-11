package filiposmexicos;

import filiposmexicos.model.Model;

import javax.swing.*;
import java.awt.event.InputMethodListener;

public class View extends JFrame {
    //we linked the instance of the models so we can work with them
    private Controller controller;
    //state of the view
    private filiposmexicos.model.Model model;
    //different buttons put in from swing
    JPanel panel;
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

        button11.setGridCoordinates(1, 1);
        button12.setGridCoordinates(1, 2);
        button13.setGridCoordinates(1, 3);

        button21.setGridCoordinates(2, 1);
        button22.setGridCoordinates(2, 2);
        button23.setGridCoordinates(2, 3);

        button31.setGridCoordinates(3, 1);
        button32.setGridCoordinates(3, 2);
        button33.setGridCoordinates(3, 3);


        //action listener :))
        button11.addActionListener(actionEvent -> controller.buttonPressed(button11));

        button12.addActionListener(actionEvent -> controller.buttonPressed(button12));

        button13.addActionListener(actionEvent -> controller.buttonPressed(button13));

        button21.addActionListener(actionEvent -> controller.buttonPressed(button21));

        button22.addActionListener(actionEvent -> controller.buttonPressed(button22));

        button23.addActionListener(actionEvent -> controller.buttonPressed(button23));

        button31.addActionListener(actionEvent -> controller.buttonPressed(button31));

        button32.addActionListener(actionEvent -> controller.buttonPressed(button32));

        button33.addActionListener(actionEvent -> controller.buttonPressed(button33));
    }





}
