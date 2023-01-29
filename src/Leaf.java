
public class Leaf extends Node{
	private final char character;
	
	public Leaf(char character, int frq) {
		super(frq);
		this.character = character;
	}
	public char getcharacter() {
		return this.character;
	}

}
