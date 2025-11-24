package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class Deposit extends JFrame implements ActionListener {

    String pin;

    TextField textField;

    JButton b1,b2;


    Deposit(String pin) {
        this.pin = pin ;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm.jpeg"));
        Image i2 = i1. getImage().getScaledInstance(1550,830,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(0,0,1550,830);
        add(l3);

        JLabel label1 = new JLabel("Enter Amount you want to Deposit");
        label1.setForeground(Color.white);
        label1.setFont(new Font("System",Font.BOLD,22));
        label1.setBounds(590,320,400,35);
        l3.add(label1);

        textField = new TextField();
        textField.setBounds(590,370,320,25);
        textField.setFont(new Font("Raleway",Font.BOLD,22));
        textField.setBackground(Color.WHITE);
        textField.setForeground(Color.black);
        l3.add(textField);

        b1 = new JButton("Deposit");
        b1.setBounds(910,460,150,25);
        b1.setFont(new Font("Raleway",Font.BOLD,12));
        b1.setBackground(Color.WHITE);
        b1.setForeground(Color.black);
        b1.addActionListener(this);
        l3.add(b1);

        b2 = new JButton("Back");
        b2.setBounds(910,490,150,25);
        b2.setFont(new Font("Raleway",Font.BOLD,12));
        b2.setBackground(Color.WHITE);
        b2.setForeground(Color.black);
        b1.addActionListener(this);
        l3.add(b2);

        setLayout(null);
        setSize(1550, 1080);
        setLocation(0, 0);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String amount = textField.getText();
            Date date = new Date();
            if (e.getSource()==b1){
                if(textField.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"Please Enter the Amount yow want to deposit");
                }else {
                    Con c = new Con();
                    c.statement.executeUpdate("insert into bank values('"+pin+"','"+date+"','Deposit','"+amount+"')");
                    JOptionPane.showMessageDialog(null,"Rs."+amount+ "Deposit Successfully");
                    setVisible(false);
                    new main_class(pin);
                }
            } else if (e.getSource()==b2) {
                setVisible(false);
                new main_class(pin);
            }
        } catch (Exception E){
            E.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Deposit("");
    }

}


