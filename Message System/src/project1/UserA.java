/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;

import java.util.ArrayList;
import static project1.Main_application.dm;
import static project1.Main_application.in;

/**
 *
 * @author karag
 */
public class UserA extends SimpleUser {

    public void performReadMessageFromAllUsers() {
        dm.readTable("data", "msg_id");
        Helper.pauseExecution();
    }

    public void performReadMessageFromConversation() {
        System.out.println("Please give a Sender and a Receiver or type '0' to any value to return.");
        String sender = in.nextLine();
        String receiver = in.nextLine();
        if (sender.equals("0") || receiver.equals("0")) {
            return;
        }
        ArrayList list = dm.readConversation(sender, receiver, false);
        if (list.isEmpty()) {
            System.out.println("There is no conversation between those 2 member");
            System.out.println("or you gave wrong usernames!");
            return;
        }
        Helper.pauseExecution();
    }

    public void performReadMessageSentFromUser() {
        System.out.println("Please insert the username of the user you want to see his sent messages or type '0' to exit!");
        String sender = in.nextLine();
        if (sender.equals("0")) {
            return;
        }
        if (dm.readConversation(sender).isEmpty()) {
            System.out.println("There is no such username or he has no sent messages!!");
        }
        Helper.pauseExecution();
    }

    public void performReadMessageReceivedFromUser() {
        System.out.println("Please insert the username of the user you want to see his received messages or type '0' to exit!");
        String receiver = in.nextLine();
        if (receiver.equals("0")) {
            return;
        }
        if (dm.readAllMessages("receiver", receiver,false).isEmpty()) {
            System.out.println("There is no such username or he hasn't received anything!");
        }
        Helper.pauseExecution();
    }

}
