package edu.ozyegin.hostPage;

import javax.swing.*;
import java.awt.*;

public class HostPage extends JFrame{
    private JPanel hostpage;
    private JButton hostGameButton;
    private JButton joinGameButton;
    private JButton exitButton;
    private JFrame frame;

    public HostPage(){
        frame = new JFrame("Login Frame");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(500, 400));
        frame.setResizable(false);

        frame.add(hostpage);
        frame.pack();
        frame.setVisible(true);


    }
}
