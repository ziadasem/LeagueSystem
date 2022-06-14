/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Test_GUI;

import Functions.Config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hosam
 */
public class Test_AddNewLeague_Function {
    
     // Changed from Object[][] ---> boolean
     private boolean addNewLeague(String name) throws Exception{
         try{
            Connection con=DriverManager.getConnection( Config.hostName,
                 Config.username,Config.password);  
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("SELECT MAX(ID) FROM league");  
            int id =  0;
            while(rs.next()) {
               id = rs.getInt(1) + 1 ;
            }
            System.out.println(id);
            int insertingResult =stmt.executeUpdate("insert into league values(" +id+ ",'" +name+ "')"  );  
            con.close(); 
            return true;
        }catch(Exception e){ 
             System.out.println(e);
             return false;
        }  
    }
    
    @Test
    public void TC1_Characters_Correct() throws Exception
    {
        boolean expected = addNewLeague("Prm2 League");
        boolean actual = true;
        assertEquals(expected, actual);
    }
    
    @Test
    public void TC2_existingLeague_FAIL() throws Exception
    {
        boolean expected = addNewLeague("Premiere League");
        boolean actual = false;
        assertEquals(expected, actual);
    }
}
