/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jocker;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class Lottery {

    Random ran = new Random();
    Set<Integer> klirwsi = new HashSet<Integer>();
    private int lucky[] = new int[5];
    private int i = 0;
    private int y;
    private int winningNumbers;
    private int winningJocker;
    static int statics[] = new int[45];

    public Lottery() {
        //constructor that randomly generates 5 numbers and 1 jocker number
        while (i < 5) {
            int x = ran.nextInt(45) + 1;
            if (klirwsi.contains(x)) {
                continue;
            } else {
                klirwsi.add(x);
                lucky[i] = x;
                i++;
            }
        }
        this.y = ran.nextInt(20) + 1;

        /*      You can fix a Lottery here! 5 Numbers and one Jocker Number       
        lucky[0]=1;
        lucky[1]=2;
        lucky[2]=3;
        lucky[3]=4;
        lucky[4]=5;        
        this.y=1;     //Jocker Number    
         */
        for (int r : lucky) {
            // counting which numbers are being selected for the statistics
            statics[r - 1]++;
        }
    }

    public void winner(Ticket delt) {
        //method that checks if a Ticket won
        Iterator itr1 = delt.getHsNumbers().iterator();
        while (itr1.hasNext()) {
            int dd = (int) itr1.next();
            winningNumbers = 0;
            winningJocker = 0;
            for (int z : lucky) {
                if (delt.getHsNumbers().contains(z)) {
                    winningNumbers++;
                }
            }
            if (delt.getHsJockerNumbers().contains(this.y)) {
                winningJocker++;
            }
        }
        if (winningNumbers == 5 && winningJocker == 1) {
            System.out.println("You won the first prize with 5+1 correct numbers!!!!");
        } else if (winningNumbers > 3) {
            System.out.println("You won the second prize with 4 correct numbers!!!!");
        } else {
            System.out.println("You lost...");
        }
    }

    public static void statistics() {
        //in the static Array "statics" are saved the times that a single number
        //has appeared in the i+1 position
        // so, if statics[0]=3 it means that (i+1) the 1 number has appeared 3 times
        //I find 3 maximum and 3 minimum values of the frequency that the numbers appeared

        int max1 = -1;
        int max2 = -1;
        int max3 = -1;
        int min1 = 2147483647;
        int min2 = 2147483647;
        int min3 = 2147483647;

        for (int i : statics) {
            //checking only numbers that have appeared more than 0 times
            if (i >= max1 && i != 0) {
                if (i > max1) {
                    max3 = max2;
                    max2 = max1;
                    max1 = i;
                }
            } else if (i >= max2 && i != 0) {
                if (i > max2) {
                    max3 = max2;
                    max2 = i;
                }
            } else if (i >= max3 && i != 0) {
                max3 = i;
            }
            if (i <= min1 && i != 0) {
                if (i < min1) {
                    min3 = min2;
                    min2 = min1;
                    min1 = i;
                }
            } else if (i <= min2 && i != 0) {
                if (i < min2) {
                    min3 = min2;
                    min2 = i;
                }
            } else if (i <= min3 && i != 0) {
                min3 = i;
            }

        }
        System.out.println("");

        Set<Integer> setmax1 = new HashSet<>();
        Set<Integer> setmax2 = new HashSet<>();
        Set<Integer> setmax3 = new HashSet<>();
        Set<Integer> setmin1 = new HashSet<>();
        Set<Integer> setmin2 = new HashSet<>();
        Set<Integer> setmin3 = new HashSet<>();
        for (int i = 0; i < 45; i++) {
            if (max1 == statics[i]) {
                setmax1.add(i + 1);
            }
            if (max2 == statics[i]) {
                setmax2.add(i + 1);
            }

            if (max3 == statics[i]) {
                setmax3.add(i + 1);
            }
            if (min1 == statics[i]) {
                setmin1.add(i + 1);
            }
            if (min2 == statics[i]) {
                setmin2.add(i + 1);
            }
            if (min3 == statics[i]) {
                setmin3.add(i + 1);
            }
        }
        
        //printing only numbers that have appeared
        System.out.println("~~~~~~ 3 numbers that have appeared the most times~~~~~~");
        if (max1 != -1) {
            System.out.println("The numbers that have appeared " + max1 + " times are " + setmax1);
        }
        if (max2 != -1) {
            System.out.println("The numbers that have appeared " + max2 + " times are " + setmax2);
        }
        if (max3 != -1) {
            System.out.println("The numbers that have appeared " + max3 + " times are " + setmax3);
        }
        System.out.println();
        System.out.println("~~~~~~ 3 numbers that have appeared the least times~~~~~~");
        if (min1 != 2147483647) {
            System.out.println("The numbers that have appeared " + min1 + " times are " + setmin1);
        }
        if (min2 != 2147483647) {
            System.out.println("The numbers that have appeared " + min2 + " times are " + setmin2);
        }
        if (min3 != 2147483647) {
            System.out.println("The numbers that have appeared " + min3 + " times are " + setmin3);
        }
    }

}
