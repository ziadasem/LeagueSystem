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
public class Test_DataEntryChecking_Position {

    DataEntryChecking obj_t = new DataEntryChecking();
    
    @Test
    public void TC1_Blank_FAIL()
    {
        String expected = " ";
        boolean actual = obj_t.isValid_Position(expected);
        assertFalse(actual);
    }
    
    @Test
    public void TC2_Constraints_FAIL()
    {
        String expected = "wef?we";
        boolean actual = obj_t.isValid_Position(expected);
        assertFalse(actual);
    }
    
    @Test
    public void TC3_String_FAIL()
    {
        String expected = "This Is Position";
        boolean actual = obj_t.isValid_Position(expected);
        assertFalse(actual);
    }
    
    @Test
    public void TC4_Word_FAIL()
    {
        String expected = "Position";
        boolean actual = obj_t.isValid_Position(expected);
        assertFalse(actual);
    }
    
    @Test
    public void TC5_IncorrectPosConstraint_FAIL()
    {
        String expected = "LM";
        boolean actual = obj_t.isValid_Position(expected);
        assertFalse(actual);
    }
    
    @Test
    public void TC6_Length_FAIL()
    {
        String expected = "H";
        boolean actual = obj_t.isValid_Position(expected);
        assertFalse(actual);
    }
    
    @Test
    public void TC7_Constraint0_Correct()
    {
        String expected = "GK";
        boolean actual = obj_t.isValid_Position(expected);
        assertTrue(actual);
    }
    
    @Test
    public void TC8_Constraint1_Correct()
    {
        String expected = "CB";
        boolean actual = obj_t.isValid_Position(expected);
        assertTrue(actual);
    }
    
    @Test
    public void TC9_Constraint2_Correct()
    {
        String expected = "LB";
        boolean actual = obj_t.isValid_Position(expected);
        assertTrue(actual);
    }
    
    @Test
    public void TC10_Constraint3_Correct()
    {
        String expected = "RB";
        boolean actual = obj_t.isValid_Position(expected);
        assertTrue(actual);
    }
    
    @Test
    public void TC11_Constraint4_Correct()
    {
        String expected = "CM";
        boolean actual = obj_t.isValid_Position(expected);
        assertTrue(actual);
    }
    
    @Test
    public void TC12_Constraint5_Correct()
    {
        String expected = "AM";
        boolean actual = obj_t.isValid_Position(expected);
        assertTrue(actual);
    }
    
    @Test
    public void TC13_Constraint6_Correct()
    {
        String expected = "DM";
        boolean actual = obj_t.isValid_Position(expected);
        assertTrue(actual);
    }
    
    @Test
    public void TC14_Constraint7_Correct()
    {
        String expected = "LW";
        boolean actual = obj_t.isValid_Position(expected);
        assertTrue(actual);
    }
    
    @Test
    public void TC15_Constraint8_Correct()
    {
        String expected = "RW";
        boolean actual = obj_t.isValid_Position(expected);
        assertTrue(actual);
    }
    
    @Test
    public void TC16_Constraint9_Correct()
    {
        String expected = "ST";
        boolean actual = obj_t.isValid_Position(expected);
        assertTrue(actual);
    }
    
    @Test
    public void TC17_Constraint10_Correct()
    {
        String expected = "CF";
        boolean actual = obj_t.isValid_Position(expected);
        assertTrue(actual);
    }
    
    @Test
    public void TC18_ConstraintCapitalization_Correct()
    {
        String expected = "gk";
        boolean actual = obj_t.isValid_Position(expected);
        assertFalse(actual);
    }
}
