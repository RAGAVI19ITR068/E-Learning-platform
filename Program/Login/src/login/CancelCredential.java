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
public class CancelCredential {

    CancelCredential(JFrame frame) {
        Statement stmt = null;
        Connection myConn = null;

        int id = 0;
        try {

            myConn = MySQLConnection.getConnection();

            String mySqlQuery = "SELECT ID FROM cancel ";

            PreparedStatement preparedStatement = myConn.prepareStatement(mySqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                id = resultSet.getInt("ID");

                String cc = "UPDATE student "
                        + "SET CID = ? "
                        + "WHERE SID = ?";
                PreparedStatement ps = myConn.prepareStatement(cc);
                ps.setString(1, "0");
                ps.setInt(2, id);
                ps.executeUpdate();

                JOptionPane.showMessageDialog(frame, "Hey!, " + LoginSession.Username + " ...\nYou have cancel the creadential of UId - " + id);

                String del = "DELETE FROM cancel "
                        + "WHERE ID = ?";
                PreparedStatement dels = myConn.prepareStatement(del);
                dels.setInt(1, id);
                dels.execute();
                
                String delPay = "DELETE FROM payment";
                PreparedStatement dpst = myConn.prepareStatement(delPay);
                dpst.execute();
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
    }
}
