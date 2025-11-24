package bank.management.system;

import com.sun.source.tree.WhileLoopTree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class cashWithdraw extends JFrame implements ActionListener {

    String pin;

    TextField textField;

    JButton b1, b2;

    cashWithdraw(String pin) {
        this.pin=pin;
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm.jpeg"));
        Image i2 = i1.getImage().getScaledInstance(1550, 830, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(0, 0, 1550, 830);
        add(l3);

        JLabel label1 = new JLabel("MAXIMUM WITHDRAWAl AMOUNT IS RS 10,000");
        label1.setForeground(Color.white);
        label1.setFont(new Font("System", Font.BOLD, 20));
        label1.setBounds(550, 330, 700, 35);
        l3.add(label1);

        JLabel label2 = new JLabel("ENTER YOUR AMOUNT ");
        label2.setForeground(Color.white);
        label2.setFont(new Font("System", Font.BOLD, 22));
        label2.setBounds(590, 370, 700, 35);
        l3.add(label2);

        textField = new TextField();
        textField.setBounds(590, 400, 320, 25);
        textField.setFont(new Font("Raleway", Font.BOLD, 22));
        textField.setBackground(Color.WHITE);
        textField.setForeground(Color.black);
        l3.add(textField);

        b1 = new JButton("Withdraw");
        b1.setBounds(910, 460, 150, 25);
        b1.setFont(new Font("Raleway", Font.BOLD, 12));
        b1.setBackground(Color.WHITE);
        b1.setForeground(Color.black);
        b1.addActionListener(this);
        l3.add(b1);

        b2 = new JButton("Back");
        b2.setBounds(910, 490, 150, 25);
        b2.setFont(new Font("Raleway", Font.BOLD, 12));
        b2.setBackground(Color.WHITE);
        b2.setForeground(Color.black);
        b2.addActionListener(this);
        l3.add(b2);

        setLayout(null);
        setSize(1550, 1080);
        setLocation(0, 0);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            try {
                String amount = textField.getText().trim();
                if (amount.equals("") || !amount.matches("\\d+")) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid numeric amount");
                    return;
                }
                int amt = Integer.parseInt(amount);
                if (amt <= 0) {
                    JOptionPane.showMessageDialog(null, "Amount must be greater than zero");
                    return;
                }
                if (amt > 10000) {
                    JOptionPane.showMessageDialog(null, "Maximum withdraw limit is Rs. 10,000");
                    return;
                }
                Con c = new Con();
                ResultSet resultset = c.statement.executeQuery("Select * from bank where pin = '" + pin + "' ");
                int balance = 0;
                while (resultset.next()) {
                    String dbAmount = resultset.getString("amount");
                    if (dbAmount == null || dbAmount.trim().equals("")) {
                        dbAmount = "0";
                    }
                    if (resultset.getString("type").equals("Deposit")) {
                        balance += Integer.parseInt(dbAmount);
                    } else {
                        balance -= Integer.parseInt(dbAmount);
                    }
                }

                if (balance < amt) {
                    JOptionPane.showMessageDialog(null, "Insufficient Balance");
                    return;
                }
                Date date = new Date();
                c.statement.executeUpdate(
                        "insert into bank values('" + pin + "' , '" + date + "','Withdraw' , '" + amt + "')"
                );
                JOptionPane.showMessageDialog(null, "Rs. " + amt + " Debited Successfully");
                setVisible(false);
                new main_class(pin);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        } else if (e.getSource() == b2) {
            setVisible(false);
            new main_class(pin);
        }
    }


    public static void main(String[] args) {
        new cashWithdraw("");
    }
}
