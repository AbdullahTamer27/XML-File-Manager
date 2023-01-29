import java.util.Stack;

public class XMLValidation {
public static boolean isValidXML(String[] lines) {
Stack<String> stack = new Stack<>();
for (String line : lines) {
int endTagIndex = line.indexOf("</");
int startTagIndex = line.indexOf("<");
if (endTagIndex == -1 && startTagIndex == -1) {
continue;
}
if (endTagIndex == 0) {
int closingTagEndIndex = line.indexOf(">");
String closingTag = line.substring(2, closingTagEndIndex);
if (stack.isEmpty() || !stack.pop().equals(closingTag)) {
return false;
}
}
if (startTagIndex == 0) {
int openingTagEndIndex = line.indexOf(">");
int closingTagStartIndex = line.indexOf("</");
if (closingTagStartIndex != -1 && openingTagEndIndex > closingTagStartIndex) {
int closingTagEndIndex = line.indexOf(">", closingTagStartIndex);
String openingTag = line.substring(1, closingTagStartIndex - 1);
String closingTag = line.substring(closingTagStartIndex + 2, closingTagEndIndex);
if (!stack.isEmpty() && stack.pop().equals(closingTag) && stack.isEmpty() || stack.pop().equals(openingTag)) {
continue;
} else {
return false;
}
} else {
String openingTag = line.substring(1, openingTagEndIndex);
stack.push(openingTag);
}
}
}
return stack.isEmpty();
}
    public static void main(String[] args) {
        String[] lines = { "<root>", "<element>", "<sub-element>", "Content", "</sub-element>", "</element>", "</root>" };
        System.out.println("Is valid XML: " + isValidXML(lines));
    }
}
