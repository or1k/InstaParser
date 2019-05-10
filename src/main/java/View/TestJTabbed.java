package View;

import Test.ParcingFollowers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TestJTabbed {
    public static JTextField linkText = new JTextField(20);
    public static JTextField userText = new JTextField(20);
    public static JPasswordField passwordText = new JPasswordField(20);
    public static JButton loginButton = new JButton("login");
    public static JButton registerButton = new JButton("exit");
    public static JFrame frame;


    public static void main(String[] args) {
        frame = new JFrame("JTabDemo1");
        frame.setPreferredSize(new Dimension(400,210));
        // handle window close

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        // set up panels with buttons

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();

        placeComponents(panel1);
        panel2.add(new JButton("Button in panel 2 in tab 2"));
        panel3.add(new JButton("Button in panel 3 in tab 3"));

        // set up JTabbedPane object and add panels

        JTabbedPane jtp = new JTabbedPane();

        jtp.add("By Geo", panel1);
        jtp.add("By HashTag", panel2);
        jtp.add("By Link", panel3);

        // display
        frame.getContentPane().add(jtp);


        frame.pack();
        frame.setVisible(true);
    }

    public static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("User");
        userLabel.setBounds(10, 10, 80, 25);
        panel.add(userLabel);

        userText = new JTextField(20);
        userText.setBounds(200, 10, 160, 25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 40, 80, 25);
        panel.add(passwordLabel);

        passwordText = new JPasswordField(20);
        passwordText.setBounds(200, 40, 160, 25);
        panel.add(passwordText);

        JLabel linkLabel = new JLabel("Link");
        linkLabel.setBounds(10, 70, 80, 25);
        panel.add(linkLabel);

        linkText = new JTextField(20);
        linkText.setBounds(200, 70, 160, 25);
        linkText.setToolTipText("Нужна ссылка откуда пиздить подписоту!!");
        panel.add(linkText);

        loginButton.setBounds(10, 110, 80, 25);
        panel.add(loginButton);
        loginButton.addActionListener(new LoginFrame2.TestActionListener());

        registerButton.setBounds(280, 110, 80, 25);
        panel.add(registerButton);
        registerButton.addActionListener(new LoginFrame2.ExitActionListener());
    }

    public static class TestActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            loginButton = (JButton)e.getSource();
            frame.dispose();
            ParcingFollowers parcingFollowers = new ParcingFollowers();
            parcingFollowers.setup();
            try {
                parcingFollowers.Test("geo");
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
    }

}