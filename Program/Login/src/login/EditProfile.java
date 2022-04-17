/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author Ragavi
 */
public class EditProfile extends JFrame implements ActionListener {

    JButton btn, reset, cancel;
    JLabel first, last, age, email, city, mob;
    SpinnerModel ageSpin;
    JSpinner ageField;
    JComboBox area;
    final JTextField textField1, textField2, textField3, mobile;

    EditProfile() {
        ImageIcon icon = new ImageIcon("D:\\5th Sem\\SE\\E-Learning\\image\\background-1.jpg");
        JLabel background = new JLabel(icon);
        background.setBounds(0, 0, 1920, 1030);
        background.setLayout(null);
        add(background);
        
        Font f = new Font("Bradley Hand ITC", Font.BOLD, 70);
        JLabel head = new JLabel("E - LEARNING PLATFORM");
        head.setBounds(465,50,1000,100);
        head.setFont(f);
        head.setForeground(new Color(36, 31, 30));
        background.add(head);
        
        JPanel editlab = new JPanel();

        JLabel title = new JLabel("EDIT   PROFILE");
        Font f1 = new Font("Bradley Hand ITC", Font.BOLD, 48);
        title.setBounds(260, 30, 500, 40);
        title.setBackground(new Color(36, 31, 30));
        title.setForeground(new Color(255, 255, 255));
        title.setFont(f1);
        editlab.add(title);

        editlab.setBackground(new Color(36, 31, 30));
        editlab.setLayout(null);
        editlab.setBounds(480, 200, 900, 100);
        editlab.setVisible(true);
        background.add(editlab);

        JPanel editpan = new JPanel();
        Font f2 = new Font("Lucida Calligraphy", Font.BOLD, 23);
        Font f3 = new Font("Lucida Calligraphy", Font.PLAIN, 18);

        first = new JLabel("First Name : ");
        first.setBounds(110, 40, 200, 40);
        first.setBackground(new Color(182, 230, 179));
        first.setForeground(new Color(36, 31, 30));
        first.setFont(f2);
        editpan.add(first);

        textField1 = new JTextField(LoginSession.Firstname);
        textField1.setBounds(310, 40, 450, 40);
        textField1.setBackground(new Color(36, 31, 30));
        textField1.setForeground(new Color(255, 255, 255));
        textField1.setFont(f3);
        textField1.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        editpan.add(textField1);

        last = new JLabel("Last Name  : ");
        last.setBounds(110, 100, 200, 40);
        last.setBackground(new Color(182, 230, 179));
        last.setForeground(new Color(36, 31, 30));
        last.setFont(f2);
        editpan.add(last);

        textField2 = new JTextField(LoginSession.Lastname);
        textField2.setBounds(310, 100, 450, 40);
        textField2.setBackground(new Color(36, 31, 30));
        textField2.setForeground(new Color(255, 255, 255));
        textField2.setFont(f3);
        textField2.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        editpan.add(textField2);

        email = new JLabel("     E-mail  :    ");
        email.setBounds(110, 160, 200, 40);
        email.setBackground(new Color(182, 230, 179));
        email.setForeground(new Color(36, 31, 30));
        email.setFont(f2);
        editpan.add(email);

        textField3 = new JTextField(LoginSession.UserEmail);
        textField3.setBounds(310, 160, 450, 40);
        textField3.setBackground(new Color(36, 31, 30));
        textField3.setForeground(new Color(255, 255, 255));
        textField3.setFont(f3);
        textField3.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        editpan.add(textField3);

        mob = new JLabel("    Mobile  :    ");
        mob.setBounds(110, 220, 200, 40);
        mob.setBackground(new Color(182, 230, 179));
        mob.setForeground(new Color(36, 31, 30));
        mob.setFont(f2);
        editpan.add(mob);

        mobile = new JTextField(LoginSession.UserMobile);
        mobile.setBounds(310, 220, 450, 40);
        mobile.setBackground(new Color(36, 31, 30));
        mobile.setForeground(new Color(255, 255, 255));
        mobile.setFont(f3);
        mobile.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        editpan.add(mobile);

        age = new JLabel("Age  : ");
        age.setBounds(110, 280, 120, 40);
        age.setBackground(new Color(182, 230, 179));
        age.setForeground(new Color(36, 31, 30));
        age.setFont(f2);
        editpan.add(age);

        int userAge = Integer.parseInt(LoginSession.UserAge);
        ageSpin = new SpinnerNumberModel(userAge, 12, 58, 1);
        ageField = new JSpinner(ageSpin);
        ageField.setBounds(250, 280, 120, 40);
        ageField.setBackground(new Color(36, 31, 30));
        ageField.setForeground(new Color(255, 255, 255));
        ageField.setFont(f3);
        editpan.add(ageField);

        city = new JLabel("City : ");
        city.setBounds(420, 280, 200, 40);
        city.setBackground(new Color(182, 230, 179));
        city.setForeground(new Color(36, 31, 30));
        city.setFont(f2);
        editpan.add(city);

        String dist[] = {"Chennai", "Coimbatore", "Tiruppur", "Erode", "Namakkal", "Trichy"};
        area = new JComboBox(dist);
        area.setSelectedItem(LoginSession.UserCity);
        area.setBounds(510, 280, 250, 40);
        area.setBackground(new Color(36, 31, 30));
        area.setForeground(new Color(255, 255, 255));
        area.setFont(f3);
        editpan.add(area);

        cancel = new JButton("CANCEL");
        cancel.setBounds(110, 380, 180, 40);
        cancel.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        cancel.setBackground(new Color(36, 31, 30));
        cancel.setForeground(new Color(255, 255, 255));
        cancel.setFont(f3);
        editpan.add(cancel);
        cancel.addActionListener(this);

        reset = new JButton("RESET");
        reset.setBounds(360, 380, 180, 40);
        reset.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        reset.setBackground(new Color(36, 31, 30));
        reset.setForeground(new Color(255, 255, 255));
        reset.setFont(f3);
        editpan.add(reset);
        reset.addActionListener(this);

        btn = new JButton("SUBMIT");
        btn.setBounds(580, 380, 180, 40);
        btn.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        btn.setBackground(new Color(36, 31, 30));
        btn.setForeground(new Color(255, 255, 255));
        btn.setFont(f3);
        editpan.add(btn);
        btn.addActionListener(this);
        
        editpan.setBackground(new Color(182, 230, 179));
        editpan.setLayout(null);
        editpan.setBounds(480, 300, 900, 550);
        editpan.setVisible(true);
        background.add(editpan);
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("EDIT PROFILE FORM");
        setSize(1920, 1030);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancel) {
            Operation.isLogin(LoginSession.Username, LoginSession.UserPass, LoginSession.Usertype, this);
            Index page = new Index();
            page.setVisible(true);
            setVisible(false);
        }

