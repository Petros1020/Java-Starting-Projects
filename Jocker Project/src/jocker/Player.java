/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jocker;

import java.util.HashMap;
import java.util.Map;

public class Player {

    private int afm;
    private String iban;
    private String firstName;
    private String lastName;
    static Map<Integer, Player> playersAFM = new HashMap<Integer, Player>();

    public Player(int afm, String iban, String firstName, String lastName) {
        this.afm = afm;
        this.iban = iban;
        this.firstName = firstName;
        this.lastName = lastName;
        playersAFM.put(afm, this);
    }
    
    public int getAfm() {
        return afm;
    }

    public String getIban() {
        return iban;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    static void afmToPlayer(int afm) {
        Player pp = playersAFM.get(afm);
        System.out.println("Player's first name: "+pp.getFirstName()+"\nPlayer's last name: "+pp.getLastName()+"\nPlayer's Iban: "+pp.getIban()+"\nPlayer's afm: "+pp.getAfm());
    }
    
    //method that created a Ticket from the current player
    public Ticket play(int numbers, int jocker){
        Ticket x = new Ticket(this.afm,numbers,jocker);
        return x;
    }
    
    @Override
    public String toString(){
       return "Player's Last Name is: "+lastName+"\nPlayer's First Name is: "+firstName+"\nPlayer's afm is: "+afm+"\nPlayer's Iban is: "+iban;       
    }
    
}
