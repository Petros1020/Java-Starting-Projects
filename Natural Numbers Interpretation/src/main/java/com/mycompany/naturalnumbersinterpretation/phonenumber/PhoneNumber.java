/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.naturalnumbersinterpretation.phonenumber;


/**
 *
 * @author karag
 */
public class PhoneNumber {

    private String phoneNumber;
    private String[] splitted;

    public PhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber.replaceAll("\\s+", "");
        splitted = phoneNumber.split("\\s+");
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String[] getSplitted() {
        return splitted;
    }

    

}
