/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Test_Functions;

import Functions.Config;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hosam
 */
public class Test_Config {
    
    @Test
    public void TC1_FAIL()
    {
        String expected = "A00";
        String result = Config.get_AdminID();
        assertEquals("Incorrect Admin ID",expected, result);
    }
    
    @Test
    public void TC2_Correct()
    {
        String expected = "0000";
        String result = Config.get_AdminID();
        assertEquals(expected, result);
    }
}
