package filiposmexicos;

import javax.swing.*;
//psvd
public class main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Application");
        frame.setContentPane(new View().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
