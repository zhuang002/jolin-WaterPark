import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

	static HashMap<Integer, ArrayList<Integer> > graph=new HashMap<>();
	static HashMap<Parameter, Integer> cache = new HashMap<>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		
		
		int id1 = sc.nextInt();
		int id2 = sc.nextInt();
		
		while (id1!=0 || id2!=0) {
			ArrayList<Integer> children =null;
			if (graph.containsKey(id1)) {
				children = graph.get(id1);
			} else {
				children = new ArrayList<>();
				graph.put(id1, children);
			}
			children.add(id2);
			
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
		
		if (!graph.containsKey(id1))
			return 0;
		
		ArrayList<Integer> children=graph.get(id1);
		for (int child:children) {
			sum+=getNoOfPaths(child, id2);
		}
		
		cache.put(parameter, sum);
		return sum;
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
