/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.util.ArrayList;

import Utilities.ReadFile;

/**
 *
 * @author Ahmed Nasr
 */
public class Examble {
     public static void main (String args[])
    {
      ReadFile input = new ReadFile("/Users/abdullahtamer/Desktop/demo/src/sample.xml");
      ArrayList<String> file = new ArrayList<String>()  ;
       ArrayList<String> output = new ArrayList<String>() ;
        for(int i=0 ; i<input.dataInFile.size();i++)
      {
        file.add(input.dataInFile.get(i));
      }

        for(int i =0 ; i<file.size() ; i++)
        {
            output.add(file.get(i).trim());
        
        }
           for(int i=0 ; i<output.size();i++)
      {
        System.out.print(output.get(i));
      }
         
    }
}
