/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;

import java.util.Scanner;
import static project1.Main_application.dm;

/**
 *
 * @author karag
 */
public class Login_screen {

    private String username;
    private String password;
    private boolean entry = false;


    public void login() {
        //τσεκάρει εάν υπάρχει το username και password στο σύστημα
        while (entry == false) {
            System.out.println("Please give me your Username or type '0' to leave the platform");
            Scanner in = new Scanner(System.in);
            username = in.nextLine();
            if (username.equals("0")) {
                System.exit(0);
            }

            System.out.println("Please give me your password");
            password = in.nextLine();

            entry = dm.check_userinfo(username, password);

            if (entry == true) {
                System.out.println("You have successfully logged in!");
            } else {
                System.out.println("The Username or the Password are incorrect...");
                System.out.println("Please try again");
            }
        }
        String jurisdiction = this.getJurisdiction(username);
        Applications_menus.user.setUsername(username);
        Applications_menus.user.setJurisdiction(jurisdiction);

    }

    public String getUsername() {
        return username;
    }

    public String getJurisdiction(String username) {
        Database_manipulation dm = new Database_manipulation();
        String jurisdiction = dm.oneInputInfo("user", "username", username, "jurisdiction");
        return jurisdiction;
    }

}
