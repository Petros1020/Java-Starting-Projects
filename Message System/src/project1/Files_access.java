/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author karag
 */
public class Files_access {

    static void fileLogs(String sender, String receiver,String dateTime,String message) {
        String first;
        String second;
        
        // βάζω τα σε συγκεκριμένη σειρά χώρις να με νοιάζει ποια θα είναι αυτή, αρκεί να είναι ίδια πάντα
        // με σκοπό να γινέται append πάντα στο ίδιο txt η συνομιλία μεταξύ 2 ατόμων
        if (sender.compareTo(receiver) < 0) {
            first = sender;
            second = receiver;
        } else {
            first = receiver;
            second = sender;
        }

        try {
            File dir = new File("logs");
            //δημιουργία φακέλου εάν δεν υπάρχει
            // ο φάκελος θα δημιουργηθεί εκεί που θα τρέξει η main (στο classes στην περίπτωσή μας)
            if (!dir.exists()) {
                dir.mkdir();
            }
            //δημιουργία txt εάν δεν υπάρχει
            File file = new File("logs/conversationBETWEEN"+first+"AND"+second+".txt");
            //εγγραφή στο txt
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("logs/conversationBETWEEN"+first+"AND"+second+".txt", true)));
            if (!file.exists()) {
                file.createNewFile();
            }
            out.println(sender+" sent to "+receiver+" on "+dateTime+" the message: "+message);
            out.close();
        } catch (IOException e) {
            e.getMessage();
        } 
    }
}
