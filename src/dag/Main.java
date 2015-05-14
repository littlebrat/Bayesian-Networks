package dag;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		AdjacencyList adj= new AdjacencyList(5);
		ArrayList<Integer> array= new ArrayList<Integer>();
		
		adj.add(0, 4);
		adj.add(0,5);
		adj.add(1,3);
		adj.add(4,1);
		adj.add(3,0);
		
		array=adj.getDests(1);
		System.out.println(array);
		adj.reverse(1,3);
		adj.reverse(2, 3);
		
		
		
		
		System.out.println(adj);
		}

}
