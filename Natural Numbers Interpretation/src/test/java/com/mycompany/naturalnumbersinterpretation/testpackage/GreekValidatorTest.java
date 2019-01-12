/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.naturalnumbersinterpretation.testpackage;

import com.mycompany.naturalnumbersinterpretation.helper.GreekValidator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author karag
 */
public class GreekValidatorTest {

    private GreekValidator grv = new GreekValidator();

    public GreekValidatorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getPhoneNumberTest() {

        assertEquals("6947022514", grv.getPhoneNumber("694 70 22 514"));
        assertEquals("0030694702251", grv.getPhoneNumber("0 0 30 694 70 22 51"));
        assertEquals("694702", grv.getPhoneNumber("694 70 2"));
        assertEquals("1234", grv.getPhoneNumber("1  2    3        4"));
    }

    @Test
    public void NumberValidationTest() {
        String number1 = "2106857990";
        String number2 = "6985682247";
        String number3 = "00306987625418";
        String number4 = "00302682065897";
        String number5 = "210987321568";
        String number6 = "00306896587532";
        assertEquals(number1+" [phone number: VALID]", grv.NumberValidation(number1));
        assertEquals(number2+" [phone number: VALID]", grv.NumberValidation(number2));
        assertEquals(number3+" [phone number: VALID]", grv.NumberValidation(number3));
        assertEquals(number4+" [phone number: VALID]", grv.NumberValidation(number4));
        assertEquals(number5+" [phone number: INVALID]", grv.NumberValidation(number5));
        assertEquals(number6+" [phone number: INVALID]", grv.NumberValidation(number6));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void checkInputTest(){
        grv.checkInput("234 34 a");
    }

}
