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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Ragavi
 */
public class CreateCourse extends JFrame implements ActionListener {

    JLabel id, name, material;
    JTextField textField1, textField2, textField3;
    JButton sub, reset, cancel;

    CreateCourse() {
        ImageIcon icon = new ImageIcon("D:\\5th Sem\\SE\\E-Learning\\image\\indexbg.jpg");
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
        
        JPanel cclab = new JPanel();

        JLabel title = new JLabel("CREATE COURSE FORM");
        Font f1 = new Font("Bradley Hand ITC", Font.BOLD, 48);
        title.setBounds(100, 30, 700, 40);
        title.setBackground(new Color(36, 31, 30));
        title.setForeground(new Color(255, 255, 255));
        title.setFont(f1);
        cclab.add(title);

        cclab.setBackground(new Color(36, 31, 30));
        cclab.setLayout(null);
        cclab.setBounds(480, 200, 900, 100);
        cclab.setVisible(true);
        background.add(cclab);

        JPanel ccpan = new JPanel();
        Font f2 = new Font("Lucida Calligraphy", Font.BOLD, 23);
        Font f3 = new Font("Lucida Calligraphy", Font.PLAIN, 18);

        id = new JLabel("Course Id : ");
        id.setBounds(110, 40, 200, 40);
        id.setBackground(new Color(182, 230, 179));
        id.setForeground(new Color(36, 31, 30));
        id.setFont(f2);
        ccpan.add(id);

        textField1 = new JTextField();
        textField1.setBounds(340, 40, 450, 40);
        textField1.setBackground(new Color(36, 31, 30));
        textField1.setForeground(new Color(255, 255, 255));
        textField1.setFont(f3);
        textField1.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        ccpan.add(textField1);

        name = new JLabel("Course Name : ");
        name.setBounds(110, 120, 200, 40);
        name.setBackground(new Color(182, 230, 179));
        name.setForeground(new Color(36, 31, 30));
        name.setFont(f2);
        ccpan.add(name);

        textField2 = new JTextField();
        textField2.setBounds(340, 120, 450, 40);
        textField2.setBackground(new Color(36, 31, 30));
        textField2.setForeground(new Color(255, 255, 255));
        textField2.setFont(f3);
        textField2.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        ccpan.add(textField2);

        material = new JLabel("Material Link : ");
        material.setBounds(110, 180, 250, 40);
        material.setBackground(new Color(182, 230, 179));
        material.setForeground(new Color(36, 31, 30));
        material.setFont(f2);
        ccpan.add(material);

        textField3 = new JTextField();
        textField3.setBounds(340, 180, 450, 40);
        textField3.setBackground(new Color(36, 31, 30));
        textField3.setForeground(new Color(255, 255, 255));
        textField3.setFont(f3);
        textField3.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        ccpan.add(textField3);

        cancel = new JButton("CANCEL");
        cancel.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        cancel.setBounds(110, 300, 180, 50);
        cancel.setBackground(new Color(36, 31, 30));
        cancel.setForeground(new Color(255, 255, 255));
        cancel.setFont(f3);
        ccpan.add(cancel);
        cancel.addActionListener(this);

        reset = new JButton("RESET");
        reset.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        reset.setBounds(350, 300, 180, 50);
        reset.setBackground(new Color(36, 31, 30));
        reset.setForeground(new Color(255, 255, 255));
        reset.setFont(f3);
        ccpan.add(reset);
        reset.addActionListener(this);

        sub = new JButton("SUBMIT");
        sub.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        sub.setBounds(580, 300, 180, 50);
        sub.setBackground(new Color(36, 31, 30));
        sub.setForeground(new Color(255, 255, 255));
        sub.setFont(f3);
        ccpan.add(sub);
        sub.addActionListener(this);
        
        ccpan.setBackground(new Color(182, 230, 179));
        ccpan.setLayout(null);
        ccpan.setBounds(480, 300, 900, 400);
        ccpan.setVisible(true);
        background.add(ccpan);
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CREATE COURSE FORM");
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
            textField1.setText("");
            textField2.setText("");
            textField3.setText("");
        }

        if (e.getSource() == sub) {

            Statement stmt = null;
            Connection myConn = null;

            String cid = textField1.getText();
            String cname = textField2.getText();
            String cmat = textField3.getText();

            try {

                myConn = MySQLConnection.getConnection();
                String mySqlQuery = "INSERT INTO course(CID, CourseName, Material) "
                        + "VALUES(?, ?, ?)";

                PreparedStatement preparedStatement = myConn.prepareStatement(mySqlQuery);
                preparedStatement.setString(1, cid);
                preparedStatement.setString(2, cname);
                preparedStatement.setString(3, cmat);
                preparedStatement.execute();

                JOptionPane.showMessageDialog(this, "Hello, " + LoginSession.Username + " ...\nYou are Successfully Created a Course called '" + cname+"'");

                Operation.isLogin(LoginSession.Username, LoginSession.UserPass, LoginSession.Usertype, this);
                Index page = new Index();
                page.setVisible(true);
                setVisible(false);
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