        if (e.getSource() == reset) {
            textField1.setText(LoginSession.Firstname);
            textField2.setText(LoginSession.Lastname);
            textField3.setText(LoginSession.UserEmail);
            mobile.setText(LoginSession.UserMobile);
//            ageField.setValue(LoginSession.UserAge);
            ageField.setModel(ageSpin);
            area.setSelectedItem(LoginSession.UserCity);
        }

        if (e.getSource() == btn) {

            Statement stmt = null;
            Connection myConn = null;

            String first = textField1.getText();
            String last = textField2.getText();
            String emailValue = textField3.getText();
            String mbl = mobile.getText();
            String age = ageField.getValue().toString();
            String city = area.getSelectedItem().toString();

            try {

                if (mobile.getText().length() == 10) {
                    for (int i = 0; i < 10; i++) {
                        if ((mobile.getText().charAt(i) >= 'A' && mobile.getText().charAt(i) <= 'Z') || (mobile.getText().charAt(i) >= 'a' && mobile.getText().charAt(i) <= 'z')) {
                            JOptionPane.showMessageDialog(this, "Mobile number must contain digits only. Please enter the valid one...");
                            break;
                        }
                    }

                    myConn = MySQLConnection.getConnection();

                    String mySqlQuery = "UPDATE e_learn "
                            + "SET FirstName = ?, LastName = ?, Email = ?, Mobile = ?, Age = ?, City = ? "
                            + "WHERE Username = '" + LoginSession.Username + "' AND Password = '" + LoginSession.UserPass + "' AND Category = '" + LoginSession.Usertype + "'";

                    PreparedStatement preparedStatement = myConn.prepareStatement(mySqlQuery);
                    preparedStatement.setString(1, first);
                    preparedStatement.setString(2, last);
                    preparedStatement.setString(3, emailValue);
                    preparedStatement.setString(4, mbl);
                    preparedStatement.setString(5, age);
                    preparedStatement.setString(6, city);
                    preparedStatement.executeUpdate();

                    JOptionPane.showMessageDialog(this, "Hello, " + LoginSession.Username + " ...\nYou Profile is Updated Succesfully");

                    Operation.isLogin(LoginSession.Username, LoginSession.UserPass, LoginSession.Usertype, this);
                    Index page = new Index();
                    page.setVisible(true);
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(this, "Mobile number must have 10 digits...");
                }

            } catch (Exception exception) {
                JOptionPane.showMessageDialog(this, "Something went wrong : " + exception);
                System.out.println(exception);
            } finally {
                try {
                    if (stmt != null) {
                        myConn.close();
                    }
                } catch (SQLException se) {
                    System.out.println(se);
                }
                try {
                    if (myConn != null) {
                        myConn.close();
                    }
                } catch (SQLException se) {
                    System.out.println(se);
                }
            }
        }
    }

}
