/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package XML2JSON;

import java.util.ArrayList;

import Utilities.ReadFile;

/**
 *
 * @author Ahmed Nasr
 */
public class json {
    public ArrayList<String> Convert (String path)
    {
        ReadFile input = new ReadFile(path);
        ArrayList<String> file = new  ArrayList<String>();
        ArrayList<String> output = new  ArrayList<String>();
        int value1=0;
        int value2=0;
        for(int i=0 ; i<input.dataInFile.size();i++)
      {
        file.add(input.dataInFile.get(i));
      }
      //  System.out.println("\t"+"\t"+"ahmed");
      
         for(int i =0 ; i<file.size();i++)
        {
            output.add("{");
            if(file.get(i).contains("<users>"))
            {
                output.add("\t"+"\"users\""+": [");
                i++;
                 while(!(file.get(i).contains("</users>")))
                 {
                    if(file.get(i).contains("<user>")  )
                    {
                        output.add("\t"+"\t"+"\"user\""+": {");
                        i++;
                        while(!(file.get(i).contains("</user>")))
                        {
                            if(file.get(i).contains("<id>") && !(file.get(i-1).contains("follower")) )
                            {
                                for (char c : file.get(i).toCharArray())
                                    if(Character.isDigit(c))
                                      {
                                          value1=c-'0';
                                         output.add("\t"+"\t"+"\t"+"\"id\""+": \""+value1+"\",");
                                      }
                                i++;
                             }

                            if(file.get(i).contains("<name>") )
                            {
                              String[] part1 = file.get(i).split(">");
                              String[] part2 = part1[1].split("<");
                              output.add("\t"+"\t"+"\t"+"\"name\""+": \""+part2[0]+"\",");
                               i++;
                            }
                               
                            if(file.get(i).contains("<posts>"))
                            {
                              i++;
                              output.add("\t"+"\t"+"\t"+"\"posts\""+": [");
                              while(!(file.get(i).contains("</posts>")))
                                {
                                    if(file.get(i).contains("<post>") )
                                    {
                                        i++;
                                        output.add("\t"+"\t"+"\t"+"\t"+"\"post\""+": [");
                                        while(!(file.get(i).contains("</post>")))
                                        {
                                            if(file.get(i).contains("<body>") )
                                            {
                                                 if(!(file.get(i+1).contains("</body>")) )
                                                 {
                                                     output.add("\t"+"\t"+"\t"+"\t"+"\t"+"  body: "+"\""+file.get(i+1).trim()+"\"");
                                                     i++;
                                                 }
                                            }
                                            i++;
                                             if(file.get(i).contains("<topic>") )
                                            {
                                                 if(!(file.get(i+1).contains("</tobic>")) )
                                                 {
                                                     output.add("\t"+"\t"+"\t"+"\t"+"\t"+"  topic: "+"\""+file.get(i+1).trim()+"\"");
                                                   //  nodes[nodes_num].addtopic(file.get(i+1).trim());
                                                     i++;
                                                    // nodes[nodes_num].increment_topics();
                                                 }
                                            }
                                            i++;
                                        }
                                  output.add("\t"+"\t"+"\t"+"\t"+"\t"+" ],");
                                  i++;
                                    }
                                }
                                output.add("\t"+"\t"+"\t"+"\t"+" ],");
                               // i++;
                            }

                           


                            if(file.get(i).contains("<followers>") )
                            {
                                output.add("\t"+"\t"+"\t"+"\"followers\""+": [");
                               while(!(file.get(i).contains("</followers>")))
                               {
                                   if(file.get(i).contains("<id>") )
                                 {
                                    for (char c : file.get(i).toCharArray())
                                        if(Character.isDigit(c))
                                          {
                                              value2=c-'0';            
                                              output.add("\t"+"\t"+"\t"+"\t"+"{");
                                              output.add("\t"+"\t"+"\t"+"\t"+"\t"+"\"id\":"+"\""+value2+"\"");
                                              output.add("\t"+"\t"+"\t"+"\t"+"}");
                                          }
                                    i++;
                                 }
                                   i++;
                               }
                               output.add("\t"+"\t"+"\t"+"\t"+"     ]");
                            }


                            else 
                                  i++;
                    }
                        i++;
                        output.add("\t"+"\t"+"\t"+"}");
                }
                 }
                output.add("\t"+"\t"+"]");
            }
             output.add("}");

        }
      return output;
    
    }
}
