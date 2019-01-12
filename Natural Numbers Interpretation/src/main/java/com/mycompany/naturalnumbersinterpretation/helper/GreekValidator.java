/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.naturalnumbersinterpretation.helper;

/**
 *
 * @author karag
 */
public class GreekValidator implements PhoneNumberValidator {
    /*
    greekNumberValidation
    Greek numbers may have 10 or 14 digits. If they have 10 digits the must 
    start with '2' or '69' and if they have 14 digits they must start with
    '00302' or '003069'
    @Param phoneNumber - phone number as String without whitespaces
    @Returns - Prints if the number is valid or not
     */
    @Override
    public String NumberValidation(String phoneNumber) {
        int numberDigits = phoneNumber.length();
        if ((numberDigits == 10 && phoneNumber.matches("(2|69).*")) || (numberDigits == 14 && phoneNumber.matches("(00302|003069).*"))) {
            System.out.println(phoneNumber + " [phone number: VALID]");
            return phoneNumber + " [phone number: VALID]";            
        } else {
            System.out.println(phoneNumber + " [phone number: INVALID]");
            return phoneNumber + " [phone number: INVALID]";
        }
    }


    
}
