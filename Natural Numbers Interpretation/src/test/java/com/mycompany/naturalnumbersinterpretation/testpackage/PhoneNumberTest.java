/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.naturalnumbersinterpretation.testpackage;

import com.mycompany.naturalnumbersinterpretation.phonenumber.PhoneNumber;
import com.mycompany.naturalnumbersinterpretation.phonenumber.PhoneNumberMethods;
import java.util.Arrays;
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
public class PhoneNumberTest {

    private final PhoneNumber phn1 = new PhoneNumber("694 68 22 514");
    private final PhoneNumber phn2 = new PhoneNumber("700 20 5");
    private final PhoneNumber phn3 = new PhoneNumber("700 0 0");
    private final PhoneNumber phn4 = new PhoneNumber("700 1");
    private final PhoneNumber phn5 = new PhoneNumber("30 90 6");
    private final PhoneNumber phn6 = new PhoneNumber("0 0 30 26 800");

    public PhoneNumberTest() {
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
    public void shrinkNumberTest() {
        assertEquals(Arrays.asList("694", "68", "22", "514"), PhoneNumberMethods.shrinkNumber(phn1.getSplitted()));
        assertEquals(Arrays.asList("725"), PhoneNumberMethods.shrinkNumber(phn2.getSplitted()));
        assertEquals(Arrays.asList("700", "0", "0"), PhoneNumberMethods.shrinkNumber(phn3.getSplitted()));
        assertEquals(Arrays.asList("701"), PhoneNumberMethods.shrinkNumber(phn4.getSplitted()));
        assertEquals(Arrays.asList("30", "96"), PhoneNumberMethods.shrinkNumber(phn5.getSplitted()));
        assertEquals(Arrays.asList("0", "0", "30", "26", "800"), PhoneNumberMethods.shrinkNumber(phn6.getSplitted()));
    }

    @Test
    public void extendNumberTest() {
        assertEquals(Arrays.asList(Arrays.asList("694","60094","6904","600904"), Arrays.asList("68", "608"),Arrays.asList("22", "202"),Arrays.asList("514", "50014","5104","500104")), PhoneNumberMethods.extendNumber(PhoneNumberMethods.shrinkNumber(phn1.getSplitted())));
        assertEquals(Arrays.asList(Arrays.asList("725", "70025","7205","700205")), PhoneNumberMethods.extendNumber(PhoneNumberMethods.shrinkNumber(phn2.getSplitted())));
        assertEquals(Arrays.asList(Arrays.asList("700"), Arrays.asList("0"),Arrays.asList("0")), PhoneNumberMethods.extendNumber(PhoneNumberMethods.shrinkNumber(phn3.getSplitted())));
        assertEquals(Arrays.asList(Arrays.asList("701","7001")), PhoneNumberMethods.extendNumber(PhoneNumberMethods.shrinkNumber(phn4.getSplitted())));
        assertEquals(Arrays.asList(Arrays.asList("30"), Arrays.asList("96", "906")), PhoneNumberMethods.extendNumber(PhoneNumberMethods.shrinkNumber(phn5.getSplitted())));
        assertEquals(Arrays.asList(Arrays.asList("0"),Arrays.asList("0"),Arrays.asList("30"), Arrays.asList("26", "206"),Arrays.asList("800")), PhoneNumberMethods.extendNumber(PhoneNumberMethods.shrinkNumber(phn6.getSplitted())));
    }
}
