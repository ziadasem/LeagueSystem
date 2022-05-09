/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

/**
 *
 * @author hosam
 */
public class MainClass {
    public static void main(String[] args) {
       try{
       new GUI.Home().setVisible(true);
       }
       catch(Exception e){
           System.out.println("Error in main");
       }
    }
}
