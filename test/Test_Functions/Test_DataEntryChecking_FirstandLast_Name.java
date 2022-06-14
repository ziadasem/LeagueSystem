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
public class Test_DataEntryChecking_FirstandLast_Name {

    DataEntryChecking obj_t = new DataEntryChecking();
    
    @Test
    public void TC1_Blank_FAIL()
    {
        String expected = " ";
        boolean actual = obj_t.isValid_firstName(expected);
        assertFalse(actual);
    }
    
    @Test
    public void TC2_Constraint0_FAIL()
    {
        String expected = "wef?we";
        boolean actual = obj_t.isValid_firstName(expected);
        assertFalse(actual);
    }
    
    @Test
    public void TC3_Constraint1_FAIL()
    {
        String expected = "we!we";
        boolean actual = obj_t.isValid_firstName(expected);
        assertFalse(actual);
    }
    
    @Test
    public void TC4_Constraint2_FAIL()
    {
        String expected = "we@e";
        boolean actual = obj_t.isValid_firstName(expected);
        assertFalse(actual);
    }
    
    @Test
    public void TC5_Constraint3_FAIL()
    {
        String expected = "w#rwe";
        boolean actual = obj_t.isValid_Name(expected);
        assertFalse(actual);
    }
    
    @Test
    public void TC6_Length_FAIL()
    {
        String expected = "H";
        boolean actual = obj_t.isValid_firstName(expected);
        assertFalse(actual);
    }
    
    @Test
    public void TC7_Correct()
    {
        String expected = "Hosam Mohamed";
        boolean actual = obj_t.isValid_firstName(expected);
        assertTrue(actual);
    }
    
    @Test
    public void TC8_Correct()
    {
        String expected = "Ramy Mohamed Ahmed";
        boolean actual = obj_t.isValid_firstName(expected);
        assertTrue(actual);
    }
    
    @Test
    public void TC9_Correct()
    {
        String expected = "Zeyad";
        boolean actual = obj_t.isValid_firstName(expected);
        assertTrue(actual);
    }
}
