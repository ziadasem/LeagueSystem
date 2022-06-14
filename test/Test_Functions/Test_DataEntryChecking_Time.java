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
public class Test_DataEntryChecking_Time {

    DataEntryChecking obj_t = new DataEntryChecking();
    
    @Test
    public void TC1_InvalidSyntax_FAIL()
    {
        String expected = "1234";
        boolean actual = obj_t.isValid_Time(expected);
        assertFalse(actual);
    }
    
    @Test
    public void TC2_Zero_FAIL()
    {
        String expected = "0";
        boolean actual = obj_t.isValid_Time(expected);
        assertFalse(actual);
    }
    
    @Test
    public void TC3_Blank_FAIL()
    {
        String expected = " ";
        boolean actual = obj_t.isValid_Time(expected);
        assertFalse(actual);
    }
    
    @Test
    public void TC4_Length_FAIL()
    {
        String expected = "24:23";
        boolean actual = obj_t.isValid_Time(expected);
        assertFalse(actual);
    }
    
    @Test
    public void TC5_NotNumbers_FAIL()
    {
        String expected = "Week1";
        boolean actual = obj_t.isValid_Time(expected);
        assertFalse(actual);
    }
    
    @Test
    public void TC6_Correct()
    {
        String expected = "12:00";
        boolean actual = obj_t.isValid_Time(expected);
        assertTrue(actual);
    }
    
    @Test
    public void TC7_Correct()
    {
        String expected = "04:59";
        boolean actual = obj_t.isValid_Time(expected);
        assertTrue(actual);
    }
    
    @Test
    public void TC8_Correct()
    {
        String expected = "23:30";
        boolean actual = obj_t.isValid_Time(expected);
        assertTrue(actual);
    }
    
}
