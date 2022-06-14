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
public class Test_DataEntryChecking_IsValid_Team {

    DataEntryChecking obj_t = new DataEntryChecking();
    
    @Test
    public void FAIL_IsValid_Team_TC1_Blank()
    {
        String expected = " ";
        boolean actual = obj_t.isValid_Team(expected);
        assertFalse(actual);
    }
    
    @Test
    public void FAIL_IsValid_Team_TC2_constraint0()
    {
        String expected = "wef?we";
        boolean actual = obj_t.isValid_Team(expected);
        assertFalse(actual);
    }
    
    @Test
    public void FAIL_IsValid_Team_TC3_constraint1()
    {
        String expected = "we!we";
        boolean actual = obj_t.isValid_Team(expected);
        assertFalse(actual);
    }
    
    @Test
    public void FAIL_IsValid_Team_TC4_constraint2()
    {
        String expected = "we@e";
        boolean actual = obj_t.isValid_Team(expected);
        assertFalse(actual);
    }
    
    @Test
    public void FAIL_IsValid_Team_TC5_constraint2()
    {
        String expected = "w#rwe";
        boolean actual = obj_t.isValid_Team(expected);
        assertFalse(actual);
    }
    
    @Test
    public void FAIL_IsValid_Team_TC6_Length()
    {
        String expected = "Hat";
        boolean actual = obj_t.isValid_Team(expected);
        assertFalse(actual);
    }
    
    @Test
    public void IsValid_Team_TC7_Correct()
    {
        String expected = "Manchester United";
        boolean actual = obj_t.isValid_Team(expected);
        assertTrue(actual);
    }
    
    @Test
    public void IsValid_Team_TC8_Correct()
    {
        String expected = "Al Ahly";
        boolean actual = obj_t.isValid_Team(expected);
        assertTrue(actual);
    }
}
