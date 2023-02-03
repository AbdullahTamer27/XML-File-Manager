/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

 import java.util.ArrayList;

        
 /**
  *
  * @author Ahmed Nasr
  */
 
 
 class node 
 {
  private int id ;
  private String name;
  private ArrayList<String> posts = new ArrayList<String>() ;
  private ArrayList<String> topics = new ArrayList<String>() ;
  private ArrayList<Integer> follwers = new ArrayList<Integer>() ;
  private int follwers_num;
  private int posts_num;
  private int topics_num;
  
  node(){}
  
  node(int i)
  {
   this.id =i;
   this.posts_num=0;
   this.follwers_num=0;
  }
  
  public void addid(int i)
  {
   this.id = i;
  }
  public void addname(String n)
  {
   this.name = n;
  }
  public void increment_followers()
  {
      this.follwers_num++;
  }
  public void increment_posts()
  {
      this.posts_num++;
  }
  public void increment_topics()
  {
      this.topics_num++;
  }
  
  void addposts(String p)
  {
     this.posts.add(p) ;
  }
  void addtopic(String t)
  {
     this.topics.add(t) ;
  }
   void addfollowers(int f)
  {
      
     this.follwers.add(f) ;
  }
   int get_id()
   {
       return this.id;
   }
   String get_name()
   {
       return this.name;
   }
   ArrayList<Integer> get_followers()
   {
       return this.follwers;
   }
   ArrayList<String> get_posts()
   {
       return this.posts;
   }
   ArrayList<String> get_topics()
   {
       return this.topics;
   }
   int get_number_of_follwers()
   {
       return this.follwers_num;
   }
    int get_number_of_posts()
   {
       return this.posts_num;
   }
    int get_number_of_topics()
   {
       return this.topics_num;
   }
 }
 
 
 
 
 
 
 //------------------------------------------------------------------------------
 
 
 
 
 
 class Graphh
 {    int nodes_num ;
     node[] nodes = new node[50] ;
     ArrayList<ArrayList<Integer>> graph;
     //int count;
     Graphh(ArrayList<String>file)
     {
         nodes_num=-1;
           for(int i =0 ; i<file.size();i++)
         {
             
            if(file.get(i).contains("<user>")  )
            {
                nodes_num++;
                i++;
                while(!(file.get(i).contains("</user>")))
                {
                    if(file.get(i).contains("<id>") && !(file.get(i-1).contains("follower")) )
                    {
                         
                       for (char c : file.get(i).toCharArray())
                           if(Character.isDigit(c))
                             {
                                this.nodes[nodes_num]  = new node(c-'0');
                                  
                             }
                       i++;
                    }
                    
                    if(file.get(i).contains("<name>") )
                    {
                      String[] part1 = file.get(i).split(">");
                      String[] part2 = part1[1].split("<");
                      nodes[nodes_num].addname(part2[0]);
                      
                       i++;
                    }
                    
                    
                    if(file.get(i).contains("<body>") )
                    {
                         if(!(file.get(i+1).contains("</body>")) )
                         {
                             nodes[nodes_num].addposts(file.get(i+1).trim());
                             i++;
                             nodes[nodes_num].increment_posts();
                         }
                    }
                    
                    if(file.get(i).contains("<topic>") )
                    {
                         if(!(file.get(i+1).contains("</tobic>")) )
                         {
                             nodes[nodes_num].addtopic(file.get(i+1).trim());
                             i++;
                             nodes[nodes_num].increment_topics();
                         }
                    }
                    
                    
                    if(file.get(i).contains("<followers>") )
                    {
                       while(!(file.get(i).contains("</followers>")))
                       {
                           if(file.get(i).contains("<id>") )
                         {
                            for (char c : file.get(i).toCharArray())
                                if(Character.isDigit(c))
                                  {
                                     nodes[nodes_num].addfollowers(c-'0');
                                     nodes[nodes_num].increment_followers();
                                  }
                            i++;
                         }
                           i++;
                       }
                    }
                    
                    
                    else 
                          i++;
                }
            }
 
         }
           
          graph = new ArrayList<ArrayList<Integer>>() ;
           for(int i=0 ; i<=nodes_num ; i++)
           {
             graph.add( new ArrayList<Integer>());
           }
            for(int j=0; j<=nodes_num;j++)
             {
                 for(int x=0; x<nodes[j].get_number_of_follwers();x++)
                 {
                     graph.get(j).add(nodes[j].get_followers().get(x));
                 }
             }
     } 
     
     
       ArrayList<String> print_users_data ()
     {
         ArrayList<String> output = new ArrayList<String>() ;
       for(int i =0 ; i<=nodes_num ; i++)
       {
         output.add("user id --> "+nodes[i].get_id());
         output.add("user name --> "+nodes[i].get_name());
         output.add("the shared posts are --> "+nodes[i].get_posts());
         output.add("the topics of posts are --> "+nodes[i].get_topics());
         output.add("the followers are --> "+nodes[i].get_followers());
         output.add("number of user folowers is --> "+nodes[i].get_number_of_follwers());
         output.add("number of user posts is --> "+nodes[i].get_number_of_posts());
         output.add("");
         output.add("-------------------------------------------------");
         output.add("");
         
       }
       return output;
     }
     
      ArrayList< ArrayList<String>> printgraph()
     {
        ArrayList<ArrayList<String>> output = new ArrayList<ArrayList<String>>() ;
        for(int i=0 ; i<=nodes_num ; i++)
        {
            output.add(new ArrayList<String>());
            output.get(i).add("user " +nodes[i].get_id()+ ":");
            for(int x : graph.get(i))
            {
                output.get(i).add(" -> " +x);
            }
        } 
        return output;
     }
     
      String sendto ()
      {
           String output2 ="" ;
          for(int i =0 ; i<=nodes_num ; i++)
          {
              for(int j =0 ; j<nodes[i].get_number_of_follwers();j++)
              {
                  output2+=nodes[i].get_id();
                  output2+="->";
                  output2+=nodes[i].get_followers().get(j);
                  output2+=";";
                  
              
              }
          }
             return output2;
      }
     
     String Most_Influencer()
     {
         String output;
        int max = nodes[0].get_number_of_follwers();
        int user=0;
      for(int i=0 ; i<=nodes_num ; i++)
        {
            if(nodes[i].get_number_of_follwers()>max)
                max = nodes[i].get_number_of_follwers();
        }
       for(int j=0; j<=nodes_num ; j++)
       {
        if(max ==nodes[j].get_number_of_follwers())
         user=nodes[j].get_id();
       }
      output = "The most_influencer --> user"+user;
      return output;
     }
     
     
        ArrayList<String> Matual_followers()
     {
          ArrayList<String> output = new ArrayList<String>() ;
          ArrayList<Integer> matualusers=new ArrayList<Integer>();
         boolean state = false;
         for(int i=0 ; i<=nodes_num ; i++)
         {
           for(int j=0; j<=nodes_num ; j++)
           {
                for(int x=0; x<nodes[i].get_number_of_follwers() ;x++)
                {
                    for(int y=0; y<nodes[j].get_number_of_follwers() ;y++)
                    {
                     if(nodes[i].get_followers().get(x)==nodes[j].get_followers().get(y) && i!=j )
                     {
                          state = true;
                         for(int z=0 ; z<matualusers.size();z++)
                         {
                          if(nodes[i].get_followers().get(x)== matualusers.get(z))
                          {
                               state = false;
                          }
                         }
                         if(state)
                         {
                             output.add("user " +nodes[i].get_followers().get(x)+" is matual follwer between user"+nodes[i].get_id()+" and user"+nodes[j].get_id());
                             matualusers.add(nodes[i].get_followers().get(x));
                         } 
                     }
                    }
                } 
           }
         }
       
       return output;
         
     }
     
     
     
     String Most_Active()
     {
         String output;
         ArrayList<ArrayList<Integer>> Activefactor = new ArrayList<ArrayList<Integer>>();
         int most_active;
         int most_active_id;
          for(int i=0 ; i<=nodes_num ; i++)
           {
             Activefactor.add( new ArrayList<Integer>()); 
           }
          for(int j =0 ; j<=nodes_num ; j++)
          {
              for(int x=0 ;x<nodes[j].get_number_of_follwers();x++)
              {
                  for(int y=0 ;y<=nodes_num ;y++)
                  {
                      if(nodes[y].get_id()== nodes[j].get_followers().get(x))
                      {
                     
                          Activefactor.get(y).add(nodes[y].get_id());
                      }
                  }
              }
             
          }
         
          most_active=Activefactor.get(0).size();
          most_active_id=Activefactor.get(0).get(0);
          for(int z=0 ; z<Activefactor.size();z++)
          { 
             if(Activefactor.get(z).size()>most_active)
             { 
                 most_active=Activefactor.get(z).size();
                 most_active_id=Activefactor.get(z).get(0);
             }
          } 
            output = "the most active user is user "+most_active_id+" he follow "+most_active+" users";
          return output;
     }
     
     
     
     ArrayList<ArrayList<String>> Suggested_followers()
     {
         ArrayList<ArrayList<Integer>> suggested_list = new ArrayList<ArrayList<Integer>>();
          ArrayList<ArrayList<String>> output = new ArrayList<ArrayList<String>>();
         boolean state = false;
          for(int i=0 ; i<=nodes_num ; i++)
           {
             suggested_list.add( new ArrayList<Integer>()); 
           }
         
          for(int j =0 ; j<=nodes_num ; j++)
          {
              for(int x=0 ;x<nodes[j].get_number_of_follwers();x++)
              {
                  for(int y=0 ;y<nodes[j].get_number_of_follwers() ;y++)
                  {
                     if(nodes[j].get_followers().get(y)!=nodes[j].get_followers().get(x))
                     {
 
                         state = true;
                         for(int z=0; z<=nodes_num ;z++)
                         {
                             if(nodes[z].get_id() == nodes[j].get_followers().get(y))
                             {
                               for(int m =0 ; m<nodes[z].get_number_of_follwers();m++)
                               {
                                   if(nodes[z].get_followers().get(m)== nodes[j].get_followers().get(x))
                                   {
                                     state = false;
                                   }
                                   if(state)
                                     suggested_list.get(nodes[z].get_id()-1).add(nodes[j].get_followers().get(x));
                               }
                             }
                         }
                     }
                  }
              }
         }
         
         
         for(int i=0 ; i<=nodes_num ; i++)
        {
            output.add(new ArrayList<String>());
            output.get(i).add("user " +nodes[i].get_id()+ ":");
            
            if(suggested_list.get(i).isEmpty())
                 output.get(i).add(" have no suggested users ");
            
            for(int x : suggested_list.get(i))
                 output.get(i).add(" -> " +x);
        } 
         return output;
     }
     
     
      ArrayList<String>  search (String tofind)
     {
         ArrayList<String> output = new ArrayList<String>() ;
         boolean state = true;
         for(int i =0 ; i<=nodes_num ; i++)
         {
             for(int x =0 ; x<nodes[i].get_number_of_topics() ; x++)
             {
                 if(nodes[i].get_topics().get(x).equals(tofind))
                 {
                     output.add("this topic was posted by user "+nodes[i].get_id());
                     state=false;
                 }
             
             }
             for(int y =0 ; y<nodes[i].get_number_of_posts() ; y++)
             {
                 if(nodes[i].get_posts().get(y).contains(tofind))
                 {
                     output.add("this woed was mentioned by user "+nodes[i].get_id()+" in his post ");
                     state=false;
                 }
             }
         }
         if(state)
             output.add("sory this word is not used by the users ");
      return output;
     }
     
     
  }
 
 
 
 //------------------------------------------------------------------------------
 
 
 
 
 
 public class Graph {
     
 }
 