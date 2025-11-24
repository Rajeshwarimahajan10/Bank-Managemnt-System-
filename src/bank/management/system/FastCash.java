package bank.management.system;

import com.mysql.cj.protocol.Resultset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class FastCash extends JFrame implements ActionListener {
    JButton b1,b2,b3,b4,b5,b6,b7;

    String pin;

    FastCash(String pin){
        this.pin=pin;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm.jpeg"));
        Image i2 = i1. getImage().getScaledInstance(1550,830,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(0,0,1550,830);
        add(l3);

        JLabel label = new JLabel(" Select Withdrawal Amount ");
        label.setBounds(600,330,700,35);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("System",Font.BOLD,24));
        l3.add(label);

        b1 = new JButton("RS. 200");
        b1.setFont(new Font("Raleway",Font.BOLD,14));
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.setBounds(500,398,150,25);
        b1.addActionListener(this);
        l3.add(b1);


        b2 = new JButton("RS. 500");
        b2.setFont(new Font("Raleway",Font.BOLD,14));
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        b2.setBounds(910,398,150,25);
        b2.addActionListener(this);
        l3.add(b2);

        b3 = new JButton("Rs. 1000");
        b3.setFont(new Font("Raleway",Font.BOLD,14));
        b3.setBackground(Color.BLACK);
        b3.setForeground(Color.WHITE);
        b3.setBounds(500,428,150,25);
        b3.addActionListener(this);
        l3.add(b3);

        b4 = new JButton("Rs. 2000");
        b4.setFont(new Font("Raleway",Font.BOLD,14));
        b4.setBackground(Color.BLACK);
        b4.setForeground(Color.WHITE);
        b4.setBounds(910,428,150,25);
        b4.addActionListener(this);
        l3.add(b4);

        b5 = new JButton("RS. 5000");
        b5.setFont(new Font("Raleway",Font.BOLD,14));
        b5.setBackground(Color.BLACK);
        b5.setForeground(Color.WHITE);
        b5.setBounds(500,458,150,25);
        b5.addActionListener(this);
        l3.add(b5);

        b6 = new JButton("RS. 10000");
        b6.setFont(new Font("Raleway",Font.BOLD,14));
        b6.setBackground(Color.BLACK);
        b6.setForeground(Color.WHITE);
        b6.setBounds(910,458,150,25);
        b6.addActionListener(this);
        l3.add(b6);

        b7 = new JButton("Back");
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

        // Only the BACK button should go to main menu
        if (e.getSource() == b7) {
            setVisible(false);
            new main_class(pin);
            return;
        }

        // All other buttons are WITHDRAW buttons
        String amount = ((JButton)e.getSource()).getText().replaceAll("\\D+", "");

        Con c = new Con();
        Date date = new Date();

        try {
            ResultSet rs = c.statement.executeQuery("SELECT * FROM bank WHERE pin = '" + pin + "'");
            int balance = 0;

            while (rs.next()) {
                String amt = rs.getString("amount");
                if (amt == null || amt.trim().equals("")) amt = "0";

                if (rs.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(amt);
                } else {
                    balance -= Integer.parseInt(amt);
                }
            }

            if (balance < Integer.parseInt(amount)) {
                JOptionPane.showMessageDialog(null, "Insufficient Balance");
                return;
            }

            c.statement.executeUpdate(
                    "INSERT INTO bank VALUES('" + pin + "', '" + date + "', 'Withdraw', '" + amount + "')"
            );

            // THIS MESSAGE WILL NOW SHOW CORRECTLY
            JOptionPane.showMessageDialog(null, "Rs. " + amount + " Debited Successfully");

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        setVisible(false);
        new main_class(pin);
    }



    public static void main(String[] args) {
        new FastCash("");
    }
}
