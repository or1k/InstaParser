package View;

import Test.ParcingFollowers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TestJTabbed {
    public static JTextField linkTextGeo = new JTextField(20);
    public static JTextField linkTextHashTag = new JTextField(20);
    public static JTextField linkTextAccount = new JTextField(20);
    public static JTextField userTextGeo = new JTextField(20);
    public static JTextField userTextHashTag = new JTextField(20);
    public static JTextField userTextAccount = new JTextField(20);
    public static JPasswordField passwordTextGeo = new JPasswordField(20);
    public static JPasswordField passwordTexthashTag = new JPasswordField(20);
    public static JPasswordField passwordTextAccount = new JPasswordField(20);
    public static JButton loginButtonGeo = new JButton("login");
    public static JButton loginButtonHashTag = new JButton("login");
    public static JButton loginButtonAccount = new JButton("login");
    public static JButton exitButton = new JButton("exit");
    public static JButton exitButton2 = new JButton("exit");
    public static JButton exitButton3 = new JButton("exit");
    public static JFrame frame;


    public static void main(String[] args) {
        frame = new JFrame("InstaParser");
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

        placeComponentsByGeo(panel1);
        placeComponentsByHashTag(panel2);
        placeComponentsByAccount(panel3);
        //panel2.add(new JButton("Button in panel 2 in tab 2"));
       // panel3.add(new JButton("Button in panel 3 in tab 3"));

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

    private static void placeComponentsByGeo(JPanel panel) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("User");
        userLabel.setBounds(10, 10, 80, 25);
        panel.add(userLabel);

        userTextGeo = new JTextField(20);
        userTextGeo.setBounds(200, 10, 160, 25);
        panel.add(userTextGeo);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 40, 80, 25);
        panel.add(passwordLabel);

        passwordTextGeo = new JPasswordField(20);
        passwordTextGeo.setBounds(200, 40, 160, 25);
        panel.add(passwordTextGeo);

        JLabel linkLabel = new JLabel("Link");
        linkLabel.setBounds(10, 70, 80, 25);
        panel.add(linkLabel);

        linkTextGeo = new JTextField(20);
        linkTextGeo.setBounds(200, 70, 160, 25);
        linkTextGeo.setToolTipText("Нужна ссылка откуда пиздить подписоту!!");
        panel.add(linkTextGeo);

        loginButtonGeo.setBounds(10, 110, 80, 25);
        panel.add(loginButtonGeo);
        loginButtonGeo.addActionListener(new TestJTabbed.ActionListenerForGeo());

        exitButton.setBounds(280, 110, 80, 25);
        panel.add(exitButton);
        exitButton.addActionListener(new TestJTabbed.ExitActionListener());
    }

    private static void placeComponentsByHashTag(JPanel panel) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("User");
        userLabel.setBounds(10, 10, 80, 25);
        panel.add(userLabel);

        userTextHashTag = new JTextField(20);
        userTextHashTag.setBounds(200, 10, 160, 25);
        panel.add(userTextHashTag);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 40, 80, 25);
        panel.add(passwordLabel);

        passwordTexthashTag = new JPasswordField(20);
        passwordTexthashTag.setBounds(200, 40, 160, 25);
        panel.add(passwordTexthashTag);

        JLabel linkLabel = new JLabel("Link");
        linkLabel.setBounds(10, 70, 80, 25);
        panel.add(linkLabel);

        linkTextHashTag = new JTextField(20);
        linkTextHashTag.setBounds(200, 70, 160, 25);
        linkTextHashTag.setToolTipText("Нужна ссылка откуда пиздить подписоту!!");
        panel.add(linkTextHashTag);

        loginButtonHashTag.setBounds(10, 110, 80, 25);
        panel.add(loginButtonHashTag);
        loginButtonHashTag.addActionListener(new TestJTabbed.ActionListenerForHashTag());

        exitButton2.setBounds(280, 110, 80, 25);
        panel.add(exitButton2);
        exitButton2.addActionListener(new TestJTabbed.ExitActionListener());
    }

    private static void placeComponentsByAccount(JPanel panel) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("User");
        userLabel.setBounds(10, 10, 80, 25);
        panel.add(userLabel);

        userTextAccount = new JTextField(20);
        userTextAccount.setBounds(200, 10, 160, 25);
        panel.add(userTextAccount);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 40, 80, 25);
        panel.add(passwordLabel);

        passwordTextAccount = new JPasswordField(20);
        passwordTextAccount.setBounds(200, 40, 160, 25);
        panel.add(passwordTextAccount);

        JLabel linkLabel = new JLabel("Link");
        linkLabel.setBounds(10, 70, 80, 25);
        panel.add(linkLabel);

        linkTextAccount = new JTextField(20);
        linkTextAccount.setBounds(200, 70, 160, 25);
        linkTextAccount.setToolTipText("Нужна ссылка откуда пиздить подписоту!!");
        panel.add(linkTextAccount);

        loginButtonAccount.setBounds(10, 110, 80, 25);
        panel.add(loginButtonAccount);
        loginButtonAccount.addActionListener(new TestJTabbed.ActionListenerForAccount());

        exitButton3.setBounds(280, 110, 80, 25);
        panel.add(exitButton3);
        exitButton3.addActionListener(new TestJTabbed.ExitActionListener());
    }

    public static class ActionListenerForGeo implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            loginButtonGeo = (JButton)e.getSource();
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

    public static class ActionListenerForHashTag implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            loginButtonHashTag = (JButton)e.getSource();
            frame.dispose();
            ParcingFollowers parcingFollowers = new ParcingFollowers();
            parcingFollowers.setup();
            try {
                parcingFollowers.Test("hashtag");
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
    }

    public static class ActionListenerForAccount implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            loginButtonAccount = (JButton)e.getSource();
            frame.dispose();
            ParcingFollowers parcingFollowers = new ParcingFollowers();
            parcingFollowers.setup();
            try {
                parcingFollowers.Test("account");
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
    }

    public static class ExitActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            exitButton = (JButton)e.getSource();
            exitButton2 = (JButton)e.getSource();
            exitButton3 = (JButton)e.getSource();
            System.exit(0);

        }
    }

}