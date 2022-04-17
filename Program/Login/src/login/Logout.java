/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;


public class Logout {
    public static void logout(){
        LoginSession.isLoggedIn = false;
        CreateLoginForm loginScreen = new CreateLoginForm();
        loginScreen.setVisible(true);
    }
}
