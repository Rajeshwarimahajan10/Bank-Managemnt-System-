package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class balanceEnquiry extends JFrame implements ActionListener {

    JLabel label2;

    JButton b1;

    String pin;

    balanceEnquiry(String pin) {
        this.pin = pin;


        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm.jpeg"));
        Image i2 = i1.getImage().getScaledInstance(1550, 830, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(0, 0, 1550, 830);
        add(l3);

        JLabel label1 = new JLabel("Your current balance is Rs.");
        label1.setForeground(Color.white);
        label1.setFont(new Font("System", Font.BOLD, 20));
        label1.setBounds(520, 330, 700, 35);
        l3.add(label1);

        label2 = new JLabel();
        label2.setForeground(Color.white);
        label2.setFont(new Font("System", Font.BOLD, 22));
        label2.setBounds(560, 370, 700, 35);
        l3.add(label2);


        b1 = new JButton("Back");
        b1.setBounds(910, 490, 150, 25);
        b1.setFont(new Font("Raleway", Font.BOLD, 12));
        b1.setBackground(Color.WHITE);
        b1.setForeground(Color.black);
        b1.addActionListener(this);
        l3.add(b1);

        int balance = 0;

        try {
            Con c = new Con();
            ResultSet resultSet = c.statement.executeQuery("select * from bank where pin = '" + pin + "'");
            while (resultSet.next()) {
                String amt = resultSet.getString("amount");
                if (amt == null || amt.trim().equals("")) {
                    amt = "0";
                }
                if (resultSet.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(amt);
                } else {
                    balance -= Integer.parseInt(amt);
                }
            }

            /* while (resultSet.next()) {
                if (resultSet.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(resultSet.getString("amount"));
                } else {
                    balance -= Integer.parseInt(resultSet.getString("amount"));
                }
            } */
        } catch (Exception e) {
            e.printStackTrace();
        }


        label2.setText("" + balance);

        setLayout(null);
        setSize(1550, 1080);
        setLocation(0, 0);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            setVisible(false);
            new main_class(pin);
        }



    public static void main(String[] args) {
        new balanceEnquiry("");
    }
}
