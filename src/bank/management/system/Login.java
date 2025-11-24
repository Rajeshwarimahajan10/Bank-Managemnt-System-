package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {

    JLabel label1 , label2 , label3;

    JTextField textField2;

    JPasswordField passwordField3;

    JButton button1, button2 , button3 ;

    Login(){
        super("Bank Management System");


        label1 = new JLabel("WELCOME TO ATM ");
        label1.setForeground(Color.white);
        label1.setFont(new Font("AvantGrade" , Font.BOLD,30));
        label1.setBounds(260,125,450,40);
        add(label1);

        label2 = new JLabel("Card No:");
        label2.setFont(new Font("Ralway", Font.BOLD, 28));
        label2.setForeground(Color.white);
        label2.setBounds(150, 190 , 375,30);
        add(label2);

        textField2 = new JTextField(15);
        textField2.setBounds(325,190,230,30);
        textField2.setFont(new Font("Arial", Font.BOLD,14));
        add(textField2);

        label3 = new JLabel("PIN :");
        label3.setFont(new Font("Ralway", Font.BOLD, 28));
        label3.setForeground(Color.white);
        label3.setBounds(150, 250 , 375,30);
        add(label3);

        passwordField3 = new JPasswordField(15);
        passwordField3.setBounds(325,250,230,30);
        passwordField3.setFont(new Font("Arial", Font.BOLD, 14));
        add(passwordField3);

        button1 = new JButton("SIGN IN");
        button1.setFont(new Font("Arial", Font.BOLD ,14));
        button1.setBackground(Color.black);
        button1.setForeground(Color.white);
        button1.setBounds(320,300,100,30);
        button1.addActionListener(this);
        add(button1);

        button2 = new JButton("CLEAR");
        button2.setFont(new Font("Arial", Font.BOLD ,14));
        button2.setForeground(Color.white);
        button2.setBackground(Color.black);
        button2.setBounds(450,300,100,30);
        button2.addActionListener(this);
        add(button2);


        button3 = new JButton("SIGN UP");
        button3.setFont(new Font("Arial", Font.BOLD ,14));
        button3.setForeground(Color.white);
        button3.setBackground(Color.black);
        button3.setBounds(325,350,225,30);
        button3.addActionListener(this);
        add(button3);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/Background.jpeg"));
        Image i2 = i1.getImage().getScaledInstance(850 , 480, Image.SCALE_DEFAULT);ImageIcon i3 = new ImageIcon(i2);
        JLabel i1mage = new JLabel(i3);
        i1mage.setBounds(0,0 ,850,450);
        add(i1mage);

        setLayout(null);
        setSize(850 , 480);
        setLocation(450 , 200 );
        setUndecorated(true);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       try{
           if (e.getSource()==button1){
               Con c = new Con();
               String cardno = textField2.getText();
               String pin = new String(passwordField3.getPassword());
               String q = "select * from login where card_number = '"+cardno+"' and pin = '"+pin+"'";
               ResultSet resultSet = c.statement.executeQuery(q);
               if (resultSet.next()) {
                   System.out.println("✅ Login success for card: " + cardno);
                   setVisible(false);
                   new main_class(pin);
               } else {
                   JOptionPane.showMessageDialog(null,"Incorrect Card Number or PIN");
               }




           }else if (e.getSource() == button2){
               textField2.setText("");
               passwordField3.setText("");
           } else if (e.getSource() == button3){
              new Signup();
              setVisible(false);
           }
       }catch(Exception E){
           E.printStackTrace();
       }
    }

    public static void main(String[] args) {
        new Login() ;

    }
}
