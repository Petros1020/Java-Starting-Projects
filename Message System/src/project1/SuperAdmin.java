/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;


import static project1.Main_application.dm;
import static project1.Main_application.in;

/**
 *
 * @author karag
 */
public class SuperAdmin extends UserC {

    public void performReadUsers() {
        dm.readTable("user", "username");
        Helper.pauseExecution();
    }

    public void performCreateUser() {
        System.out.println("Please give me a new user name");
        boolean bool = true;
        String usr=null;
        while (bool) {
            usr = in.nextLine();
            String exists = dm.oneInputInfo("user", "username", usr, "username");
            if (!usr.equals(exists)) {
                bool = false;
            } else {
                System.out.println("This username already exists! Please give another one!");
            }
        }
        System.out.println("Please give me a password for this user");
        String psw = in.nextLine();
        String jur = Helper.checkJurisdiction();
        System.out.println("Are you sure you want to insert a user with username = " + usr + " password = " + psw + " and jurisdiction = " + jur + " ?");
        if (!Helper.requestConfirmation()) {
            return;
        }
        dm.insertUser(usr, psw, jur);
        System.out.println("Insertion completed!");
    }

    public void performEditUser() {
        String usr = Helper.correctInputs("user", "username", "username");
        if (usr.equals("0")) {
            return;
        }
        System.out.println("Please type which property from 'username', 'password' or 'jurisdiction' you want to alter");
        String val = in.nextLine();
        while (!val.equals("username") && !val.equals("password") && !val.equals("jurisdiction")) {
            System.out.println(" this input is incorrect! You can only choose between 'username', 'password' and 'jurisdiction'!");
            val = in.nextLine();
        }
        String newval = "";
        if (val.equals("jurisdiction")) {
            newval = Helper.checkJurisdiction();
        } else {
            System.out.println("Please type the new " + val);
            newval = in.nextLine();
        }
        System.out.println("Are you sure you want change " + val + " into '" + newval + "'?");
        if (!Helper.requestConfirmation()) {
            return;
        }
        dm.alterUser(usr, val, newval);
        System.out.println("Update done!");
    }

    public void performDeleteUser() {

        dm.readTable("user", "username");
        System.out.println();
        System.out.println("Please select the username of the user you want to delete");
        String del = Helper.correctInputs("user", "username", "username");
        if (del.equals("0")) {
            return;
        }
        System.out.println("Are you sure you want to delete '" + del + "'?");
        if (!Helper.requestConfirmation()) {
            return;
        }
        dm.deleteFromTable("user", "username", del);
        System.out.println("Deletion done!");
    }

}
