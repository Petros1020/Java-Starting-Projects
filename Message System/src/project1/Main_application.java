/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;

import java.util.Scanner;

/**
 * S
 *
 * @author karag
 */
public class Main_application {

    static Database_manipulation dm = new Database_manipulation();
    static Scanner in = new Scanner(System.in);
    static Database_creation dc = new Database_creation();
    static final String databaseName = "new_db";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String rootUsername = "root";
        String rootPassword = "1991";

        if (dc.databaseExists(rootUsername, rootPassword, databaseName)) {
            dc.create_database(rootUsername, rootPassword, databaseName);
        }
        //   Application Start, after creating the database
        new Applications_menus().mainMenu();
    }

}
