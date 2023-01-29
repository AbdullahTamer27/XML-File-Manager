
public class Node implements Comparable<Node>{
	private final int frequency;
	private Node left;
	private Node right;
	
	int getFrequency() {
		return this.frequency;
	}
	Node getleft() {
		return this.left;
	}
	Node getright() {
		return this.right;
	}
	
	public Node(Node l , Node r) {
		this.frequency = l.getFrequency() + r.getFrequency();
		this.left = l;
		this.right = r;
	}
	public Node(int freq) {
		this.frequency = freq;
	}
	@Override
	public int compareTo(Node node) {
		return Integer.compare(frequency, node.getFrequency());
	}
	

}
