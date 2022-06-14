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
public class Test_DataEntryChecking_Numbers {

    DataEntryChecking obj_t = new DataEntryChecking();
    
    @Test
    public void TC1_ContainsNumbers_Correct()
    {
        String expected = "123214";
        boolean actual = obj_t.containsNumbers(expected);
        assertTrue(actual);
    }
    
    @Test
    public void TC2_ContainsNumbers_Correct()
    {
        String expected = "12H0sam2";
        boolean actual = obj_t.containsNumbers(expected);
        assertTrue(actual);
    }
    
    @Test
    public void TC3_ContainsNumbers_Blank_FAIL()
    {
        String expected = " ";
        boolean actual = obj_t.containsNumbers(expected);
        assertFalse(actual);
    }
    
    @Test
    public void TC4_ContainsNumbers_NoNumbers_FAIL()
    {
        String expected = "asdasdSD";
        boolean actual = obj_t.containsNumbers(expected);
        assertFalse(actual);
    }
    
    @Test
    public void TC5_ContainsNumbers_Length_FAIL()
    {
        int expected = 0;
        boolean actual = obj_t.isValid_number(expected);
        assertFalse(actual);
    }
    
    @Test
    public void TC6_ContainsNumbers_Correct()
    {
        int expected = 434;
        boolean actual = obj_t.isValid_number(expected);
        assertTrue(actual);
    }
}
