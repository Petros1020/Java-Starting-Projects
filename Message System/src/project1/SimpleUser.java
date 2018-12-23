/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import static project1.Main_application.dm;
import static project1.Main_application.in;

/**
 *
 * @author karag
 */
public class SimpleUser {

    private String username;
    private String jurisdiction;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getJurisdiction() {
        return jurisdiction;
    }

    public void setJurisdiction(String jurisdiction) {
        this.jurisdiction = jurisdiction;
    }

    public void performSendAMessage() {

        boolean validUsername = false;
        String receiver = "a";
        String dateTime;
        String msg = "";

        System.out.println("Please write the username of the receiver or type '0' to return!");
        while (!validUsername && !receiver.equals("0")) {
            receiver = in.nextLine();
            if (receiver.equals(dm.oneInputInfo("user", "username", receiver, "username"))) {
                validUsername = true;
            } else {
                System.out.println("There is no member with such username in the database!");
                System.out.println("Please try again!");
            }
        }
        if (receiver.equals("0")) {
            return;
        }
        msg = Helper.messageCheck();
        dateTime = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy").format(Calendar.getInstance().getTime());
        dm.sendMsg(receiver, this.getUsername(), dateTime, msg);
    }

    public void performEditMessage() {
        //μπορεί να κάνει edit μόνο τα μηνύματα που έχει στείλει
        ArrayList<String> ids = new ArrayList<String>();
        ids = dm.readConversation(this.getUsername());
        System.out.println("\nPlease select the ID of the message you want to edit or type '0' to exit");
        String edit = in.nextLine();

        while (!ids.contains(edit)) {
            if (edit.equals("0")) {
                return;
            }
            System.out.println("Message ID does not exist");
            System.out.println("Try again ot type '0' to exit!");
            edit = in.nextLine();
        }
        String msg = Helper.messageCheck();
        System.out.println("Are you sure you want to change message '" + dm.oneInputInfo("data", "msg_id", edit, "message") + "' to '" + msg + "'?");
        if (!Helper.requestConfirmation()) {
            return;
        }
        dm.updateMessage(msg, edit, this.getUsername());
        System.out.println("Edit done!");
    }

    public void performDeleteMessage() {
        //μπορεί να κάνει delete μόνο τα μηνύματα που έχει στείλει
        ArrayList<String> ids = new ArrayList<String>();
        ids = dm.readConversation(this.getUsername());
        System.out.println("\nPlease select the ID of the message you want to delete or type '0' to exit!");

        String del = Helper.existsChecker(ids);
        if (del.equals("0")) {
            return;
        }
        System.out.println("Are you sure you want to delete the message with id " + del + "?");
        if (!Helper.requestConfirmation()) {
            return;
        }
        dm.deleteFromTable("data", "msg_id", del);
        System.out.println("Message deleted!");
    }

    public void performViewReceived() {
        dm.readAllMessages("receiver", this.getUsername(),true);

        Helper.pauseExecution();
    }

    public void performViewSent() {
        dm.readAllMessages("sender", this.getUsername(),false);
        Helper.pauseExecution();
    }

    public void performViewConversation() {
        String sender = "a";
        String exists = "b";
        while (!sender.equals(exists)) {
            System.out.println("Please give the name of the user that you want to see the conversation or type '0' to exit!");
            sender = in.nextLine();
            exists = dm.oneInputInfo("user", "username", sender, "username");
            if (sender.equals("0")) {
                return;
            }
            if (!sender.equals(exists)) {
                System.out.println("This username does not exist! please try again!");
            }
        }
        dm.readConversation(this.getUsername(), sender,true);
        Helper.pauseExecution();
    }

}
