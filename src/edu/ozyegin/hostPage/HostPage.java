package edu.ozyegin.hostPage;

import edu.ozyegin.hostPage.hostGamePage.HostGamePage;
import edu.ozyegin.hostPage.joinGamePage.JoinGamePage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

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

        hostGameButton.addActionListener(e -> {
            actionPerformed(e);
            frame.dispose();
        });
        joinGameButton.addActionListener(e -> {
            actionPerformed(e);
            frame.dispose();

        });
        exitButton.addActionListener(e -> {
          frame.dispose();
        });
    }

    public void actionPerformed(ActionEvent evt){
        if (evt.getSource() == hostGameButton) {
            new HostGamePage();
        }
        else if(evt.getSource() == joinGameButton){
            new JoinGamePage();
        }
    }

    public int decide(int i){
        return i;
    }
}


