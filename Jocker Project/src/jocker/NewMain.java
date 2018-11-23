/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jocker;

public class NewMain {

    public static void main(String[] args) {

        //creation of a player
        Player ps = new Player(16113, "gr413241234", "Petros", "Karagiannis");
        
        //first Lottery
        Lottery k1 = new Lottery();
        
        
        //creation of 2 Tickets and printing the overidden toString method
        Ticket delt1 = ps.play(5, 1);
        Ticket delt2 = ps.play(5, 1);
        System.out.println("");
        System.out.println(delt1.toString());
        System.out.println("");
        System.out.println(delt2.toString());
        System.out.println("");
        
        //checking if any Tickets won in Lottery k1
        k1.winner(delt1);
        k1.winner(delt2);
        
        
        // more 19 Lotteries
        Lottery k2 = new Lottery();
        Lottery k3 = new Lottery();
        Lottery k4 = new Lottery();
        Lottery k5 = new Lottery();
        Lottery k6 = new Lottery();
        Lottery k7 = new Lottery();
        Lottery k8 = new Lottery();
        Lottery k9 = new Lottery();
        Lottery kl0 = new Lottery();
        Lottery kl1 = new Lottery();
        Lottery kl2 = new Lottery();
        Lottery kl3 = new Lottery();
        Lottery kl4 = new Lottery();
        Lottery kl5 = new Lottery();
        Lottery kl6 = new Lottery();
        Lottery kl7 = new Lottery();
        Lottery kl8 = new Lottery();
        Lottery kl9 = new Lottery();
        Lottery k20 = new Lottery();

        //statistics of Lotteries
        Lottery.statistics();

    }
}
