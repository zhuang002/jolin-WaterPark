import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

	static Node[] nodes = null;
	static HashMap<Parameter, Integer> cache = new HashMap<>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		nodes = new Node[n];
		
		for (int i=0;i<n;i++) {
			Node node = new Node(i+1);
			nodes[i]=node;
		}
		
		int id1 = sc.nextInt();
		int id2 = sc.nextInt();
		
		while (id1!=0 || id2!=0) {
			Node node1 = nodes[id1-1];
			Node node2 = nodes[id2-1];
			node1.children.add(node2);
			
			id1 = sc.nextInt();
			id2 = sc.nextInt();
		}
		
		
		
		System.out.println(getNoOfPaths(nodes[0],nodes[n-1]));
	}
	
	
	private static int getNoOfPaths(Node node1, Node node2) {
		// TODO Auto-generated method stub
		int sum = 0;
		if (node1.id == node2.id) {
			return 1;
		}
		
		Parameter parameter = new Parameter(node1,node2);
		if (cache.containsKey(parameter)) {
			return cache.get(parameter);
		}
		
		for (Node child:node1.children) {
			sum+=getNoOfPaths(child,node2);
		}
		
		cache.put(parameter, sum);
		return sum;
	}

}

class Node {
	int id;
	ArrayList<Node> children = new ArrayList<>();
	
	public Node(int id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}

class Parameter {
	Node p1;
	Node p2;
	
	public Parameter(Node p1,Node p2) {
		this.p1=p1;
		this.p2=p2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((p1 == null) ? 0 : p1.hashCode());
		result = prime * result + ((p2 == null) ? 0 : p2.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Parameter other = (Parameter) obj;
		if (p1 == null) {
			if (other.p1 != null)
				return false;
		} else if (!p1.equals(other.p1))
			return false;
		if (p2 == null) {
			if (other.p2 != null)
				return false;
		} else if (!p2.equals(other.p2))
			return false;
		return true;
	}
	
	
}
