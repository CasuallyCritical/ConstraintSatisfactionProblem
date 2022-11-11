import java.awt.Color;
import java.util.ArrayList;

public class Node {

	String name = "";
	
	public ArrayList<Node> neighbors = new ArrayList<Node>();
	
	String color = "";
	
	ArrayList<String> domain = new ArrayList<String>();
	
	public Node(String name) {
		this.name = name;
		domain.add("RED");
		domain.add("BLUE");
		domain.add("GREEN");
	}
	
	public void SetColor(String c) {
		color = c;
	}
	
	public void removeFromDomain(String c) {
		for(int i = 0; i < domain.size(); i++) {
			String check = domain.get(i);
			if(check.equals(c)) {
				domain.remove(i);
				break;
			}
		}
	}
	
	public boolean isFailCase() {
		for(Node n : neighbors) {
			if(n.color == color) {
				return true;
			}
		}
		
		return false;
	}
	
	public void addNeighbor(Node n) {
		neighbors.add(n);
		n.neighbors.add(this);
	}
	
	@Override
	public String toString() {
		return name;
	}
	
}
