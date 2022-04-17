/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;

class Index extends JFrame implements ActionListener {

    JLabel wel, name, email, mob, empty, nonEmpty, student_empty, logout, edit;
    JButton create, view, get, cancel, payment, users;
    JComboBox cList;
    URI matLink;

    Index() {

        ImageIcon icon = new ImageIcon("D:\\5th Sem\\SE\\E-Learning\\image\\indexbg.jpg");
        JLabel background = new JLabel(icon);
        background.setBounds(0, 0, 1920, 1030);
        background.setLayout(null);
        add(background);

        JPanel profpan = new JPanel();

        ImageIcon profIcon = new ImageIcon("D:\\5th Sem\\SE\\E-Learning\\image\\user.png");
        JLabel profile = new JLabel(profIcon);
        profile.setBounds(270, 50, 200, 270);
        profpan.add(profile);

        Font f1 = new Font("Bradley Hand ITC", Font.BOLD, 30);
        Font f2 = new Font("Lucida Calligraphy", Font.BOLD, 40);
        Font f3 = new Font("Lucida Calligraphy", Font.PLAIN, 18);

        wel = new JLabel("Hello, " + LoginSession.Username + " ...");
        wel.setBounds(200, 350, 450, 40);
        wel.setBackground(new Color(36, 31, 30));
        wel.setForeground(new Color(255, 255, 255));
        wel.setFont(f1);
        profpan.add(wel);

        name = new JLabel("Name    :  " + LoginSession.Firstname + " " + LoginSession.Lastname);
        name.setBounds(60, 420, 600, 40);
        name.setBackground(new Color(36, 31, 30));
        name.setForeground(new Color(255, 255, 255));
        name.setFont(f1);
        profpan.add(name);

        email = new JLabel("Email   :  " + LoginSession.UserEmail);
        email.setBounds(60, 470, 650, 40);
        email.setBackground(new Color(36, 31, 30));
        email.setForeground(new Color(255, 255, 255));
        email.setFont(f1);
        profpan.add(email);

        mob = new JLabel("Mobile   :  " + LoginSession.UserMobile);
        mob.setBounds(60, 520, 700, 40);
        mob.setBackground(new Color(36, 31, 30));
        mob.setForeground(new Color(255, 255, 255));
        mob.setFont(f1);
        profpan.add(mob);

        ImageIcon editIcon = new ImageIcon("D:\\5th Sem\\SE\\E-Learning\\image\\edit_pen.png");
        edit = new JLabel(editIcon);
        edit.setBounds(480, 320, 80, 80);
        edit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        profpan.add(edit);
        edit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                EditProfile ep = new EditProfile();
                ep.setVisible(true);
                setVisible(false);
            }
        });

        ImageIcon logoutIcon = new ImageIcon("D:\\5th Sem\\SE\\E-Learning\\image\\logout.png");
        logout = new JLabel(logoutIcon);
        logout.setBounds(270, 630, 200, 200);
        logout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        profpan.add(logout);
        logout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                setVisible(false);
                Logout.logout();
            }
        });

        profpan.setBounds(270, 50, 750, 900);
        profpan.setBackground(new Color(36, 31, 30));
        profpan.setLayout(null);
        background.add(profpan);

        if ("Admin".equals(LoginSession.Usertype)) {
            JPanel adminpane = new JPanel();

            cancel = new JButton("CANCEL CREDENTIAL");
            cancel.setBounds(140, 300, 300, 40);
            cancel.setBackground(new Color(13, 71, 161));
            cancel.setForeground(new Color(255, 255, 255));
            cancel.setFont(f3);
            adminpane.add(cancel);
            cancel.addActionListener(this);

            payment = new JButton("CHECK PAYMENT");
            payment.setBounds(160, 400, 270, 40);
            payment.setBackground(new Color(0, 96, 100));
            payment.setForeground(new Color(255, 255, 255));
            payment.setFont(f3);
            adminpane.add(payment);
            payment.addActionListener(this);

            users = new JButton("USERS");
            users.setBounds(210, 500, 150, 40);
            users.setBackground(new Color(130, 119, 23));
            users.setForeground(new Color(255, 255, 255));
            users.setFont(f3);
            adminpane.add(users);
            users.addActionListener(this);

            adminpane.setLayout(null);
            adminpane.setBackground(Color.cyan);
            adminpane.setBorder(BorderFactory.createLineBorder(Color.black, 5, true));
            adminpane.setBounds(1020, 50, 580, 900);
            adminpane.setVisible(true);
            background.add(adminpane);
        }
        if ("Teacher".equals(LoginSession.Usertype)) {
            JPanel teachpane = new JPanel();

            int count = CourseCount.count(this);

            if (count == 0) {
                empty = new JLabel("No course available");
                empty.setBounds(50, 200, 480, 50);
                empty.setForeground(new Color(36, 31, 30));
                empty.setFont(f2);
                teachpane.add(empty);

                JLabel emp = new JLabel("in the course record");
                emp.setBounds(30, 270, 520, 40);
                emp.setForeground(new Color(36, 31, 30));
                emp.setFont(f2);
                teachpane.add(emp);

                create = new JButton("CREATE COURSE");
                create.setBackground(new Color(78, 52, 46));
                create.setForeground(new Color(255, 255, 255));
                create.setFont(f3);
                create.setBounds(170, 380, 300, 40);
                create.addActionListener(this);
                teachpane.add(create);
            } else {
                nonEmpty = new JLabel("Available Courses : \n\n");
                nonEmpty.setBounds(80, 200, 480, 50);
                nonEmpty.setForeground(new Color(36, 31, 30));
                nonEmpty.setFont(f2);
                teachpane.add(nonEmpty);

                String courseList = CoursesAvailable.getCourses(this);
                JTextArea courseAvail = new JTextArea();
                courseAvail.setText(courseList);
                courseAvail.setEditable(false);
                courseAvail.setBounds(150, 280, 300, 200);
                courseAvail.setBackground(new Color(224, 224, 224));
                courseAvail.setForeground(new Color(36, 31, 30));
                courseAvail.setFont(f3);
                courseAvail.setVisible(true);
                teachpane.add(courseAvail);

//                JScrollPane scroll = new JScrollPane(courseAvail);
//                teachpane.add(scroll);
                view = new JButton("VIEW COURSE");
                view.setBackground(new Color(244, 143, 177));
                view.setBounds(200, 500, 200, 40);
                view.setBackground(new Color(69, 90, 100));
                view.setForeground(new Color(255, 255, 255));
                view.setFont(f3);
                view.addActionListener(this);
                teachpane.add(view);

                create = new JButton("CREATE SOME MORE COURSE");
                create.setBackground(new Color(97, 97, 97));
                create.setForeground(new Color(255, 255, 255));
                create.setFont(f3);
                create.setBounds(100, 580, 400, 40);
                create.addActionListener(this);
                teachpane.add(create);
            }

            teachpane.setLayout(null);
            teachpane.setBackground(new Color(255, 202, 40));
            teachpane.setBorder(BorderFactory.createLineBorder(Color.black, 5, true));
            teachpane.setBounds(1020, 50, 580, 900);
            teachpane.setVisible(true);
            background.add(teachpane);
        }
        if ("Student".equals(LoginSession.Usertype)) {
            JPanel studentpane = new JPanel();

            int count = CourseCount.count(this);

            if (count == 0) {
                empty = new JLabel("No course available");
                empty.setBounds(50, 380, 480, 50);
                empty.setForeground(new Color(36, 31, 30));
                empty.setFont(f2);
                studentpane.add(empty);

                JLabel emp = new JLabel("in the course record...");
                emp.setBounds(30, 450, 480, 40);
                emp.setForeground(new Color(36, 31, 30));
                emp.setFont(f2);
                studentpane.add(emp);
            } else {
                Statement stmt = null;
                Connection myConn = null;
                String course_student = "";

                try {
                    myConn = MySQLConnection.getConnection();

                    String mySqlQuery = "SELECT CID FROM student WHERE SID = ?";
                    PreparedStatement preparedStatement = myConn.prepareStatement(mySqlQuery);
                    preparedStatement.setInt(1, LoginSession.UID);
                    ResultSet resultSet = preparedStatement.executeQuery();

                    while (resultSet.next()) {
                        course_student = resultSet.getString("CID");
                    }

                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(this, "Something went wrong in student section : " + exception);
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

                CoursesAvailable.getCourses(this);

                if (course_student.equals("0")) {
                    student_empty = new JLabel("Please Select ");
                    student_empty.setBounds(140, 200, 600, 50);
                    student_empty.setForeground(new Color(36, 31, 30));
                    student_empty.setFont(f2);
                    studentpane.add(student_empty);

                    JLabel emp = new JLabel("any one of the course");
                    emp.setBounds(50, 250, 600, 50);
                    emp.setForeground(new Color(36, 31, 30));
                    emp.setFont(f2);
                    studentpane.add(emp);

                    cList = new JComboBox(CoursesAvailable.courses);
                    cList.setBounds(200, 350, 200, 50);
                    cList.setForeground(new Color(36, 31, 30));
                    cList.setBackground(new Color(158, 158, 158));
                    cList.setFont(f3);
                    studentpane.add(cList);

                    get = new JButton("GET COURSE");
                    get.setBounds(180, 430, 250, 40);
                    get.setBackground(new Color(245, 127, 23));
                    get.setForeground(new Color(36, 31, 30));
                    get.setFont(f3);
                    studentpane.add(get);
                    get.addActionListener(this);

                } else {
                    CoursesAvailable.ViewCourse(this, course_student);

                    JLabel crs_head = new JLabel(CoursesAvailable.CourseName);
                    crs_head.setBounds(180, 300, 500, 50);
                    crs_head.setForeground(new Color(36, 31, 30));
                    crs_head.setFont(f2);
                    studentpane.add(crs_head);

                    JLabel crs_id = new JLabel("        COURSE ID  :  " + CoursesAvailable.CID);
                    crs_id.setBounds(100, 400, 500, 40);
                    crs_id.setForeground(new Color(36, 31, 30));
                    crs_id.setFont(f3);
                    studentpane.add(crs_id);

                    JLabel crs_name = new JLabel("    COURSE NAME  :  " + CoursesAvailable.CourseName);
                    crs_name.setBounds(100, 460, 500, 40);
                    crs_name.setForeground(new Color(36, 31, 30));
                    crs_name.setFont(f3);
                    studentpane.add(crs_name);

                    JLabel crs_mat = new JLabel(" MATERIAL LINK  :  ");
                    crs_mat.setBounds(100, 520, 250, 40);
                    crs_mat.setForeground(new Color(36, 31, 30));
                    crs_mat.setFont(f3);
                    studentpane.add(crs_mat);

                    try {
                        matLink = new URI(CoursesAvailable.Material);

                        JLabel hyperLink = new JLabel("Click Here");
                        hyperLink.setForeground(Color.BLUE.darker());
                        hyperLink.setBounds(320, 520, 150, 40);
                        hyperLink.setFont(f3);
                        hyperLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        studentpane.add(hyperLink);

                        hyperLink.addMouseListener(new MouseAdapter() {
                            public void mouseClicked(MouseEvent me) {
                                if (Desktop.isDesktopSupported()) {
                                    try {
                                        Desktop.getDesktop().browse(matLink);
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

                    int delay = 1000;

                    ActionListener task = new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            Statement st = null;
                            Connection cn = null;

                            try {

                                cn = MySQLConnection.getConnection();

                                String mySqlQuery = "INSERT INTO CANCEL(ID) "
                                        + "VALUES(?)";

                                PreparedStatement preparedStatement = cn.prepareStatement(mySqlQuery);
                                preparedStatement.setInt(1, LoginSession.UID);
                                preparedStatement.execute();

                                JOptionPane.showMessageDialog(null, "Hey, " + LoginSession.Username + "\n Your course is expired ...");
                            } catch (Exception exception) {
                                JOptionPane.showMessageDialog(null, "Something went wrong : " + exception);
                                System.out.println(exception);
                            } finally {
                                try {
                                    if (st != null) {
                                        cn.close();
                                    }
                                } catch (SQLException se) {
                                    System.out.println(se);
                                }
                                try {
                                    if (cn != null) {
                                        cn.close();
                                    }
                                } catch (SQLException se) {
                                    System.out.println(se);
                                }
                            }
                        }
                    };
                    Timer timer = new Timer(delay * 120, task);
                    timer.setRepeats(false);
                    timer.start();
                }
            }

            studentpane.setLayout(null);
            studentpane.setBackground(Color.cyan);

            studentpane.setBorder(BorderFactory.createLineBorder(Color.black, 5, true));
            studentpane.setBounds(1020, 50, 580, 900);
            studentpane.setVisible(true);
            background.add(studentpane);
        }

        setTitle("Welcome to E-Learning Platform");
        setLayout(null);
        setSize(1930, 1030);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == create) {
            CreateCourse cc = new CreateCourse();
            cc.setVisible(true);
            setVisible(false);
        }
        if (e.getSource() == view) {
            JLabel id = new JLabel("Course ID : ");
            String vc_id = JOptionPane.showInputDialog(this, id, "Enter the course ID ...", QUESTION_MESSAGE);
            if (!(vc_id == null)) {
                CourseView view = new CourseView(vc_id);
                view.setVisible(true);
                setVisible(false);
            }
        }
        if (e.getSource() == cancel) {
            new CancelCredential(this);
        }
        if (e.getSource() == payment) {
            Statement stmt = null;
            Connection myConn = null;

            int id = 0;
            try {
                myConn = MySQLConnection.getConnection();

                String getId = "SELECT SID FROM PAYMENT";
                PreparedStatement psId = myConn.prepareStatement(getId);
                ResultSet rsId = psId.executeQuery();

                while (rsId.next()) {
                    id = rsId.getInt("SID");
                    JOptionPane.showMessageDialog(this, " UID   :   " + id + "  have paid successfully to you ...");
                }

            } catch (Exception exception) {
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
        if (e.getSource() == users) {
            int count = CourseCount.countUser(this);
            if (count == 0) {
                JOptionPane.showMessageDialog(this, "No users available!");
            } else {
                new UsersAvail(this, count);
            }
        }
        if (e.getSource() == get) {

            ImageIcon pay = new ImageIcon("D:\\5th Sem\\SE\\E-Learning\\image\\Payment_scan.jpg");
            JOptionPane.showMessageDialog(this, " \nScan it using Gpay\n ", "Payment", JOptionPane.INFORMATION_MESSAGE, pay);
            String sel = (String) cList.getSelectedItem();
            String select = "";

            Statement stmt = null;
            Connection myConn = null;

            try {
                myConn = MySQLConnection.getConnection();

                String getId = "SELECT CID FROM course "
                        + "WHERE CourseName = '" + sel + "'";
                PreparedStatement psId = myConn.prepareStatement(getId);
                ResultSet rsId = psId.executeQuery();

                while (rsId.next()) {
                    select = rsId.getString("CID");
                }

                String mySqlQuery = "UPDATE STUDENT "
                        + "SET CID = ?"
                        + "WHERE SID = ?";
                PreparedStatement preparedStatement = myConn.prepareStatement(mySqlQuery);
                preparedStatement.setString(1, select);
                preparedStatement.setInt(2, LoginSession.UID);
                preparedStatement.executeUpdate();

                String payQuery = "INSERT INTO PAYMENT(SID) "
                        + "VALUES(?)";
                PreparedStatement pst = myConn.prepareStatement(payQuery);
                pst.setInt(1, LoginSession.UID);
                pst.executeUpdate();
            } catch (Exception exception) {
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

            JOptionPane.showMessageDialog(this, "Hey, " + LoginSession.Username + "\n you successfully selected a course called " + sel);

            Index page = new Index();
            page.setVisible(true);
            setVisible(false);
        }

    }
}
