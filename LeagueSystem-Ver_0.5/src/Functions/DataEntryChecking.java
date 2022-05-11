/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Functions;

/**
 *
 * @author hosam
 */
public class DataEntryChecking {
    private final char constraints[] = {'?', '!', '@', '#'};
    // private final String constraints_str = "?!@#";
    public boolean isValid_Team(String temp)
    {
        if(temp.isEmpty())
            return false;
        for(int i=0; i<constraints.length; i++)
        {
            if(temp.startsWith(Character.toString(constraints[i])))
                return false;
            if(temp.endsWith(Character.toString(constraints[i])))
                return false;
            if(temp.length()<4)
                return false;
        }
        return true;
    }

    public boolean isValid_Name(String temp)
    {
        if(temp.isEmpty())
            return false;
        for(int i=0; i<constraints.length; i++)
        {
            if(temp.startsWith(Character.toString(constraints[i])))
                return false;
            if(temp.endsWith(Character.toString(constraints[i])))
                return false;
            if(temp.length()<2)
                return false;
        }
        return true;
    }
}
