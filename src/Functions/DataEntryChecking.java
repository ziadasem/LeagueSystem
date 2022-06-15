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
    private final String positionConstraints[] = { "GK", "CB", "LB", "RB", "CM", "AM", "DM", "LW", "RW", "ST", "CF" };
    
    // private final String constraints_str = "?!@#";
    public boolean isValid_Team(String temp)
    {
        if(temp.isEmpty())
            return false;
        for(int i=0; i<constraints.length; i++)
        {
            if(temp.contains(Character.toString(constraints[i])))
                return false;
            if(temp.length()<4)
                return false;
        }
        return true;
    }
    public boolean containsNumbers(String inp){
      char[] chars = inp.toCharArray();
      for(char c : chars){
         if(Character.isDigit(c)){
             return true;
         }
        }
      return false;
    }
    
    public boolean isValid_Name(String temp)
    {
        if(temp.isEmpty())
            return false;
        for(int i=0; i<constraints.length; i++)
        {
            if(temp.contains(Character.toString(constraints[i])))
                return false;
            if(temp.length()<2)
                return false;
        }
        return true;
    }
    
    public boolean isValid_number(int temp)
    {
       
        if (temp <= 0){
            return false ;
        }
        return true;
    }
    
    public boolean isValid_firstName(String temp)
    {
        if(temp.isEmpty())
            return false;
        temp = temp.strip();
        for(int i=0; i<constraints.length; i++)
        {
            if(temp.contains(Character.toString(constraints[i])))
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
        if(temp.isEmpty()){
            return false;
        }
        if (!temp.contains(":")){
            return false;
        }
        String[] time = temp.split(":");
        if (time[0].length() !=  2 || time[1].length() !=  2 ){
            return false ;
        }
        
        if ( Integer.parseInt(time[0]) >  12 || Integer.parseInt(time[0]) <  0 
             || Integer.parseInt(time[1]) >  59  || Integer.parseInt(time[1]) <  0 ){
            return false ;
        }
        
        return true;
    }
    
    public boolean isValid_Position(String temp)
    {
        if(temp.isEmpty())
           return false;
        if(temp.length()>2)
            return false;
        for(int i=0; i<positionConstraints.length; i++)
        {
            if(temp.contains(positionConstraints[i]))
                return true;
        }
        return false;
    }
}

