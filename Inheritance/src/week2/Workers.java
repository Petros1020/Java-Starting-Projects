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
public class Workers extends Human{

    private String firstName;
    private String lastName;
    private double weekSalary;
    private double workHours;
    private static int numberOfWorkers = 0;

    public Workers(String firstName, String lastName, double weekSalary, double workHours) {
        super(firstName,lastName);
        setWeekSalary(weekSalary);
        setWorkHours(workHours);
        numberOfWorkers++;
    }

    public Workers(String firstName, String lastName) {
        super(firstName,lastName);
        numberOfWorkers++;
    }


    public double getWeekSalary() {
        return weekSalary;
    }

    public void setWeekSalary(double weekSalary) {
        //Checking if week Salary is more than 10
        if (weekSalary > 10) {
            // Rounding of week salary to two decimals.
            weekSalary = weekSalary * 100;
            weekSalary = Math.round(weekSalary);
            weekSalary = weekSalary / 100;
            this.weekSalary = weekSalary;
        } else {
            throw new IllegalArgumentException("The week salary should be more than 10!");
        }
    }

    public double getWorkHours() {
        return workHours;
    }

    public void setWorkHours(double workHours) {
        //Checking if work hours are between 1 and 12 hours
        if (workHours >= 1 && workHours <= 12) {
            // Rounding of work hours to two decimals 
            workHours = workHours * 100;
            workHours = Math.round(workHours);
            workHours = workHours / 100;
            this.workHours = workHours;
        } else {
            throw new IllegalArgumentException("The work hours should be range from 1 to 12!");
        }
    }
    
    public double moneyByHour(){
        //calculating the salary per hour rounded to 2 decimals
        if (weekSalary ==0 || workHours==0){
            // if a worker without salary or workhours is created
            // the user must set both variables to get the current value
            return 0;
        }
        double x = this.weekSalary  / (this.workHours * 5) * 100;
        x = Math.round(x);
        x = x / 100 ;
        return  x;
    }
    
    static void workersWorking(){
        //method for printing the number of employed workers
        System.out.println("The number of workers that are currently employed is "+ numberOfWorkers);
    } 
    
    @Override
    public String toString() {
        //Results in the requested format
        return "FIrst name : " + getFirstName() + "\n" + "Last Name : " + getLastName() + "\n" + "Week Salary : " + getWeekSalary() + "\n" + "Hours Per Day : " + getWorkHours() + "\n" + "Salary Per Hour : " + moneyByHour();
    }

}
