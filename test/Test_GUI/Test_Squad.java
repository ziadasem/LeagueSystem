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
public class Test_Squad {
    
    // Changed from Object[][] ---> boolean
    // *** Assume That the currentTeamID is an integer that we pass ...
    // For Proper Testing
       private boolean getSquad(int currentTeamID) throws Exception{
        try{  
            Object squadList[][];
            Connection con=DriverManager.getConnection( Config.hostName,
            Config.username,Config.password);  
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("select * from player where TEAMID =" +currentTeamID);  
            squadList = new Object[50][3];
            int index = 0 ;
            while(rs.next()) { 
                squadList[index][0] = rs.getString("firstname") + " " + rs.getString("lastname");
                squadList[index][1] = rs.getString("position");
                index ++ ;
             }
            con.close(); 
        //    return squadList;
            return true;
        }catch(SQLException e){ 
                System.out.println(e);
        //        throw e;
            return false;
        }  
   }
       
        // Changed from void ---> boolean
        // *** Assume That the currentTeamID is an integer that is = 3 ...
       private boolean addNewPlayer(String firstname , String lastname, String position) throws Exception{
       try{  
            Connection con=DriverManager.getConnection( Config.hostName,
            Config.username,Config.password);  
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("SELECT MAX(ID) FROM player");  
            int id =  0;
            while(rs.next()) {
               id = rs.getInt(1) + 1 ;
            }
            int insertingResult =stmt.executeUpdate("insert into player values"
                    + "(" +id+ ",'" +firstname+ "','" +lastname+ "','" +position+ "'," + 3 + ")" );  
            con.close(); 
            return true;
        }catch(Exception e){ 
             System.out.println(e);
        //     throw e;
             return false;
        }  
   }
    
    @Test
    public void TC1_ValidTeamID_Correct() throws Exception
    {
        boolean expected = getSquad(3);
        boolean actual = true;
        assertEquals(expected, actual);
    }
    
    @Test
    public void TC2_ValidPlayer_Correct() throws Exception
    {
        boolean expected = addNewPlayer("Hosam", "Mohamed", "ST");
        boolean actual = true;
        assertEquals(expected, actual);
    }
    
    @Test
    public void TC3_ExistingPlayer_FAIL() throws Exception
    {
        boolean expected = addNewPlayer("Toni", "Kroos", "CM");
        boolean actual = false;
        assertEquals(expected, actual);
    }
}
