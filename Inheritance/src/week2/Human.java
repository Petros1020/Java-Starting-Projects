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
public class Human {

    private String firstName;
    private String lastName;

    public Human(String firstName, String lastName) {
        setFirstName(firstName);
        setLastName(lastName);
    }

    public void setFirstName(String firstName) {
        //checking if the lenght of the given name is lower than 3 and if the input is only letters
        if (firstName.length() < 3 || !firstName.matches("[a-zA-z]*")) {
            throw new IllegalArgumentException("The first name should have more than 2 characters that are all letters!");
        }
        //checking if the first letter in the input is lower case. If it is we make it upper case.
        if (Character.isLowerCase(firstName.charAt(0))) {
            String output = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
            this.firstName = output;
        } else {
            this.firstName = firstName;
        }
    }

    public void setLastName(String lastName) {
        //same constraints as in firstName but the length now must me more than 3 letters.
        if (lastName.length() < 4 || !lastName.matches("[a-zA-z]*")) {
            throw new IllegalArgumentException("The last name should have more than 3 characters that are all letters!");
        }
        if (Character.isLowerCase(lastName.charAt(0))) {
            String output = lastName.substring(0, 1).toUpperCase() + lastName.substring(1);
            this.lastName = output;
        } else {
            this.lastName = lastName;
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    
    
}
