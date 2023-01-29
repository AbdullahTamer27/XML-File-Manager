

import static java.lang.Character.isWhitespace;

/**
 *
 * @author Ahmed Nasr
 */
public class Minify {

    public String minifiying(String path) {
        ReadFile input = new ReadFile(path);
        String orgin = input.intoString();
        String output = ""  ;
        char[] cmp1= new char[50];
        char[] cmp2= new char[50];
        char[] data= new char[2000];
        int c1=0,c2=0,d=0;
        int i =0;
        boolean check;
        int ptime1 =0;
        int ptime2 =1;
        int ptime3 =0;
        int ptime4 =1;
        while(i < orgin.length())
        {
            check=false;
         if(orgin.charAt(i)=='<')
         {

          if(orgin.charAt(i+1)!='/')
          {
              c1=0;
              while(orgin.charAt(i+1)!='>')
                   {
                    i++;
                 //   s--;
                    cmp1[c1]=orgin.charAt(i);
                    c1++;
                   }
               i=i+2;
             //  s=s-2;
               ptime1++;
           }


           else if(orgin.charAt(i+1)=='/')
          {
              c2=0;
              while(orgin.charAt(i+1)!='>')
                   {
                    i++;
                    cmp2[c2]=orgin.charAt(i);
                    c2++;
                   }
               ptime3++;
               i=i+2;
               check = true;
           }
           if(orgin.charAt(i)!='<'&& check == false)
           {
               d=0;
          while (isWhitespace(orgin.charAt(i)))
              i++;
          while(orgin.charAt(i)!='<')
               {
                 if(isWhitespace(orgin.charAt(i))&&isWhitespace(orgin.charAt(i+1))&&isWhitespace(orgin.charAt(i+2))&&isWhitespace(orgin.charAt(i+3))&&isWhitespace(orgin.charAt(i+4))&&isWhitespace(orgin.charAt(i+5))) 
                  break;   
                 data[d]=orgin.charAt(i);
                 i++;
                 d++;
               }
           }
           if(c1!=0 && ptime1==ptime2)
           {
               output+='<';

                for(int cc1 =0;cc1<c1;cc1++)
                 {
                     output+=cmp1[cc1];

                 }
                 output+='>';

                ptime2++;
           }
           if(check==true && c1==c2-1)
           {
                for(int dd =0;dd<d;dd++)
             {
                  output+=data[dd];

             }

           }
           if(c2!=0 && ptime3==ptime4)
           {
                output+='<';

                for(int cc2 =0;cc2<c2;cc2++)
                 {
                      output+=cmp2[cc2];

                 }
                 output+='>';

                ptime4++;

           }


      }

      else
      {
       i++;
      }
        }
        return output;
    }
    
}
