package edu.ozyegin.hostPage.joinGamePage;

import javax.swing.*;
import java.awt.*;

public class JoinGamePage extends JFrame {
    private JPanel joinGame;
    private JTextField ipAdress;
    private JTextField port;
    private JButton joinButton;
    private JFrame frame;

    public JoinGamePage() {
        frame = new JFrame("Login Frame");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(500, 400));
        frame.setResizable(false);

        frame.add(joinGame);
        frame.pack();
        frame.setVisible(true);

    }
}
