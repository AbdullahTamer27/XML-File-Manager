
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Huffmantrial {
	private final String text;
	private Node root;
	private Map<Character , Integer> charFrequencies;
	private final Map<Character, String> huffmanCodes;
	
	public Huffmantrial(String text) {
		this.text = text;
		fillCharFrequenciesMap();
		huffmanCodes = new HashMap<>();
	}
	
	private void fillCharFrequenciesMap()
	{
		charFrequencies = new HashMap<>();
		for(char character : text.toCharArray()) {
			Integer integer = charFrequencies.get(character);
			charFrequencies.put(character, integer != null ? integer +1 : 1);
		}
		
	}
	public String encode(){
		Queue<Node> queue = new PriorityQueue<>();
		charFrequencies.forEach((character, frequency) -> queue.add(new Leaf(character, frequency)));
		while(queue.size() > 1) {
			queue.add(new Node(queue.poll(), queue.poll() ));
		}
		generateHuffmanCodes(root = queue.poll(), "");
		
		return binary2ascii("e","");
	}
	
	private void generateHuffmanCodes(Node node, String code) {
		if(node instanceof Leaf) {
			huffmanCodes.put(( (Leaf) node).getcharacter(), code);
			return;
			
		}
		generateHuffmanCodes(node.getleft(), code.concat("0"));
		generateHuffmanCodes(node.getright(), code.concat("1"));
		
	}
	
	private String getEncodedText() {
		StringBuilder sb = new StringBuilder();
		for(char charachter : text.toCharArray()) {
			sb.append(huffmanCodes.get(charachter));		
		}
		return sb.toString();
	}
	
	public String decode(String encodedtext) {
		encodedtext = binary2ascii("d",encodedtext);
		StringBuilder sb = new StringBuilder();
		Node current = root;
		for(char charachter : encodedtext.toCharArray()) {
			current = charachter =='0' ? current.getleft():current.getright();
			
			if(current instanceof Leaf) {
				sb.append(((Leaf) current).getcharacter());
				current = root;
			}
			
		}
		return sb.toString();
	}

	public String binary2ascii(String type, String encodedtxt){

		String  binaryString = getEncodedText();

		//checking length and adding padding

		boolean isFactorOf8 = binaryString.length() % 8 ==0;
		String marker = "1";
		if(isFactorOf8){
			binaryString = marker + binaryString;
		}
		int padding = 8 - binaryString.length()%8;
		if(padding != 8){
			for (int i = 0; i < padding; i++) {
				binaryString = "0" + binaryString;
			}
		}

		// convert binary to ascii

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < binaryString.length(); i += 8) {
            int ascii = Integer.parseInt(binaryString.substring(i, i + 8), 2);
            sb.append((char) ascii);
        }
        String asciiString = sb.toString();

		// convert ascii back to binary

        StringBuilder binary = new StringBuilder();
		for (char c : asciiString.toCharArray()) {
            String asciiVal = Integer.toBinaryString((int) c);
            while(asciiVal.length()<8) asciiVal = "0"+asciiVal;
            binary.append(asciiVal);
        }
        String binaryString2 = binary.toString();

		//remove padding
		String originalBinaString  ="";
		if(isFactorOf8){
			originalBinaString = binaryString2.substring(binaryString2.indexOf(marker)+marker.length());
		}
		else{
			int originalLength = binaryString.length() - padding;
			originalBinaString = binaryString2.substring(binaryString2.length() -originalLength);

		}
	

		if(type == "e"){
			return asciiString;
		}
		return originalBinaString ;

	}


	
	public void printCodes() {
		huffmanCodes.forEach((character, code) -> System.out.println(character + ": "+code));
	}
}
