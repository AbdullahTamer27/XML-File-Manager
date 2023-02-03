package Validation;

import java.util.ArrayList;
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
        if(tag.length() == 0){
            return false;
        }
        return (tag.charAt(1) != '/' && tag.contains("<") && tag.contains(">") && !twotags(tag));
    }
    public boolean isClosingTag(String tag){
        if(tag.length() == 0){
            return false;
        }
        return (tag.charAt(1) == '/');
    }
    public boolean areMatchingTags(String tag1, String tag2) {
        tag1 = tag1.substring(1);
        return tag1.equals(tag2.substring(2));
    }
    public boolean twotags(String str){
        if(str.lastIndexOf("<") != 0 && str.contains("<")){
            return true;
        }
        return false;
    }
    public String replaceClosingTag(String openTag){
        String  sb = "";
        sb = openTag.charAt(0) + "/" + openTag.substring(1, openTag.length());
        return sb;
    }
    public boolean trackHierarchy(){
        
        return false;
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

    public ArrayList<String> Correct(ArrayList<String> data){
        for(int i=0; i<data.size(); i++){
            data.set(i, data.get(i).trim());
        }
        int openingcount = 0;
        int closingcount = 0;
        for (String string : data) {
            if(isOpeningTag(string)){
                openingcount++;
            }
            else if (isClosingTag(string)){
                closingcount++;
            }
        }
        System.out.println(closingcount+"   "+openingcount);
        ArrayList<String> outpuStrings = new ArrayList<String>();
        Stack<String> stack = new Stack<String>();
        Stack<Integer> indexStack = new Stack<Integer>();
        String str = "";
        boolean isMissing = false;
        for (int i = 0; i < data.size(); i++) {
            str = data.get(i);
            if(str.equals("")){
                continue;
            }
            // if we have 2 tags in the same line we extract both tags and compare them
            if(twotags(str)){
                String tagString1 = str.substring(0,str.indexOf(">")+1);
                String tagString2 = str.substring(str.lastIndexOf("<"));
                String dataString = str.substring(str.indexOf(">")+1, str.lastIndexOf("<"));
                stack.push(tagString1);
                indexStack.push(i);
                if(areMatchingTags(stack.peek(), tagString2)){
                    stack.pop();
                    indexStack.pop();
                }
                else{
                    tagString2 = replaceClosingTag(stack.peek());
                    stack.pop();  
                    indexStack.pop();  
                    str = tagString1 + dataString +tagString2;                
                }
            }
            else if(str.charAt(str.length()-1) != '>' && str.charAt(0) == '<'){
                String tagString1 = str.substring(0,str.indexOf(">")+1);
                String tagString2 = "</"+ tagString1.substring(1);
                str = str + tagString2;
            }
            // if tag is opening push
            else if(isOpeningTag(str)){
                if(!stack.isEmpty() && stack.peek().equals(str)){
                    if(!isOpeningTag(data.get(indexStack.peek()+1)) && !isClosingTag(data.get(indexStack.peek()+1)) && !twotags(data.get(indexStack.peek()+1))){
                        outpuStrings.add(outpuStrings.lastIndexOf(stack.peek()) +2, "</default>");
                    stack.pop();
                            indexStack.pop();
                    }
                    else{ 
                        int maxdataindex = 0;
                        for (int j = indexStack.peek()+1; j < i; j++) {
                            if(!isOpeningTag(data.get(j)) && !isClosingTag(data.get(j)) && !twotags(data.get(j))){
                                maxdataindex = j;
                            }
                        }
                        outpuStrings.add(maxdataindex+1, "</default>");
                        stack.pop();
                        indexStack.pop();
                    }
                }
                stack.push(str);
                indexStack.push(i);
            }
            // if tag is closing compare with top of stack
            else if(isClosingTag(str)){
                    // if matching pop
                    if(!stack.isEmpty() && areMatchingTags(stack.peek(), str)){
                        stack.pop();
                        indexStack.pop();
                    }
                    //if not matching we need to check if the closing tag of the to is different
                    else if(openingcount == closingcount && !stack.isEmpty()){
                        str = replaceClosingTag(stack.peek());
                        stack.pop();
                        indexStack.pop();
                    }
                    else if(openingcount >= closingcount){
                        Stack<String> stack2 = new Stack<String>();
                        while(!stack.isEmpty()){
                            if(areMatchingTags(stack.peek(), str)){
                                isMissing = true;
                                break;
                            }
                            stack2.push(stack.pop());
                        }
                        while(!stack2.isEmpty()){
                            stack.push(stack2.pop());
                        }
                        if(isMissing){

                            if(!isOpeningTag(data.get(indexStack.peek()+1)) && !isClosingTag(data.get(indexStack.peek()+1)) && !twotags(data.get(indexStack.peek()+1))){
                                outpuStrings.add(outpuStrings.lastIndexOf(stack.peek()) +2, "</default>");
                                stack.pop();
                                indexStack.pop();
                            }
                            else{ 
                                int maxdataindex = 0;
                                for (int j = indexStack.peek()+1; j < i; j++) {
                                    if(!isOpeningTag(data.get(j)) && !isClosingTag(data.get(j)) && !twotags(data.get(j))){
                                        maxdataindex = j;
                                    }
                                }
                                if(!str.equals("")){
                                    outpuStrings.add(maxdataindex+1, "</default>");
                                }
                                stack.pop();
                                indexStack.pop();

                            }
                            isMissing = false;
                            closingcount++;
                            i--;
                            continue;
                        }
                        else{
                            continue;
                        }
                    }
                }
                //if it is data we dont want to do anything with it
                else{
                    outpuStrings.add(str);
                    continue;
                }
            outpuStrings.add(str);            
        }

        return outpuStrings;
    }
}    
