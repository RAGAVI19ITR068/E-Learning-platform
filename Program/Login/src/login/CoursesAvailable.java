/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Ragavi
 */
public class CoursesAvailable {
    
    public static String CID;
    public static String CourseName;
    public static String Material;
    public static String courses[];
    
    public static String getCourses(JFrame frame) {
        
        Statement stmt = null;
        Connection myConn = null;
        String courseId, courseName;
        String courseList = "";
        
        try {
            myConn = MySQLConnection.getConnection();

            String mySqlQuery = "SELECT CID, CourseName FROM COURSE";
            PreparedStatement preparedStatement = myConn.prepareStatement(mySqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            int i = 1;
            int count = CourseCount.count(frame);
            courses = new String[count];
            while (resultSet.next()) {
                courseId = resultSet.getString("CID");
                courseName = resultSet.getString("CourseName");

                courseList += i + ").     " + courseId + "  -  " + courseName + "\n";
                
                courses[i-1] = courseName;
                i++;
            }

        } catch (Exception exception) {
            JOptionPane.showMessageDialog(frame, "Something went wrong : " + exception);
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
        return courseList;
    }
    
    public static boolean ViewCourse(JFrame frame, String id){
        Statement stmt = null;
        Connection myConn = null;
        
        try {
            myConn = MySQLConnection.getConnection();

            String mySqlQuery = "SELECT CID, CourseName, Material FROM COURSE "
                    + "WHERE CID = '"+id+"'";
            PreparedStatement preparedStatement = myConn.prepareStatement(mySqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                CID = resultSet.getString("CID");
                CourseName = resultSet.getString("CourseName");
                Material = resultSet.getString("Material");
            }

        } catch (Exception exception) {
            JOptionPane.showMessageDialog(frame, "Something went wrong : " + exception);
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
        return true;
    }
}
