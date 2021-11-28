package edu.ozyegin.loginPage;
import edu.ozyegin.hostPage.HostPage;
import javax.swing.*;
import java.awt.*;

public class LoginPage extends JFrame{
    private JTextField username;
    private JButton nextButton;
    private JPanel loginpage;
    private JButton button1;
    private JFrame frame;

    public LoginPage(){
        frame = new JFrame("Login Frame");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(500, 400));
        frame.setResizable(false);

        frame.add(loginpage);
        frame.pack();
        frame.setVisible(true);

        nextButton.addActionListener(e -> {
            new HostPage();
            frame.dispose();
        });
    }

    public String returnUsername(){
        return username.toString();
    }

}
