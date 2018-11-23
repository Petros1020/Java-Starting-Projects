/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package week2;

public class Students extends Human {

    private String facultyNumber;

    public Students(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public Students(String firstName, String lastName, String facultyNumber) {
        super(firstName,lastName);
        setFacultyNumber(facultyNumber);
    }

    public String getFacultyNumber() {
        return facultyNumber;
    }

    public void setFacultyNumber(String facultyNumber) {
        //checking if the faculty number contains only numbers and letters, and its range should be between 5-10 symbols
        if (!facultyNumber.matches("[a-zA-Z0-9]*") || facultyNumber.length() < 5 || facultyNumber.length() > 10) {
            throw new IllegalArgumentException("The faculty number must contain only letters and numbers, and a length from 5 to 10 symbols! ");
        }
        this.facultyNumber = facultyNumber;
    }

    @Override
    public String toString() {
        //with this method we get our results in the requested format
        return "FIrst name : " + getFirstName() + "\n" + "Last Name : " + getLastName() + "\n" + "Faculty Number : " + getFacultyNumber();
    }

}
