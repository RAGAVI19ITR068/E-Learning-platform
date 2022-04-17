/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
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
import static javax.swing.JOptionPane.QUESTION_MESSAGE;
import javax.swing.JPanel;

/**
 *
 * @author Ragavi
 */
public class CourseView extends JFrame implements ActionListener {

    JLabel id, name, material, back;
    JButton del, editMat;
    URI link;

    CourseView(String vid) {

        ImageIcon icon = new ImageIcon("D:\\5th Sem\\SE\\E-Learning\\image\\indexbg.jpg");
        JLabel background = new JLabel(icon);
        background.setBounds(0, 0, 1920, 1030);
        background.setLayout(null);
        add(background);

        if (CoursesAvailable.ViewCourse(this, vid)) {
            Font f = new Font("Bradley Hand ITC", Font.BOLD, 70);
            JLabel head = new JLabel(CoursesAvailable.CourseName);
            head.setBounds(850, 100, 700, 100);
            head.setFont(f);
            head.setForeground(new Color(36, 31, 30));
            background.add(head);

            JPanel viewpane = new JPanel();

            Font f3 = new Font("Lucida Calligraphy", Font.PLAIN, 20);

            id = new JLabel("  Course Id    :  " + CoursesAvailable.CID);
            id.setForeground(new Color(36, 31, 30));
            id.setBounds(300, 100, 500, 40);
            id.setFont(f3);
            viewpane.add(id);

            name = new JLabel("Course name    :  " + CoursesAvailable.CourseName);
            name.setForeground(new Color(36, 31, 30));
            name.setBounds(300, 160, 500, 40);
            name.setFont(f3);
            viewpane.add(name);

            material = new JLabel("Material link  :  ");
            material.setForeground(new Color(36, 31, 30));
            material.setBounds(300, 220, 200, 40);
            material.setFont(f3);
            viewpane.add(material);
            try {
                link = new URI(CoursesAvailable.Material);

                JLabel hyperLink = new JLabel("Click Here");
                hyperLink.setForeground(Color.BLUE.darker());
                hyperLink.setBounds(500, 220, 150, 40);
                hyperLink.setFont(f3);
                hyperLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                viewpane.add(hyperLink);

                hyperLink.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent me) {
                        if (Desktop.isDesktopSupported()) {
                            try {
                                Desktop.getDesktop().browse(link);
                            } catch (Exception excep) {
                                System.out.println(excep);
                            }
                        } else {
                            System.out.println("Desktop is not supported");
                        }
                    }
                });
            } catch (Exception ex) {
                System.out.println(ex);
            }

            ImageIcon backIcon = new ImageIcon("D:\\5th Sem\\SE\\E-Learning\\image\\arrow_back.png");
            back = new JLabel(backIcon);
            back.setBounds(50, 80, 150, 80);
            back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            back.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent me) {

                    Index page = new Index();
                    page.setVisible(true);
                    setVisible(false);

                }
            });
            viewpane.add(back);

            editMat = new JButton(" EDIT MATERIAL");
            editMat.setBackground(new Color(200, 230, 201));
            editMat.setBounds(160, 300, 280, 40);
            editMat.setFont(f3);
            editMat.addActionListener(this);
            viewpane.add(editMat);

            del = new JButton(" DELETE COURSE");
            del.setBackground(new Color(249, 168, 37));
            del.setBounds(510, 300, 280, 40);
            del.setFont(f3);
            del.addActionListener(this);
            viewpane.add(del);

            viewpane.setLayout(null);
            viewpane.setBackground(new Color(179, 229, 252));
            viewpane.setBorder(BorderFactory.createLineBorder(Color.black, 5, true));
            viewpane.setSize(900, 510);
            viewpane.setLocation(500, 200);
            viewpane.setVisible(true);
            background.add(viewpane);
        }

        setTitle(CoursesAvailable.CourseName);
        setLayout(null);
        setSize(1920, 1030);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == del) {
            Statement stmt = null;
            Connection myConn = null;

            try {

                myConn = MySQLConnection.getConnection();

                String mySqlQuery = "DELETE FROM course "
                        + "WHERE CID = '" + CoursesAvailable.CID + "'";

                PreparedStatement preparedStatement = myConn.prepareStatement(mySqlQuery);
                preparedStatement.execute();

                JOptionPane.showMessageDialog(this, "Hey!, " + LoginSession.Username + " ...\nYou have deleted the course called " + CoursesAvailable.CourseName);
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

        if (e.getSource() == editMat) {
            JLabel linkop = new JLabel("Material Link : ");
            String mat = JOptionPane.showInputDialog(this, linkop, "Enter the Material Link ...", QUESTION_MESSAGE);

            Statement stmt = null;
            Connection myConn = null;

            if (mat != null) {
                try {

                    myConn = MySQLConnection.getConnection();

                    String mySqlQuery = "UPDATE course "
                            + "SET Material = ? "
                            + "WHERE CID = '" + CoursesAvailable.CID + "'";

                    PreparedStatement preparedStatement = myConn.prepareStatement(mySqlQuery);
                    preparedStatement.setString(1, mat);
                    preparedStatement.executeUpdate();

                    JOptionPane.showMessageDialog(this, "Hello, " + LoginSession.Username + " ...\nYou Updated Material Link Succesfully");

                    CoursesAvailable.Material = mat;
                    try {
                        Font f3 = new Font("Lucida Calligraphy", Font.PLAIN, 20);
                        link = new URI(CoursesAvailable.Material);
                        JLabel hyperLink = new JLabel("Click Here");
                        hyperLink.setForeground(Color.BLUE.darker());
                        hyperLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        hyperLink.setBounds(500, 220, 150, 40);
                        hyperLink.setFont(f3);

                        hyperLink.addMouseListener(new MouseAdapter() {
                            public void mouseClicked(MouseEvent me) {
                                if (Desktop.isDesktopSupported()) {
                                    try {
                                        Desktop.getDesktop().browse(link);
                                    } catch (Exception excep) {
                                        System.out.println(excep);
                                    }
                                } else {
                                    System.out.println("Desktop is not supported");
                                }
                            }
                        });
                    } catch (Exception ex) {
                        System.out.println(ex);
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

}
