import java.util.ArrayList;
import java.util.Stack;
import Minify.Minify;
import Pretify.Prettify;
import Validation.validation;
import XML2JSON.json;
import Compression.Huffmantrial;

import Utilities.ReadFile;
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
    private Button correctionbutton;

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
    private Button xml2json;




    @FXML
    private Button enterbutton;

    @FXML
    void compress(ActionEvent event) {

        Huffmantrial huffman = new Huffmantrial(inputarea.getText());		
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

        String data = inputarea.getText();

        Minify minifiying = new Minify();
        String outpuArrayList = minifiying.minifiying(data);

        outputarea.setText(outpuArrayList);

    }
    @FXML
    void pritify(ActionEvent event) {
        ArrayList<String> arr;
        ReadFile r = new ReadFile(input1.getText());
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
        ArrayList<String> arr2 = new ArrayList<String>();
        arr2 = r.dataInFile;
        validation validation2 = new validation();
        arr2 = validation2.Correct(arr2);
        arr2 = validation2.Correct(arr2);
        String str2 = "";
        for(int i =0; i< arr2.size();i++){
            str2 = str2 + arr2.get(i) +"\n";
        }
        outputarea.setText(str+"\n\n"+str2);
    }


    @FXML
    void analyize(ActionEvent event) {
        ReadFile input = new ReadFile(input1.getText());
        ArrayList<String> test = new  ArrayList<String>();
        for(int i=0 ; i<input.dataInFile.size();i++)
      {
        test.add(input.dataInFile.get(i));
      }
        
        Graphh G= new Graphh(test);
//-------------------------------------------------------------------               
        ArrayList<String> output1 = G.print_users_data();
        String str = "";
        for(int i =0 ; i<output1.size() ; i++)
            str = str + output1.get(i) +"\n";
        
//------------------------------------------------------------------- 
       ArrayList< ArrayList<String>> output2 = G.printgraph();
       String str2 = "";
        for(int i =0 ; i<output2.size() ; i++)
        {
            for(int x=0 ;x< output2.get(i).size();x++){
               str2 = str2 + output2.get(i).get(x);
            }
            str2 = str2 +"\n";
        }
        str = str +"Graph:\n" + str2;
        
//-------------------------------------------------------------------
        String str3 = "-------------------------------------------------\n"+"Most Influencer:";
        str3 = str3 + "\n" +G.Most_Influencer();
        str = str + str3;

//--------------------------------------------------------------------
        String str4 = "\n-------------------------------------------------\n"+"Most Active:";
        str4 = str4 + "\n" +G.Most_Active();
        str = str + str4;

//--------------------------------------------------------------------

        ArrayList<String> output3 = G.Matual_followers();
        String str5 = "";
        for(int i =0 ; i<output3.size() ; i++)
            str5 = str5 + output3.get(i);
        str = str +"\n--------------------------------------------------------------------\n" + str5;

        

        String str6 = "\n--------------------------------------------------------------------\n";

        ArrayList< ArrayList<String>> output4 = G.Suggested_followers();
        for(int i =0 ; i<output4.size() ; i++)
        {
            for(int x=0 ;x< output4.get(i).size();x++){
               str6 = str6 + output4.get(i).get(x);
            }
            str6 = str6 +"\n";
        }
        str = str + str6;
        
        String str7 = "\n--------------------------------------------------------------------\n";
        ArrayList<String> output5 = G.search(inputarea.getText());
        for(int i =0 ; i<output5.size() ; i++){
            str7 = str7 + output5.get(i);
        }
        str = str + str7;
        outputarea.setText(str);

        String dotFormat = G.sendto();
        GraphViz.createDotGraph(dotFormat, "DotGraph");
    }
    
    @FXML
    void Xml2Json(ActionEvent event) {
        json json = new json();
        ArrayList<String> out = new ArrayList<String>(); 
        out = json.Convert(input1.getText());
        String str = "";
        for (int i = 0; i < out.size(); i++) {
            str = str + out.get(i) + "\n";    
        }
        outputarea.setText(str);

    }

}