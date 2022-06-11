/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Functions;
//*****************************************************************//
    //******************** NOT USED YET ********************//
//*****************************************************************//
/**
 *
 * @author hosam
 */
public class SharedData {
    
        //******************** Declaring Variables that to be shared ********************//
            // Variable for holding the Current League Name ...
    private String currentLeague_Name = "Default";
        //*****************************************************************//
    public void setLeague_Name(String currentLeague_Name)
    {
        this.currentLeague_Name = currentLeague_Name;
    }
    public String getLeague_Name()
    {
        return this.currentLeague_Name;
    }
}
