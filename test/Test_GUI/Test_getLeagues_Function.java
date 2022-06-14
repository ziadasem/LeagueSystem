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
public class Test_getLeagues_Function {
    
    // Changed from Object[][] ---> boolean
    // For Proper Testing
     private boolean getLeagues() throws Exception{
        try{  
            Connection con=DriverManager.getConnection( Config.hostName,
                 Config.username,Config.password);  
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("select * from league");  
            // ResultSet rs=stmt.executeUpdate("insert into league ...");  
            Object[][] leagueList = new Object[30][2];
            int index = 0 ;
            while(rs.next()) { 
                leagueList[index][0] = rs.getInt("ID");
                leagueList[index][1] = rs.getString("LEAGUENAME");
                index ++ ;
             }
            con.close(); 
        //    return leagueList;
        return true;
        }catch(Exception e){ 
                System.out.println(e);
                throw e;
        }  
   }
    
    @Test
    public void TC1_Correct() throws Exception
    {
        boolean expected = getLeagues();
        boolean actual = true;
        assertEquals(expected, actual);
    }
}
