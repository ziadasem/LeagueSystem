/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Test_Functions;

import Functions.DataEntryChecking;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hosam
 */
public class Test_DataEntryChecking_Week {

    DataEntryChecking obj_t = new DataEntryChecking();
    
    @Test
    public void TC1_Length_FAIL()
    {
        String expected = "1234";
        boolean actual = obj_t.isValid_Week(expected);
        assertFalse(actual);
    }
    
    @Test
    public void TC2_Zero_FAIL()
    {
        String expected = "0";
        boolean actual = obj_t.isValid_Week(expected);
        assertFalse(actual);
    }
    
    @Test
    public void TC3_Blank_FAIL()
    {
        String expected = " ";
        boolean actual = obj_t.isValid_Week(expected);
        assertFalse(actual);
    }
    
    @Test
    public void TC4_StartsWithZero_FAIL()
    {
        String expected = "02134";
        boolean actual = obj_t.isValid_Week(expected);
        assertFalse(actual);
    }
    
    @Test
    public void TC5_NotNumbers_FAIL()
    {
        String expected = "Week1";
        boolean actual = obj_t.isValid_Week(expected);
        assertFalse(actual);
    }
    
    @Test
    public void TC6_Correct()
    {
        String expected = "2";
        boolean actual = obj_t.isValid_Week(expected);
        assertTrue(actual);
    }
    
    @Test
    public void TC7_Correct()
    {
        String expected = "12";
        boolean actual = obj_t.isValid_Week(expected);
        assertTrue(actual);
    }
    
}
