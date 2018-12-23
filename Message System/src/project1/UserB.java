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
public class UserB extends UserA {


    public void performEditMessageFromAllUsers() {
        ArrayList<String> ids = new ArrayList();
        ids = dm.readTable("data","msg_id");
        System.out.println("Please select the ID of the message you want to delete or type '0' to exit!");
        String edit = Helper.existsChecker(ids);
        if(edit.equals("0")){
            return;
        }
        System.out.println("Please write the new message");
        String msg = in.nextLine();
        System.out.println("Are you sure you want to change message '" + dm.oneInputInfo("data", "msg_id", edit, "message") + "' to '" + msg + "'?");
        if (!Helper.requestConfirmation()) {
            return;
        }
        dm.updateMessage(msg, edit,this.getUsername());
        System.out.println("Message edited!");

    }

    public void performEditMessageFromConversation() {
        System.out.println("Please give a Sender and a Receiver or type '0' to any value to return.");
        String sender = in.nextLine();
        String receiver = in.nextLine();
        if (sender.equals("0") || receiver.equals("0")) {
            return;
        }
        ArrayList list = dm.readConversation(sender, receiver,false);
        if (list.isEmpty()) {
            System.out.println("There is no conversation between those 2 member");
            System.out.println("or you gave wrong usernames!");
        }
        System.out.println("Please select the ID of the message you want to edit or type '0' to exit!");
        String edit = Helper.existsChecker(list);
        if(edit.equals("0")){
            return;
        }
        System.out.println("Please write the new message");
        String msg = in.nextLine();
        System.out.println("Are you sure you want to change message '" + dm.oneInputInfo("data", "msg_id", edit, "message") + "' to '" + msg + "'?");
        if (!Helper.requestConfirmation()) {
            return;
        }
        dm.updateMessage(msg, edit,this.getUsername());
        System.out.println("Message edited!");
    }

}
