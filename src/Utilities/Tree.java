package Utilities;
public class Tree { 
    static class Node {    
    int value;
    String text; 
        Node left, right; 
          
        Node(int value, String text){ 
            this.text = text;
            this.value = value; 
            left = null; 
            right = null; 
        } 
    } 
       
    public void insert(Node node, int value, String text) {
        if (value < node.value) { if (node.left != null) { insert(node.left, value, text); } else { System.out.println(" Inserted " + value + " to left of " + node.value); node.left = new Node(value, text); } } else if (value > node.value) {
          if (node.right != null) {
            insert(node.right, value , text);
          } else {
            node.right = new Node(value, text);
            }
        }
    }


    public void traverseInOrder(Node node) {
        if (node != null) {
            traverseInOrder(node.left);
            System.out.print(" " + node.value);
            traverseInOrder(node.right);
        }
    }
     
    public static void main(String args[]) 
    { 

    }
}
