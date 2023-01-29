import java.io.File;  
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList; 

public class ReadFile {
    ArrayList<String> dataInFile = new ArrayList<String>();

    ReadFile(String file){
        try {
			File myObj = new File(file);
			Scanner myReader = new Scanner(myObj);
			
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();			  
			  	this.dataInFile.add(data);
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
    }

    String intoString(){
        StringBuffer sb = new StringBuffer();
      
      for (String s : this.dataInFile) {
        sb.append(s);
        sb.append("\n");
      }
      String str = sb.toString();
        return str;
    }
    String intoString(ArrayList<String> arr){

      StringBuffer sb = new StringBuffer();
    
    for (String s : arr) {
      sb.append(s);
      sb.append("\n");
    }
    String str = sb.toString();
      return str;
  }

    void createFile(String input, String filepath){
        try {
            File myObj = new File(filepath);
            if (myObj.createNewFile()) {
                FileWriter myWriter = new FileWriter(filepath);
                myWriter.write(input);
                myWriter.close();
            } else {
                FileWriter myWriter = new FileWriter(filepath);
                myWriter.write(input);
                myWriter.close();
            }
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }

    }
    
}
