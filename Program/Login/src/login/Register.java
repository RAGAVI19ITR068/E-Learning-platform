/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

class Register extends JFrame implements ActionListener {

    JButton btn, reset, cancel;
    JLabel first, last, userName, dob, age, email, catogery, city, state, pincode, password, reenter, old, mob;
    SpinnerModel ageSpin;
    JSpinner ageField;
    JComboBox combo, area;
    final JTextField textField1, textField2, textField3, textField4, textField5, textField6, mobile;

    Register() {
        ImageIcon icon = new ImageIcon("D:\\5th Sem\\SE\\E-Learning\\image\\background-1.jpg");
        JLabel background = new JLabel(icon);
        background.setBounds(0, 0, 1920, 1030);
        background.setLayout(null);
        add(background);
        
        Font f = new Font("Bradley Hand ITC", Font.BOLD, 70);
        JLabel head = new JLabel("E - LEARNING PLATFORM");
        head.setBounds(465,10,1000,100);
        head.setFont(f);
        head.setForeground(new Color(36, 31, 30));
        background.add(head);

        JPanel reglab = new JPanel();

        JLabel title = new JLabel("REGISTRATION FORM");
        Font f1 = new Font("Bradley Hand ITC", Font.BOLD, 48);
        title.setBounds(160, 30, 700, 40);
        title.setBackground(new Color(36, 31, 30));
        title.setForeground(new Color(255, 255, 255));
        title.setFont(f1);
        reglab.add(title);

        reglab.setBackground(new Color(36, 31, 30));
        reglab.setLayout(null);
        reglab.setBounds(480, 100, 900, 100);
        reglab.setVisible(true);
        background.add(reglab);

        JPanel regpan = new JPanel();
        Font f2 = new Font("Lucida Calligraphy", Font.BOLD, 23);
        Font f3 = new Font("Lucida Calligraphy", Font.PLAIN, 18);

        first = new JLabel("First Name : ");
        first.setBounds(110, 40, 200, 40);
        first.setBackground(new Color(182, 230, 179));
        first.setForeground(new Color(36, 31, 30));
        first.setFont(f2);
        regpan.add(first);

        textField1 = new JTextField(20);
        textField1.setBounds(310, 40, 450, 40);
        textField1.setBackground(new Color(36, 31, 30));
        textField1.setForeground(new Color(255, 255, 255));
        textField1.setFont(f3);
        textField1.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        regpan.add(textField1);

        last = new JLabel(" Last Name : ");
        last.setBounds(110, 100, 200, 40);
        last.setBackground(new Color(182, 230, 179));
        last.setForeground(new Color(36, 31, 30));
        last.setFont(f2);
        regpan.add(last);

        textField2 = new JTextField(20);
        textField2.setBounds(310, 100, 450, 40);
        textField2.setBackground(new Color(36, 31, 30));
        textField2.setForeground(new Color(255, 255, 255));
        textField2.setFont(f3);
        textField2.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        regpan.add(textField2);

        email = new JLabel("       E-mail :   ");
        email.setBounds(110, 160, 200, 40);
        email.setBackground(new Color(182, 230, 179));
        email.setForeground(new Color(36, 31, 30));
        email.setFont(f2);
        regpan.add(email);

        textField3 = new JTextField(20);
        textField3.setBounds(310, 160, 450, 40);
        textField3.setBackground(new Color(36, 31, 30));
        textField3.setForeground(new Color(255, 255, 255));
        textField3.setFont(f3);
        textField3.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        regpan.add(textField3);

        mob = new JLabel("      Mobile :    ");
        mob.setBounds(110, 220, 200, 40);
        mob.setBackground(new Color(182, 230, 179));
        mob.setForeground(new Color(36, 31, 30));
        mob.setFont(f2);
        regpan.add(mob);

        mobile = new JTextField(10);
        mobile.setBounds(310, 220, 450, 40);
        mobile.setBackground(new Color(36, 31, 30));
        mobile.setForeground(new Color(255, 255, 255));
        mobile.setFont(f3);
        mobile.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        regpan.add(mobile);

        userName = new JLabel(" Username : ");
        userName.setBounds(110, 280, 200, 40);
        userName.setBackground(new Color(182, 230, 179));
        userName.setForeground(new Color(36, 31, 30));
        userName.setFont(f2);
        regpan.add(userName);

        textField4 = new JTextField(10);
        textField4.setBounds(310, 280, 450, 40);
        textField4.setBackground(new Color(36, 31, 30));
        textField4.setForeground(new Color(255, 255, 255));
        textField4.setFont(f3);
        textField4.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        regpan.add(textField4);

        age = new JLabel("Age : ");
        age.setBounds(110, 340, 150, 40);
        age.setBackground(new Color(182, 230, 179));
        age.setForeground(new Color(36, 31, 30));
        age.setFont(f2);
        regpan.add(age);

        ageSpin = new SpinnerNumberModel(12, 12, 58, 1);
        ageField = new JSpinner(ageSpin);
        ageField.setBounds(210, 340, 100, 40);
        ageField.setBackground(new Color(36, 31, 30));
        ageField.setForeground(new Color(255, 255, 255));
        ageField.setFont(f3);
        regpan.add(ageField);

        catogery = new JLabel();
        catogery.setText("Catogery : ");
        catogery.setBounds(390, 340, 150, 40);
        catogery.setBackground(new Color(182, 230, 179));
        catogery.setForeground(new Color(36, 31, 30));
        catogery.setFont(f2);
        regpan.add(catogery);

        String cat[] = {"Student", "Teacher"};
        combo = new JComboBox(cat);
        combo.setBounds(560, 340, 200, 40);
        combo.setBackground(new Color(36, 31, 30));
        combo.setForeground(new Color(255, 255, 255));
        combo.setFont(f3);
        combo.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        regpan.add(combo);

        city = new JLabel();
        city.setText("          City : ");
        city.setBounds(110, 400, 200, 40);
        city.setBackground(new Color(182, 230, 179));
        city.setForeground(new Color(36, 31, 30));
        city.setFont(f2);
        regpan.add(city);

        String dist[] = {"Chennai", "Coimbatore", "Tiruppur", "Erode", "Namakkal", "Trichy"};
        area = new JComboBox(dist);
        area.setBounds(310, 400, 450, 40);
        area.setBackground(new Color(36, 31, 30));
        area.setForeground(new Color(255, 255, 255));
        area.setFont(f3);
        area.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        regpan.add(area);

        password = new JLabel("  Password : ");
        password.setBounds(110, 460, 200, 40);
        password.setBackground(new Color(182, 230, 179));
        password.setForeground(new Color(36, 31, 30));
        password.setFont(f2);
        regpan.add(password);

        textField5 = new JPasswordField(20);
        textField5.setBounds(310, 460, 450, 40);
        textField5.setBackground(new Color(36, 31, 30));
        textField5.setForeground(new Color(255, 255, 255));
        textField5.setFont(f3);
        textField5.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        regpan.add(textField5);

        reenter = new JLabel("   Re-enter : ");
        reenter.setBounds(110, 520, 200, 40);
        reenter.setBackground(new Color(182, 230, 179));
        reenter.setForeground(new Color(36, 31, 30));
        reenter.setFont(f2);
        regpan.add(reenter);

        textField6 = new JPasswordField(20);
        textField6.setBounds(310, 520, 450, 40);
        textField6.setBackground(new Color(36, 31, 30));
        textField6.setForeground(new Color(255, 255, 255));
        textField6.setFont(f3);
        textField6.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        regpan.add(textField6);

        cancel = new JButton("CANCEL");
        cancel.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        cancel.setBounds(110, 620, 180, 50);
        cancel.setBackground(new Color(36, 31, 30));
        cancel.setForeground(new Color(255, 255, 255));
        cancel.setFont(f3);
        regpan.add(cancel);
        cancel.addActionListener(this);

        reset = new JButton("RESET");
        reset.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        reset.setBounds(350, 620, 180, 50);
        reset.setBackground(new Color(36, 31, 30));
        reset.setForeground(new Color(255, 255, 255));
        reset.setFont(f3);
        regpan.add(reset);
        reset.addActionListener(this);

        btn = new JButton("REGISTER");
        btn.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        btn.setBounds(580, 620, 180, 50);
        btn.setBackground(new Color(36, 31, 30));
        btn.setForeground(new Color(255, 255, 255));
        btn.setFont(f3);
        regpan.add(btn);
        btn.addActionListener(this);

        old = new JLabel("Old User?  Login here ... ");
        old.setBounds(280, 690, 500, 40);
        old.setBackground(new Color(182, 230, 179));
        old.setForeground(new Color(36, 31, 30));
        old.setFont(f2);
        old.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        regpan.add(old);
        old.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                CreateLoginForm form = new CreateLoginForm();
                form.setVisible(true);
                setVisible(false);

            }
        });
        
        regpan.setBackground(new Color(182, 230, 179));
        regpan.setLayout(null);
        regpan.setBounds(480, 200, 900, 750);
        regpan.setVisible(true);
        background.add(regpan);
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("REGISTRATION FORM");
        setSize(1920, 1030);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn) {

            Statement stmt = null;
            Connection myConn = null;

            String uid = String.format("%04d", new Random().nextInt(10000));
            String first = textField1.getText();
            String last = textField2.getText();
            String emailValue = textField3.getText();
            String mbl = mobile.getText();
            String age = ageField.getValue().toString();
            String type = combo.getSelectedItem().toString();
            String city = area.getSelectedItem().toString();
            String userValue = textField4.getText();
            String passValue = textField5.getText();
            String reenterValue = textField6.getText();

            try {

                if (mobile.getText().length() == 10) {
                    for (int i = 0; i < 10; i++) {
                        if ((mobile.getText().charAt(i) >= 'A' && mobile.getText().charAt(i) <= 'Z') || (mobile.getText().charAt(i) >= 'a' && mobile.getText().charAt(i) <= 'z')) {
                            JOptionPane.showMessageDialog(this, "Mobile number must contain digits only. Please enter the valid one...");
                            break;
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Mobile number must have 10 digits...");
                }
                if (textField5.getText().length() == 8) {
                    if (!textField5.getText().equals(textField6.getText())) {
                        JOptionPane.showMessageDialog(this, "Password should be match");
                    } else {
                        myConn = MySQLConnection.getConnection();

                        String mySqlQuery = "INSERT INTO e_learn(UID, FirstName, LastName, Username, Email, Mobile, Age, Category, Password, City) "
                                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                        PreparedStatement preparedStatement = myConn.prepareStatement(mySqlQuery);
                        preparedStatement.setString(1, uid);
                        preparedStatement.setString(2, first);
                        preparedStatement.setString(3, last);
                        preparedStatement.setString(4, userValue);
                        preparedStatement.setString(5, emailValue);
                        preparedStatement.setString(6, mbl);
                        preparedStatement.setString(7, age);
                        preparedStatement.setString(8, type);
                        preparedStatement.setString(9, passValue);
                        preparedStatement.setString(10, city);
                        preparedStatement.execute();

                        JOptionPane.showMessageDialog(this, "Hello, " + userValue + " ...\nYou are Successfully Registered as " + type);

                        if (type.equals("Student")) {
                            String stud = "INSERT INTO student(SID, CID) "
                                    + "VALUES(?, ?)";
                            PreparedStatement ps = myConn.prepareStatement(stud);
                            ps.setString(1, uid);
                            ps.setString(2, "0");
                            ps.execute();
                        }

                        Operation.isLogin(userValue, passValue, type, this);
                        Index page = new Index();
                        page.setVisible(true);
                        setVisible(false);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Password length should be 8 characters");
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
        
        if (e.getSource() == cancel) {
            CreateLoginForm log = new CreateLoginForm();
            log.setVisible(true);
            setVisible(false);
        }
        
        if (e.getSource() == reset) {
            textField1.setText("");
            textField2.setText("");
            textField3.setText("");
            textField4.setText("");
            textField5.setText("");
            textField6.setText("");
            mobile.setText("");
            ageField.setValue(ageSpin);
            combo.setSelectedIndex(0);
            area.setSelectedIndex(0);
        }

    }
}
