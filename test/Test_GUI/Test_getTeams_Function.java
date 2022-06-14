/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Test_GUI;

import Functions.Config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hosam
 */
public class Test_getTeams_Function {
    
    // Changed from Object[][] ---> boolean
    // *** Assume That the current LeagueID is an integer that we pass ...
    // For Proper Testing
    private boolean getTeams(int currentLeagueID) throws Exception{
        try{  
            Connection con=DriverManager.getConnection( Config.hostName,
            Config.username,Config.password);  
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("select * from team where leagueid =" +currentLeagueID);  
            Object[][] teamsList = new Object[30][3];
            int index = 0 ;
            while(rs.next()) { 
                teamsList[index][0] = rs.getString("name");
                teamsList[index][1] = rs.getInt("foundedyear");
                index ++ ;
             }
            con.close(); 
        //    return teamsList;
            return true;
        }catch(SQLException e){ 
                System.out.println(e);
            //    throw e;
                return false;
        }  
   }
    
    @Test
    public void TC1_ValidLeagueID_Correct() throws Exception
    {
        boolean expected = getTeams(3);
        boolean actual = true;
        assertEquals(expected, actual);
    }
}
