/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.naturalnumbersinterpretation;

import com.mycompany.naturalnumbersinterpretation.helper.GreekValidator;
import com.mycompany.naturalnumbersinterpretation.helper.PhoneNumberValidator;
import com.mycompany.naturalnumbersinterpretation.phonenumber.PhoneNumber;
import com.mycompany.naturalnumbersinterpretation.phonenumber.PhoneNumberMethods;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author karag
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);
        while (true) {
            System.out.println("Enter a Phone Number or press '0' to exit: ");
            String phoneNumber = reader.nextLine();
            if (phoneNumber.equals("0")) {
                break;
            }

            PhoneNumberValidator phv = new GreekValidator();

            PhoneNumber phn = new PhoneNumber(phoneNumber);
            phv.checkInput(phn.getPhoneNumber());
            System.out.println("Your input is: " +phn.getPhoneNumber());

            List<String> inputNumbers_shrinked = PhoneNumberMethods.shrinkNumber(phn.getSplitted());
            List<List<String>> inputNumbers_extended = PhoneNumberMethods.extendNumber(inputNumbers_shrinked);

            List<String> validationResult = new ArrayList<>();
            PhoneNumberMethods.GenerateCombinations(inputNumbers_extended, validationResult, 0, "");

            validationResult.stream().forEach(s -> phv.NumberValidation(s));
        }
        reader.close();
    }

    

}
