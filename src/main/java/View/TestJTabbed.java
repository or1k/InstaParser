package View;

import Test.ParcingFollowers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class TestJTabbed {
    public static JTextField linkText;
    public static JTextField userText;
    public static JTextField order;
    public static JTextField countSubscribers;
    public static JPasswordField passwordText;
    public static JButton loginButton = new JButton("login");
    public static JButton exitButton = new JButton("exit");
    public static String filePath;
    public static JTextField path; //Assume this is the text box you placed beside browse button
    public static JButton btnBrowse = new JButton("Save folder");


    public static JFrame frame;

    public static JRadioButton jRadioButton1;
    public static JRadioButton jRadioButton2;
    public static JRadioButton jRadioButton3;


    public static void main(String[] args) {
        frame = new JFrame("InstaParser");
        frame.setPreferredSize(new Dimension(400,330));
        // handle window close

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });


        // set up panels with buttons

        JPanel panel1 = new JPanel();
        placeComponentsByGeo(panel1);


        // display
        frame.getContentPane().add(panel1);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void placeComponentsByGeo(JPanel panel) {
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

        JLabel orderLabel = new JLabel("Quantity");
        orderLabel.setBounds(10, 100, 80, 25);
        panel.add(orderLabel);

        order = new JTextField(20);
        order.setBounds(200, 100, 160, 25);
        order.setToolTipText("Количество человек!!");
        panel.add(order);

        JLabel minSubscribers = new JLabel("minSubscribers");
        minSubscribers.setBounds(10, 130, 100, 25);
        panel.add(minSubscribers);

        countSubscribers = new JTextField(20);
        countSubscribers.setBounds(200, 130, 160, 25);
        countSubscribers.setToolTipText("минимальное кол-во подписчиков");
        panel.add(countSubscribers);

        jRadioButton1 = new JRadioButton();
        jRadioButton1.setText("Geo");
        jRadioButton1.setBounds(10, 160, 50, 20);
        panel.add(jRadioButton1);

        jRadioButton2 = new JRadioButton();
        jRadioButton2.setText("HashTag");
        jRadioButton2.setBounds(80, 160, 80, 20);
        panel.add(jRadioButton2);

        jRadioButton3 = new JRadioButton();
        jRadioButton3.setText("Account");
        jRadioButton3.setBounds(160, 160, 80, 20);
        panel.add(jRadioButton3);


        btnBrowse.setBounds(140, 190, 100, 25);
        panel.add(btnBrowse);
        btnBrowse.addActionListener(new TestJTabbed.ActionListenerSaveReport());

        path = new JTextField(20);
        path.setBounds(10, 220, 350, 25);
        path.setEditable(false);
        path.setToolTipText("Нужна ссылка откуда пиздить подписоту!!");
        panel.add(path);

        loginButton.setBounds(10, 250, 80, 25);
        panel.add(loginButton);
        loginButton.addActionListener(new TestJTabbed.ActionListenerForGeo());

        exitButton.setBounds(280, 250, 80, 25);
        panel.add(exitButton);
        exitButton.addActionListener(new TestJTabbed.ExitActionListener());

    }

//

    public static class ActionListenerSaveReport extends Component implements ActionListener{
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == btnBrowse)
            {
               final JFileChooser chooser = new JFileChooser(new File("C:\\")); //Downloads Directory as default
                chooser.setDialogTitle("Select Location");
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                chooser.setAcceptAllFileFilterUsed(true);

                if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION)
                {
                    filePath = chooser.getSelectedFile().getPath();
                    path.setText(filePath);
                }
            }
        }
    }

    public static class ActionListenerForGeo implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            loginButton = (JButton)e.getSource();
            frame.dispose();
            ParcingFollowers parcingFollowers = new ParcingFollowers();
            parcingFollowers.setup();
            if(jRadioButton1.isSelected()) {
                try {
                    parcingFollowers.Test("geo");
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }else if(jRadioButton2.isSelected()){
                try {
                    parcingFollowers.Test("hashtag");
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }else if (jRadioButton3.isSelected()){
                try {
                    parcingFollowers.Test("account");
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

//

    public static class ExitActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            exitButton = (JButton)e.getSource();
            System.exit(0);

        }
    }

}