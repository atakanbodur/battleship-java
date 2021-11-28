package edu.ozyegin.hostPage.hostGamePage;

import javax.swing.*;
import java.awt.*;

public class HostGamePage extends JFrame{
    private JPanel hostGame;
    private JButton hostButton;
    private JTextField portNo;
    private JFrame frame;

    public HostGamePage() {
        frame = new JFrame("Host Game Frame");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(500, 400));
        frame.setResizable(false);

        frame.add(hostGame);
        frame.pack();
        frame.setVisible(true);

    }

    public int getPortNumber(){
        return Integer.parseInt(portNo.toString());
    }
}
