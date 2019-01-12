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
public interface PhoneNumberValidator {

    /*
    getPhoneNumber
    Uses regex to remove whitespaces from phone numbers
    @Param phoneNumber - phone number as String like "30 2 5 58"
    @Returns - phone number as String without whitespace like "302558"
     */
    default String getPhoneNumber(String phoneNumber) {
        phoneNumber = phoneNumber.replaceAll("\\s", "");
        return phoneNumber;
    }
    
        /*
    checkInput
    Throws IllegalArgumentException if the input does not contain only numbers
    @Param phoneNumber - phone number as String like "30 2 5 58"
     */
    default void checkInput(String phoneNumber){
        if (!phoneNumber.matches("[0-9]+")){
            throw new IllegalArgumentException("Bad input");
        }
    }
    
    public String NumberValidation(String phoneNumber);
}
