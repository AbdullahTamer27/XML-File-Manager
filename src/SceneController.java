import java.util.ArrayList;
import java.util.Stack;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SceneController {
    static String state = "compress";

    @FXML
    private Button compressbutton;

    @FXML
    private Button validationbuttun;

    @FXML
    private TextField input1;

    @FXML
    private TextArea inputarea;

    @FXML
    private TextArea outputarea;

    @FXML
    private Label tftitle;

    @FXML
    private Button minifybutton;

    @FXML
    private Button pretifybutton;



    @FXML
    private Button enterbutton;

    @FXML
    void compress(ActionEvent event) {
        String path = input1.getText();
        ReadFile input = new ReadFile(path);
        Huffmantrial huffman = new Huffmantrial(input.intoString());		
        String encoded = huffman.encode();

        if(state == "compress"){            
            outputarea.setText(encoded);
            state = "decompress";
            compressbutton.setText("Decompress");

        }
        else if(state == "decompress"){
            String original = huffman.decode(encoded);
            outputarea.setText(original);
            state = "compress";
            compressbutton.setText("Compress");
        }

    }
    @FXML
    void enter(ActionEvent event) {
        String path = input1.getText();
        ReadFile input = new ReadFile(path);
        inputarea.setText(input.intoString());
    }

    @FXML
    void minify(ActionEvent event) {
        String path = input1.getText();
        System.out.println(path);
        Minify minifiying = new Minify();
        String outpuArrayList = minifiying.minifiying(path);

        outputarea.setText(outpuArrayList);

    }
    @FXML
    void pritify(ActionEvent event) {
        ArrayList<String> arr;

       ReadFile r = new ReadFile("/Users/abdullahtamer/Desktop/demo/src/sample.xml");
       arr= r.dataInFile;
       
       for(int i=0;i<arr.size();i++){
           arr.set(i, arr.get(i).trim());
       }


       String[] res= new String[arr.size()];
       arr.toArray(res);


       Prettify p = new Prettify(res);
       String[] result = p.pretty();
       String ouString = "";
       for(int i = 0; i< result.length; i++){
        ouString = ouString + result[i] + "\n";

       }
       outputarea.setText(ouString);

    }
    @FXML
    void validate(ActionEvent event) {
        ReadFile r = new ReadFile(input1.getText());
        ArrayList<String> arr = new ArrayList<String>();
        arr = r.dataInFile;
        for(int i=0;i<arr.size();i++){
            arr.set(i, arr.get(i).trim());
        }
 
        String[] res = new String[arr.size()];
        String[] result = arr.toArray(res);
        Stack<String> st = new Stack<String>() ;
        validation validation = new validation();
        String str  = validation.isXMLTagMatched(st,result);
        outputarea.setText(str);

        

    }

}








