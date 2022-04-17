/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class CreateLoginForm extends JFrame implements ActionListener {

    JButton sub, reset, cancel;
    JLabel name, email, password, category, user;
    JComboBox catCombo;
    final JTextField textField1, textField2, textField3;

    CreateLoginForm() {
        
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
        
        JPanel loginlab = new JPanel();

        JLabel title = new JLabel("LOGIN FORM");
        Font f1 = new Font("Bradley Hand ITC", Font.BOLD, 48);
        title.setBounds(280, 30, 500, 40);
        title.setBackground(new Color(36, 31, 30));
        title.setForeground(new Color(255, 255, 255));
        title.setFont(f1);
        loginlab.add(title);

        loginlab.setBackground(new Color(36, 31, 30));
        loginlab.setLayout(null);
        loginlab.setBounds(480, 200, 900, 100);
        loginlab.setVisible(true);
        background.add(loginlab);

        JPanel loginpan = new JPanel();
        Font f2 = new Font("Lucida Calligraphy", Font.BOLD, 23);
        Font f3 = new Font("Lucida Calligraphy", Font.PLAIN, 18);

        name = new JLabel();
        name.setText("Username : ");
        name.setBounds(110, 40, 200, 40);
        name.setBackground(new Color(182, 230, 179));
        name.setForeground(new Color(36, 31, 30));
        name.setFont(f2);
        loginpan.add(name);

        textField1 = new JTextField(25);
        textField1.setBounds(310, 40, 450, 40);
        textField1.setBackground(new Color(36, 31, 30));
        textField1.setForeground(new Color(255, 255, 255));
        textField1.setFont(f3);
        textField1.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        loginpan.add(textField1);

        email = new JLabel();
        email.setText("    E-mail : ");
        email.setBounds(110, 100, 200, 40);
        email.setBackground(new Color(182, 230, 179));
        email.setForeground(new Color(36, 31, 30));
        email.setFont(f2);
        loginpan.add(email);

        textField2 = new JTextField(25);
        textField2.setBounds(310, 100, 450, 40);
        textField2.setBackground(new Color(36, 31, 30));
        textField2.setForeground(new Color(255, 255, 255));
        textField2.setFont(f3);
        textField2.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        loginpan.add(textField2);

        password = new JLabel();
        password.setText("Password : ");
        password.setBounds(110, 160, 200, 40);
        password.setBackground(new Color(182, 230, 179));
        password.setForeground(new Color(36, 31, 30));
        password.setFont(f2);
        loginpan.add(password);

        textField3 = new JPasswordField(25);
        textField3.setBounds(310, 160, 450, 40);
        textField3.setBackground(new Color(36, 31, 30));
        textField3.setForeground(new Color(255, 255, 255));
        textField3.setFont(f3);
        textField3.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        loginpan.add(textField3);

        category = new JLabel();
        category.setText("Category : ");
        category.setBounds(110, 220, 350, 40);
        category.setBackground(new Color(182, 230, 179));
        category.setForeground(new Color(36, 31, 30));
        category.setFont(f2);
        loginpan.add(category);

        String cat[] = {"Admin", "Student", "Teacher"};
        catCombo = new JComboBox(cat);
        catCombo.setBounds(310, 220, 450, 40);
        catCombo.setBackground(new Color(36, 31, 30));
        catCombo.setForeground(new Color(255, 255, 255));
        catCombo.setFont(f3);
        loginpan.add(catCombo);

        cancel = new JButton("CANCEL");
        cancel.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        cancel.setBounds(120, 300, 180, 50);
        cancel.setBackground(new Color(36, 31, 30));
        cancel.setForeground(new Color(255, 255, 255));
        cancel.setFont(f3);
        loginpan.add(cancel);
        cancel.addActionListener(this);

        reset = new JButton("RESET");
        reset.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        reset.setBounds(350, 300, 180, 50);
        reset.setBackground(new Color(36, 31, 30));
        reset.setForeground(new Color(255, 255, 255));
        reset.setFont(f3);
        loginpan.add(reset);
        reset.addActionListener(this);

        sub = new JButton("LOGIN");
        sub.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        sub.setBounds(580, 300, 180, 50);
        sub.setBackground(new Color(36, 31, 30));
        sub.setForeground(new Color(255, 255, 255));
        sub.setFont(f3);
        loginpan.add(sub);
        sub.addActionListener(this);

        user = new JLabel();
        user.setText(" Create a new account here ");
        user.setBounds(280, 410, 500, 40);
        user.setBackground(new Color(182, 230, 179));
        user.setForeground(new Color(36, 31, 30));
        user.setFont(f2);
        user.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginpan.add(user);
        user.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                Register user_reg = new Register();
                user_reg.setVisible(true);
                setVisible(false);

            }
        });

        loginpan.setBackground(new Color(182, 230, 179));
        loginpan.setLayout(null);
        loginpan.setBounds(480, 300, 900, 500);
        loginpan.setVisible(true);
        background.add(loginpan);

        setSize(1920, 1030);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);
        setTitle("LOGIN FORM");
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == sub) {
            Operation operation = new Operation();

            try {
                String userValue = textField1.getText();
                String emailValue = textField2.getText();
                String passValue = textField3.getText();
                String type = catCombo.getSelectedItem().toString();

                if (Operation.isLogin(userValue, passValue, type, this)) {
                    Index page = new Index();
                    page.setVisible(true);
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(this, "Please enter valid username or password");
                }
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(this, "Please enter the correct login details");
            }
        }

        if (ae.getSource() == reset) {
            textField1.setText("");
            textField2.setText("");
            textField3.setText("");
            catCombo.setSelectedIndex(0);
        }
        if (ae.getSource() == cancel) {
            setVisible(false);
        }
    }
}

class Login {

    public static void main(String arg[]) {
        try {
            CreateLoginForm form = new CreateLoginForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
