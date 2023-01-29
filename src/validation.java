
import java.util.Stack;


import java.lang.String;

public class validation {
    public String stripEnds(String t) {
        if (t.length() <= 2) {
            return null;	// this is a degenerate tag
        }
        return t.substring(1, t.length() - 1);
    }

    public boolean isOpeningTag(String tag) {
        return (tag.charAt(1) != '/');
    }

    public boolean areMatchingTags(String tag1, String tag2) {
        tag1 = tag1.substring(1);
        return tag1.equals(tag2.substring(2));
    }


    public String isXMLTagMatched(Stack<String> S, String[] tag) {
  
        for (int i = 0; i < tag.length; i++) {
    
            String line = tag[i];
            if(!line.contains("<" )){
                if(!line.contains(">")){
                    continue;
                }
                else{
                    return "unBalanced4";
                }
            }
            else if(!line.contains(">" )){
                if(!line.contains("<")){
                    continue;
                }
                else{
                    return "unBalanced3";
                }
            }
            if(line.lastIndexOf("<") != 0){
                String opentag = line.substring(0, line.indexOf(">")) + ">";
                String closetag = line.substring(line.lastIndexOf("<", line.lastIndexOf(">")));
                if(closetag.charAt(1)!='/'){
                    return "unBalanced";
                }
                else{
                    String tagName1= opentag.substring(0, opentag.length());
                    String tagName2 = closetag.substring(0, closetag.length());
                    S.push(tagName1);

                    if(areMatchingTags(S.peek(), tagName2)){
                        S.pop();
                    }
                    else{
                        return "unbalanced";
                    }
 
                }
                
                



            }
            else{



            String tagName1= line.substring(1,line.indexOf(">"));


            if(line.startsWith("<")){

                if(line.contains(">") && line.indexOf(">")!=line.length()-1){

                    int index1= line.indexOf(">");
                    if(isOpeningTag(line.substring(0, index1))){                        
                        S.push(tagName1);

                    }
                }
                else{

                    if(isOpeningTag(tag[i])){                        
                        S.push(tagName1);
                    }
                    else {
                        if(S.empty()){
                            return "unBalanced1";
                        }
                        else{
                            if(areMatchingTags(S.peek(), tagName1)){
                                S.pop();
                            }
                        }
                    }

                }


            }
            if(line.contains("<") && line.indexOf("<")!=0){
                int index2 = line.indexOf("<");
                String closeTag = line.substring(index2, line.length()-1);
                if(closeTag.charAt(1)=='/') {
                    String tagName2 = closeTag.substring(index2+2, closeTag.length()-2);
                    if(areMatchingTags(tagName1, tagName2)){
                        S.pop();
                    }
                } 
            }

        }
    }


        if (S.isEmpty()) 
        {
            return "Balanced XML Tags"; // we matched everything
        }
        else {
            return "Unbalanced2";
        }
    }
        

}


