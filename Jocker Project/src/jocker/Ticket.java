/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jocker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;


public class Ticket {

    private HashSet hsNumbers = new HashSet();
    private HashSet hsJockerNumbers = new HashSet();
    private String dateAndTime;
    private int iD;
    static int iDdeltiwn = 10000;

   
    

    public Ticket(int afm, int numbersPlayed, int jockerNumbersPlayed) {
        //checking if this is an actual player
        if (!Player.playersAFM.containsKey(afm)) {
            throw new IllegalArgumentException("The afm number " + afm + " does not match to any registered players!");
        }
        //checking if you played 5-45 numbers or 1-20 jocker numbers
        if (numbersPlayed < 5 || numbersPlayed > 45 || jockerNumbersPlayed < 1 || jockerNumbersPlayed > 20) {
            System.out.println("You can play only 5-45 Numbers and 1-20 Jocker Numbers");
            return;
        } else {
            Scanner sc = new Scanner(System.in);
            hsNumbers = new HashSet();
            int i = 1;
            //choosing the unique numbers 
            while (i < numbersPlayed + 1) {
                System.out.println("Please give me the " + i + " number between 1 and 45!");
                int y = sc.nextInt();
                if (y < 1 || y > 45 || hsNumbers.contains(y)) {
                    System.out.println("Incorrect Number");
                    continue;
                } else {
                    hsNumbers.add(y);
                    i++;
                }
            }
            hsJockerNumbers = new HashSet();
            int j = 1;
            while (j < jockerNumbersPlayed + 1) {
                System.out.println("Please give me the " + j + " Jocker numbers between 1 and 20!");
                int y = sc.nextInt();
                if (y < 1 || y > 20 || hsJockerNumbers.contains(y)) {
                    System.out.println("Incorrect Number");
                    continue;
                } else {
                    hsJockerNumbers.add(y);
                    j++;
                }
            }
            //unique ID deltiou
            iDdeltiwn++;
            this.iD = iDdeltiwn;
            //date and time
            this.dateAndTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());

              
        }
    }

    public HashSet getHsNumbers() {
        return hsNumbers;
    }

    public HashSet getHsJockerNumbers() {
        return hsJockerNumbers;
    }

    public int getiD() {
        return iD;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    @Override
    public String toString(){
        return "Ticket's ID is: "+this.iD+"\nThe date and time played is: "+this.dateAndTime+"\nThe numbers played are: "+this.hsNumbers+"\nThe jocker numbers played are: "+this.hsJockerNumbers;
    }
}
