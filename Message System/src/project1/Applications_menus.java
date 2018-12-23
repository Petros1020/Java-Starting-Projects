/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;

import consoleapp.Menu;
import consoleapp.MenuItem;

/**
 *
 * @author karag
 */
public class Applications_menus {

    static SuperAdmin user = new SuperAdmin();

    // πρώτο μενού - επιλογή login
    public void mainMenu() {
        Menu menu = new Menu();
        menu.setTitle("-->Login Screen<--");
        menu.addItem(new MenuItem("Login", this, "subMainMenu"));
        menu.execute();
    }

    //εδώ γίνεται το login - υπάρχει δυνατότητα εξόδου από την εφαρμογή
    public void subMainMenu() {

        Login_screen lg = new Login_screen();
        lg.login();
        // όταν γίνει το login το σύστημα έλεγχει έαν ο χρήστης έχει νέα μυνήματα.
        // τα προσωπικά μηνύματα εμφανίζονται ως αναγνωσμένα μόνο εάν το επιλέξει εδώ ο χρήστης
        // ή τα δει μέσα από το Manage personal messages --> Read a message
        int newMessages = Main_application.dm.howManyNotSeenMessages(user.getUsername());
        if (newMessages != 0) {
            System.out.println("You have " + newMessages + " new messages!");
            System.out.println("Do you want to view them now?");
            if (Helper.requestConfirmation()) {
                Main_application.dm.notSeenMessages(user.getUsername());
                Helper.pauseExecution();
            }
        }

        Menu menu = new Menu();
        menu.setTitle("Welcome to Peaky Blinders Family\nWhat do you want to do?");
        //ακολουθούν τα διάφορα μενού του συστήματος ανάλογα με τη δικαιοδοσια
        menu.addItem(new MenuItem("Manage personal messages", this, "subMenuUser"));
        switch (user.getJurisdiction()) {
            case "super admin":
                menu.addItem(new MenuItem("Manage database", this, "subMenuAdmin"));
            case "C":
                menu.addItem(new MenuItem("Delete any message", this, "subMenuCAdmin"));
            case "B":
                menu.addItem(new MenuItem("Edit any message", this, "subMenuBCAdmin"));
            case "A":
                menu.addItem(new MenuItem("Read any message", this, "subMenuABCAdmin"));
                break;
            default:
                System.out.println("Unknown Jurisdiction, you are being treated as a normal user!");
        }
        menu.execute();
    }

    public void subMenuUser() {
        Menu menu = new Menu();
        menu.setTitle("-->Manage personal account<--");
        menu.addItem(new MenuItem("Send a message", user, "performSendAMessage"));
        menu.addItem(new MenuItem("Read a message", this, "subMenuReadMessage"));
        menu.addItem(new MenuItem("Edit your messages", user, "performEditMessage"));
        menu.addItem(new MenuItem("Delete your messages", user, "performDeleteMessage"));
        menu.execute();
    }

    public void subMenuReadMessage() {
        Menu menu = new Menu();
        menu.setTitle("What do you want to do?");
        menu.addItem(new MenuItem("View all received messages?", user, "performViewReceived"));
        menu.addItem(new MenuItem("View all sent messeges?", user, "performViewSent"));
        menu.addItem(new MenuItem("View a conversation with another user?", user, "performViewConversation"));
        menu.execute();
    }

    public void subMenuABCAdmin() {
        Menu menu = new Menu();
        menu.setTitle("-->Message reading<--");
        menu.addItem(new MenuItem("View all messages?", user, "performReadMessageFromAllUsers"));
        menu.addItem(new MenuItem("View a conversation?", user, "performReadMessageFromConversation"));
        menu.addItem(new MenuItem("View sent messages from a user?", user, "performReadMessageSentFromUser"));
        menu.addItem(new MenuItem("View received messages from a user?", user, "performReadMessageReceivedFromUser"));
        menu.execute();
    }

    public void subMenuBCAdmin() {
        Menu menu = new Menu();
        menu.setTitle("-->Message editing<--");
        menu.addItem(new MenuItem("View all messages?", user, "performEditMessageFromAllUsers"));
        menu.addItem(new MenuItem("View by conversation?", user, "performEditMessageFromConversation"));
        menu.execute();
    }

    public void subMenuCAdmin() {
        Menu menu = new Menu();
        menu.setTitle("-->Message deletion<--");
        menu.addItem(new MenuItem("View all messages?", user, "performDeleteMessageFromAllUsers"));
        menu.addItem(new MenuItem("View by conversation?", user, "performDeleteMessageFromConversation"));
        menu.execute();
    }

    public void subMenuAdmin() {
        Menu menu = new Menu();
        menu.setTitle("-->Manage database<--");
        menu.addItem(new MenuItem("View all users and their information", user, "performReadUsers"));
        menu.addItem(new MenuItem("Create a new user", user, "performCreateUser"));
        menu.addItem(new MenuItem("Edit the information of an existing user", user, "performEditUser"));
        menu.addItem(new MenuItem("Delete a user", user, "performDeleteUser"));
        menu.execute();
    }
}
