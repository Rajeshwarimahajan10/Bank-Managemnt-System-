package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class main_class extends JFrame implements ActionListener {

    JButton b1,b2,b3,b4,b5,b6,b7;

    String pin;

    main_class(String pin){

        this.pin = pin;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm.jpeg"));
        Image i2 = i1. getImage().getScaledInstance(1550,830,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(0,0,1550,830);
        add(l3);

        JLabel label = new JLabel("Please Select Your Transaction");
        label.setBounds(590,320,700,35);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("System",Font.BOLD,28));
        l3.add(label);

        b1 = new JButton("Deposit");
        b1.setFont(new Font("Raleway",Font.BOLD,14));
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.setBounds(500,398,150,25);
        b1.addActionListener(this);
        l3.add(b1);


        b2 = new JButton("Cash Withdraw");
        b2.setFont(new Font("Raleway",Font.BOLD,14));
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        b2.setBounds(910,398,150,25);
        b2.addActionListener(this);
        l3.add(b2);

        b3 = new JButton("Fast Cash");
        b3.setFont(new Font("Raleway",Font.BOLD,14));
        b3.setBackground(Color.BLACK);
        b3.setForeground(Color.WHITE);
        b3.setBounds(500,428,150,25);
        b3.addActionListener(this);
        l3.add(b3);

        b4 = new JButton("Mini Statement");
        b4.setFont(new Font("Raleway",Font.BOLD,14));
        b4.setBackground(Color.BLACK);
        b4.setForeground(Color.WHITE);
        b4.setBounds(910,428,150,25);
        b4.addActionListener(this);
        l3.add(b4);

        b5 = new JButton("PIN Change");
        b5.setFont(new Font("Raleway",Font.BOLD,14));
        b5.setBackground(Color.BLACK);
        b5.setForeground(Color.WHITE);
        b5.setBounds(500,458,150,25);
        b5.addActionListener(this);
        l3.add(b5);

        b6 = new JButton("Check Balance");
        b6.setFont(new Font("Raleway",Font.BOLD,14));
        b6.setBackground(Color.BLACK);
        b6.setForeground(Color.WHITE);
        b6.setBounds(910,458,150,25);
        b6.addActionListener(this);
        l3.add(b6);

        b7 = new JButton("Exit");
        b7.setFont(new Font("Raleway",Font.BOLD,14));
        b7.setBackground(Color.BLACK);
        b7.setForeground(Color.WHITE);
        b7.setBounds(910,488,150,25);
        b7.addActionListener(this);
        l3.add(b7);


        setLayout(null);
        setSize(1550,1080);
        setLocation(0,0);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==b1){
            new Deposit(pin);
            setVisible(false);
        } else if (e.getSource()==b7) {
            System.exit(1);
        } else if (e.getSource()==b2){
            new cashWithdraw(pin);
            setVisible(false);
        }else if (e.getSource()==b6){
            new balanceEnquiry(pin);
            setVisible(false);
        } else if (e.getSource()==b3) {
            new FastCash(pin);
            setVisible(false);
        } else if (e.getSource()==b5) {
            new Pin(pin);
            setVisible(false);
        } else if (e.getSource()==b4) {
            new miniStatement(pin);
        }
    }

    public static void main(String[] args) {
        new main_class("");
    }
}
