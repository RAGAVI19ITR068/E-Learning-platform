/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Ragavi
 */
public class UsersAvail {

    UsersAvail(JFrame frame, int count) {
        Object[] colHeads = {"UID", "Username", "FirstName", "LastName", "Email", "Mobile", "Category", "Age", "City"};
        Object[][] data = new Object[count][9];
        try{
            Connection myConn = MySQLConnection.getConnection();
            String mySqlQuery = "SELECT UID, FirstName, LastName, Email, Mobile, Age, City, Category, Username FROM e_learn";
            PreparedStatement preparedStatement = myConn.prepareStatement(mySqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            int i=0;
            while(resultSet.next()){
                int j=0;
                data[i][j] = resultSet.getInt("UID");
                j++;
                data[i][j] = resultSet.getString("Username");
                j++;
                data[i][j] = resultSet.getString("FirstName");
                j++;
                data[i][j] = resultSet.getString("LastName");
                j++;
                data[i][j] = resultSet.getString("Email");
                j++;
                data[i][j] = resultSet.getString("Mobile");
                j++;
                data[i][j] = resultSet.getString("Category");
                j++;
                data[i][j] = resultSet.getString("Age");
                j++;
                data[i][j] = resultSet.getString("City");
                i++;
            }
        }catch(Exception exception){
            JOptionPane.showMessageDialog(frame, "Available users : "+exception.getMessage());
        }
        JTable table = new JTable(data, colHeads);
        table.setBounds(10, 10, 1000, 300);
        table.setBackground(new Color(187, 222, 251));

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(10, 10, 1000, 100);
        scroll.setBackground(new Color(187, 222, 251));

        JOptionPane.showMessageDialog(frame, scroll);
    }
}
