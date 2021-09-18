import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

	static int[][] graph=null;;
	static HashMap<Parameter, Integer> cache = new HashMap<>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		graph = new int[n+1][n+1]; // the memory requirement may exceeds limitation.
		
		
		int id1 = sc.nextInt();
		int id2 = sc.nextInt();
		
		while (id1!=0 || id2!=0) {
			graph[id1][id2]=1;
			
			id1 = sc.nextInt();
			id2 = sc.nextInt();
		}
		
		
		
		System.out.println(getNoOfPaths(1,n));
	}
	
	
	private static int getNoOfPaths(int id1, int id2) {
		// TODO Auto-generated method stub
		int sum = 0;
		if (id1 == id2) {
			return 1;
		}
		
		Parameter parameter = new Parameter(id1,id2);
		if (cache.containsKey(parameter)) {
			return cache.get(parameter);
		}
		
		for (int child:getChildren(id1)) {
			sum+=getNoOfPaths(child, id2);
		}
		
		cache.put(parameter, sum);
		return sum;
	}


	private static ArrayList<Integer> getChildren(int id) {
		// TODO Auto-generated method stub
		ArrayList<Integer> children=new ArrayList<>();
		for (int i=1;i<graph[id].length;i++) {
			if (graph[id][i]==1) {
				children.add(i);
			}
		}
		return children;
	}

}

class Node {
	int id;
	ArrayList<Node> children = new ArrayList<>();
	
	public Node(int id) {
		this.id = id;
	}
	
}

class Parameter {
	int p1;
	int p2;
	
	public Parameter(int p1,int p2) {
		this.p1=p1;
		this.p2=p2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + p1;
		result = prime * result + p2;
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
		if (p1 != other.p1)
			return false;
		if (p2 != other.p2)
			return false;
		return true;
	}

	
	
	
}
