package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class update_patient_details extends JFrame {

    update_patient_details() {

        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 940, 490);
        panel.setBackground(new Color(90, 156, 163));
        panel.setLayout(null);
        add(panel);

        ImageIcon imageIcon = new ImageIcon(
                ClassLoader.getSystemResource("icons/Update.png"));
        Image image = imageIcon.getImage()
                .getScaledInstance(300, 300, Image.SCALE_DEFAULT);
        JLabel label = new JLabel(new ImageIcon(image));
        label.setBounds(500, 60, 300, 300);
        panel.add(label);

        JLabel label1 = new JLabel("Update Patient Details");
        label1.setBounds(124, 11, 300, 25);
        label1.setFont(new Font("Tahoma", Font.BOLD, 20));
        label1.setForeground(Color.WHITE);
        panel.add(label1);

        JLabel label2 = new JLabel("Name :");
        label2.setBounds(25, 88, 150, 14);
        label2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label2.setForeground(Color.WHITE);
        panel.add(label2);

        Choice choice = new Choice();
        choice.setBounds(248, 85, 140, 25);
        panel.add(choice);

        try {
            conn c = new conn();
            ResultSet rs = c.statement.executeQuery(
                    "select Name from Patient_Info");
            while (rs.next()) {
                choice.add(rs.getString("Name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel label3 = new JLabel("Room Number :");
        label3.setBounds(25, 129, 150, 14);
        label3.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label3.setForeground(Color.WHITE);
        panel.add(label3);

        JTextField textFieldR = new JTextField();
        textFieldR.setBounds(248, 129, 140, 20);
        panel.add(textFieldR);

        JLabel label4 = new JLabel("In-Time :");
        label4.setBounds(25, 174, 150, 14);
        label4.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label4.setForeground(Color.WHITE);
        panel.add(label4);

        JTextField textFieldINTime = new JTextField();
        textFieldINTime.setBounds(248, 174, 140, 20);
        panel.add(textFieldINTime);

        JLabel label5 = new JLabel("Amount Paid (Rs) :");
        label5.setBounds(25, 216, 150, 14);
        label5.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label5.setForeground(Color.WHITE);
        panel.add(label5);

        JTextField textFieldAmount = new JTextField();
        textFieldAmount.setBounds(248, 216, 140, 20);
        panel.add(textFieldAmount);

        JLabel label6 = new JLabel("Pending Amount (Rs) :");
        label6.setBounds(25, 261, 180, 14);
        label6.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label6.setForeground(Color.WHITE);
        panel.add(label6);

        JTextField textFieldPending = new JTextField();
        textFieldPending.setBounds(248, 261, 140, 20);
        textFieldPending.setEditable(false);
        panel.add(textFieldPending);

        JButton check = new JButton("CHECK");
        check.setBounds(281, 378, 89, 23);
        check.setForeground(Color.WHITE);
        check.setBackground(Color.BLACK);
        panel.add(check);

        check.addActionListener(e -> {
            try {
                conn c = new conn();
                String name = choice.getSelectedItem();

                ResultSet rs = c.statement.executeQuery(
                        "select * from Patient_Info where Name = '"+name+"'");

                if (rs.next()) {
                    textFieldR.setText(rs.getString("Room_Number"));
                    textFieldINTime.setText(rs.getString("Time"));
                    textFieldAmount.setText(rs.getString("Deposite"));
                }

                ResultSet rs2 = c.statement.executeQuery(
                        "select Price from room where room_no = '"
                                + textFieldR.getText() + "'");

                if (rs2.next()) {
                    int price = Integer.parseInt(rs2.getString("Price"));
                    int paid = Integer.parseInt(textFieldAmount.getText());
                    textFieldPending.setText(
                            String.valueOf(price - paid));
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        JButton update = new JButton("UPDATE");
        update.setBounds(56, 378, 89, 23);
        update.setForeground(Color.WHITE);
        update.setBackground(Color.BLACK);
        panel.add(update);

        update.addActionListener(e -> {
            try {
                conn c = new conn();
                String name = choice.getSelectedItem();

                c.statement.executeUpdate(
                        "update Patient_Info set " +
                                "Room_Number = '"+textFieldR.getText()+"', " +
                                "Time = '"+textFieldINTime.getText()+"', " +
                                "Deposite = '"+textFieldAmount.getText()+"' " +
                                "where Name = '"+name+"'"
                );

                JOptionPane.showMessageDialog(
                        null, "Updated Successfully");
                setVisible(false);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        JButton back = new JButton("BACK");
        back.setBounds(168, 378, 89, 23);
        back.setForeground(Color.white);
        back.setBackground(Color.BLACK);
        panel.add(back);

        back.addActionListener(e -> setVisible(false));

        setUndecorated(true);
        setSize(950, 500);
        setLayout(null);
        setLocation(400, 250);
        setVisible(true);
    }

    public static void main(String[] args) {
        new update_patient_details();
    }
}
