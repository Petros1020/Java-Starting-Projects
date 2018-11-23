/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package week2;

/**
 *
 * @author karag
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        Workers st2 = new Workers("petros","karagiannis",100.1,5);
        Workers st3 = new Workers("marios","papamixail");
        Students st4 = new Students("Giorgos","Georgiou","d32r434");
        Students st5 = new Students("Epaminondas", "mixos");
        
        System.out.println(st2.toString());
        System.out.println("");
        System.out.println(st4.toString());
        System.out.println("");
        Workers.workersWorking();
        
        //Incorrect entries
        //Workers w = new Workers("Mi","Nontas", 100,6);
        
    }
    
}
