package dag;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		AdjacencyList adj= new AdjacencyList(5);
		adj.add(3,4);
		adj.add(2,4);
		adj.add(3,4);
		
		System.out.println(adj);
	}

}
