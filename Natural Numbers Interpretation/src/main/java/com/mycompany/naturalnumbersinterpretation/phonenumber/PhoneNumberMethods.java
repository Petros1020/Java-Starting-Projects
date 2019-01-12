/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.naturalnumbersinterpretation.phonenumber;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author karag
 */
public class PhoneNumberMethods {

    /*
    shrinkNumber
    The purpose of this method is to receive an array with the user's numbers
    and export a list with all the ambiguities shrinked
    @Param String[] inputNumbers - an array of strings which containing 
                                   the number sequence given                            
    @Returns List<Integer> inputNumbers_shrinked - A list of Integers based on the given array.
                            input example[0,0,30,69,700,24,1,3,50,2]
                           output example[0,0,30,69,  724 ,1,3, 52 ]
     */
    public static List<String> shrinkNumber(final String[] inputNumbers) {
        List<String> inputNumbers_shrinked = new ArrayList<>();
        int length = inputNumbers.length;
        for (int i = 0; i < length; i++) {
            int num = Integer.parseInt(inputNumbers[i]);
            //if num is 2-digit
            if (inputNumbers[i].length() == 2) {
                // if num ends in 0, there is a next number, next number is 1-digit and next number is not 0 (e.g. 30 3 -> 33 )
                if (num % 10 == 0 && length - 1 > i && inputNumbers[i + 1].length() == 1 && !inputNumbers[i + 1].equals("0")) {
                    num = num + Integer.parseInt(inputNumbers[i + 1]);//add the two numbers
                    i++;//skip the added number                   
                }
                //if num is 3-digit
            } else if (inputNumbers[i].length() == 3) {
                //if num ends in 00
                if (num % 100 == 0) {
                    //if there is a next number, next number is 1-digit and next number is not 0 (e.g. 700 5 ->705)
                    if (length - 1 > i && inputNumbers[i + 1].length() == 1 && !inputNumbers[i + 1].equals("0")) {
                        num = num + Integer.parseInt(inputNumbers[i + 1]);
                        i++;
                        //else if there is a next number and next number is 2-digit (e.g. 700 50 6 ->750 6)
                    } else if (length - 1 > i && inputNumbers[i + 1].length() == 2) {
                        num = num + Integer.parseInt(inputNumbers[i + 1]);
                        i++;
                        //if new num ends in 0, there is a another next number, this number is 1-digit and not 0 (e.g. 700 50 6 ->750 6 ->756)
                        if (num % 10 == 0 && length - 1 > i && inputNumbers[i + 1].length() == 1 && !inputNumbers[i + 1].equals("0")) {
                            num = num + Integer.parseInt(inputNumbers[i + 1]);
                            i++;
                        }
                    }
                    // if num ends in 0, there is a next number, next number is 1-digit and next number is not 0 (e.g. 850 6 ->856)
                } else if (num % 10 == 0 && length - 1 > i && inputNumbers[i + 1].length() == 1 && !inputNumbers[i + 1].equals("0")) {
                    num = num + Integer.parseInt(inputNumbers[i + 1]);
                    i++;
                }
            }
            inputNumbers_shrinked.add(String.valueOf(num));
        }
        return inputNumbers_shrinked;
    }

    /*
    greekNumberValidation
    @Param List<Integer> inputNumbers_shrinked - a shrinked list
    @Returns List<List<String>> inputNumbers_extended - a list in which, each 
                                number is considered as a list of all possible 
                                interpretations
    input example [7, 69 , 723]
    output example [[7],[69, 609],[723, 7203, 70023, 700203]]
     */
    public static List<List<String>> extendNumber(List<String> inputNumbers_shrinked) {
        List<List<String>> inputNumbers_extended = new ArrayList<>();
        for (String num_Str : inputNumbers_shrinked) {
            int len = num_Str.length();
            List<String> possibleExtentions = new ArrayList<>();
            //if num is 1-digit
            if (len == 1) {
                possibleExtentions.add(num_Str); //add itself
                //if num is 2-digit
            } else if (len == 2) {
                String tensDigit = num_Str.substring(0, 1);
                String unitsDigit = num_Str.substring(1, 2);
                possibleExtentions.add(num_Str);//add itself
                if (!unitsDigit.equals("0")) {
                    possibleExtentions.add(tensDigit + "0" + unitsDigit); // add tens digit ambiguity (e.g. 65 -> 605)
                }
                //if num is 3-digit
            } else {
                String hundrendsDigit = num_Str.substring(0, 1);
                String tensDigit = num_Str.substring(1, 2);
                String unitsDigit = num_Str.substring(2, 3);
                possibleExtentions.add(num_Str);//add itself
                //if tens digit is not 0
                if (!tensDigit.equals("0")) {
                    possibleExtentions.add(hundrendsDigit + "00" + tensDigit + unitsDigit);//add hundrends digit ambiguity (e.g. 720 -> 70020)
                    //if units digit is also 0
                    if (!unitsDigit.equals("0")) {
                        possibleExtentions.add(hundrendsDigit + tensDigit + "0" + unitsDigit);//add tens digit ambiguity (e.g. 725 -> 7205)
                        possibleExtentions.add(hundrendsDigit + "00" + tensDigit + "0" + unitsDigit);//add hundreds and tens digit ambiguity (e.g. 725 -> 700205)
                    }
                    //if tens digit is 0 and units digit is not 0
                } else if (!unitsDigit.equals("0")) {
                    possibleExtentions.add(hundrendsDigit + "00" + unitsDigit);//add hundrends digit ambiguity (e.g. 705 -> 7005)
                }
            }
            inputNumbers_extended.add(possibleExtentions);
        }
        return inputNumbers_extended;
    }

    /*
    GenerateCombinations
    recursive method to generate all possible combinations
    @Param List<List<String>> Lists - Lists to combine
    @Param List<String> result - empty list to be filled with the combinations
    @Param int depth - depth of lists (initialized value to 0)
    @Param String current - starting string to be build as a combination (initialized value to "")
     */
    public static void GenerateCombinations(List<List<String>> Lists, List<String> result, int depth, String current) {
        if (depth == Lists.size()) {
            result.add(current);
            return;
        }

        for (int i = 0; i < Lists.get(depth).size(); ++i) {
            GenerateCombinations(Lists, result, depth + 1, current + Lists.get(depth).get(i));
        }
    }
}
