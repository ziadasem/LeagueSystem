/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Functions;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

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
    
    
    public boolean isValid_Week(String temp)
    {
        if(temp.isEmpty())
            return false;
        if(temp.length()>2)
            return false;
        if(temp.startsWith("0"))
            return false;
        try{
            Integer test = Integer.parseInt(temp);
        }
        catch(NumberFormatException nfe)
            {return false;}
        return true;
    }
    
    public boolean isValid_Time(String temp)
    {
        if(temp.isEmpty())
            return false;
        try{
            LocalTime.parse(temp);
        }
        catch(DateTimeParseException | NullPointerException e)
        {
            return false;
        }
        return true;
    }
}

