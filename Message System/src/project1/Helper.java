/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;

import java.util.ArrayList;
import static project1.Main_application.in;

/**
 *
 * @author karag
 */
public class Helper {

    //checking if the message is of maximum 250 words
    static String messageCheck() {
        String msg = "";

        while (msg.isEmpty() || msg.length() > 250) {
            System.out.println("Please write a message of maximum 250 characters");
            msg = Main_application.in.nextLine();
            if (msg.length() > 250) {
                System.out.println("The message is too long!");
            }
        }
        return msg;
    }

    //checkin if the input exists in the given result set
    static String existsChecker(ArrayList list) {
        String exist = in.nextLine();

        while (!list.contains(exist)) {
            if (exist.equals("0")) {
                return exist;
            }
            System.out.println("Your input is incorrect.");
            System.out.println("Try again!");
            exist = in.nextLine();
        }
        return exist;
    }

    static String checkJurisdiction() {
        // τσεκάρει έαν μπαίνουν σωστές τιμές στο jurisdiction
        boolean juris = true;
        String jur = "";
        System.out.println("Choose user's jurisdiction with values 'super admin', 'A', 'B', 'C', 'user' or type 'exit' to go back");
        while (juris && !jur.equals("exit")) {
            jur = Main_application.in.nextLine();
            if (jur.equals("super admin") || jur.equals("A") || jur.equals("B") || jur.equals("C") || jur.equals("user")) {
                juris = false;
            } else {
                System.out.println("This input is incorrect. You can only give values 'super admin', 'A', 'B', 'C', 'user' for the jurisdiction field.");
            }
        }
        return jur;
    }

    static String correctInputs(String table_name, String askedProperty, String returned_property) {
        //έλεγχει εάν ένα input υπάρχει όντως στο σύστημα
        String propertyInput = null;
        String propertyInput1 = "";
        while (propertyInput == null) {
            System.out.println("Please insert a valid " + askedProperty + " or type 0 to return.");
            propertyInput1 = Main_application.in.nextLine();
            if (propertyInput1.equals("0")) {
                propertyInput = "0";
                break;
            }
            propertyInput = Main_application.dm.oneInputInfo(table_name, askedProperty, propertyInput1, returned_property);
            if (propertyInput == null) {
                System.out.println("This " + askedProperty + " does not exist!");
            }
        }
        return propertyInput;
    }

    public static void pauseExecution() {
        System.out.println("Press ENTER to continue!");
        in.nextLine();
    }

    public static boolean requestConfirmation() {
        String choice = "";
        boolean entry = true;
        while (entry) {
            System.out.println("Are you sure you want to proceed??? (y/n)");
            choice = in.nextLine();
            if (choice.equals("Y") || choice.equals("y")) {
                return true;
            } else if (choice.equals("N") || choice.equals("n")) {
                return false;
            }
        }
        return entry;
    }

}
