/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Operation {
    public static boolean isLogin(String username, String password, String usertype, JFrame frame){
        try{
            Connection myConn = MySQLConnection.getConnection();
            String mySqlQuery = "SELECT UID, FirstName, LastName, Email, Mobile, Age, City, Category, Username, Password FROM e_learn "
                    + "WHERE Username = '"+username+"' AND Password = '"+password+"' AND Category = '"+usertype+"'";
            PreparedStatement preparedStatement = myConn.prepareStatement(mySqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                LoginSession.UID = resultSet.getInt("UID");
                LoginSession.Firstname = resultSet.getString("FirstName");
                LoginSession.Lastname = resultSet.getString("LastName");
                LoginSession.UserEmail = resultSet.getString("Email");
                LoginSession.UserMobile = resultSet.getString("Mobile");
                LoginSession.Usertype = resultSet.getString("Category");
                LoginSession.Username = resultSet.getString("Username");
                LoginSession.UserPass = resultSet.getString("Password");
                LoginSession.UserAge = resultSet.getString("Age");
                LoginSession.UserCity = resultSet.getString("City");
                
                return true;
            }
        }catch(Exception exception){
            JOptionPane.showMessageDialog(frame, "Database error: "+exception.getMessage());
        }
        return false;
    }
}
