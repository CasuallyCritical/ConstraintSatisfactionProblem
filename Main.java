import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Main {
	
	ArrayList<Node> regions = new ArrayList<Node>();
	
	public ArrayList<Node> findSolutions() {
		Random rng = new Random();
		int i = rng.nextInt(0, regions.size());
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(regions.get(i));
		
		Node lastNode = regions.get(i);
		
		ArrayList<Node> colorPath = new ArrayList<Node>();
		
		while(queue.isEmpty() == false) {
			Node n = queue.poll();
			if(n.color.isEmpty()) {
				if(n.domain.size() > 0) {
					int c = rng.nextInt(n.domain.size());
					String color = n.domain.get(c);
					System.out.println(n.toString() + " Color: " + color);
					
					n.SetColor(color);
					if(n.isFailCase() == false) {
						
						colorPath.add(n);
						
						lastNode = n;
						n.removeFromDomain(color);
						
						for(Node neighbor : n.neighbors) {
							queue.add(neighbor);
							neighbor.removeFromDomain(color);
						}
					} else {
						n.SetColor(null);
						queue.add(lastNode);
					}
				} else {
					System.out.println("WARNING: Failed to find path using this color chart");
					return null;
				}
			}
		}
		
		return colorPath;
	}
	
	public Main() {
		Node WA = new Node("Western Australia");
		Node NT = new Node("Northern Territory");
		Node SA = new Node("South Australia");
		Node QL = new Node("Queensland");
		Node NSW = new Node("New South Wales");
		Node VT = new Node("Victoria");
		Node TM = new Node("Tasmania");
		
		
		WA.addNeighbor(NT);
		WA.addNeighbor(SA);
		NT.addNeighbor(WA);
		NT.addNeighbor(SA);
		NT.addNeighbor(QL);
		SA.addNeighbor(WA);
		SA.addNeighbor(NT);
		SA.addNeighbor(QL);
		SA.addNeighbor(NSW);
		SA.addNeighbor(VT);
		QL.addNeighbor(NT);
		QL.addNeighbor(SA);
		QL.addNeighbor(NSW);
		NSW.addNeighbor(SA);
		NSW.addNeighbor(QL);
		NSW.addNeighbor(VT);
		VT.addNeighbor(SA);
		VT.addNeighbor(NSW);
		VT.addNeighbor(TM);
		TM.addNeighbor(VT);
		
		
		regions.add(WA);
		regions.add(NT);
		regions.add(SA);
		regions.add(QL);
		regions.add(NSW);
		regions.add(VT);
		regions.add(TM);
		
		ArrayList<Node> path = findSolutions();
		
		if(path == null) {
			new Main();
		} else {
			System.out.println("Final Path: " + '\n');
			for(Node n : path) {
				System.out.println("Region: " + n.toString());
				System.out.println("Color: " + n.color);
				
				System.out.println("Fail case? : " + n.isFailCase());
				
				System.out.println("Neighbors: ");
				for(Node i : n.neighbors) {
					System.out.print(i.toString() + ", ");
				}
				System.out.print('\n');
			}
		}
	}
	
	public static void main(String[] args) {
		new Main();
	}

}
