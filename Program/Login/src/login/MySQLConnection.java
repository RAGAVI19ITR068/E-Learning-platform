/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.sql.DriverManager;
import java.sql.Connection;

public class MySQLConnection {
    public static Connection getConnection(){
        String dbRoot = "jdbc:mysql://";
        String hostName = "localhost:3306/";
        String dbName = "e-learning_plat";
        String dbUrl = dbRoot+hostName+dbName;
        
        String hostUsername = "root";
        String hostPassword = "";
        
        
        Connection myconn = null;
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            myconn = (Connection)DriverManager.getConnection(dbUrl, hostUsername, hostPassword);
        }
        catch(Exception e){
            System.out.println("Mysql : "+e);
        }
        
        return myconn;
    }
}
