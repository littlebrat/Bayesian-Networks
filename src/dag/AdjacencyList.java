package dag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class AdjacencyList implements Graph{
	
	protected Origin<Integer>[] originlist;
	
	public AdjacencyList(int dim){
		originlist = (Origin<Integer>[]) new Origin[dim];
		for (int i = 0; i < originlist.length; i++) {
			originlist[i]=new Origin<Integer>(i);
		}
	}
	
	@Override
	public void add(int ori, int dest) {
		// TODO Auto-generated method stub
		if(ori>=0 && ori<size() && dest>=0 && dest<size() && ori!=dest)
			if(reachable(dest)[ori]==0){
				originlist[ori].add(dest);
			}
	}

	@Override
	public void remove(int ori, int dest) {
		// TODO Auto-generated method stub
		if(ori>=0 && ori<size() && dest>=0 && dest<size() && ori!=dest)
			originlist[ori].remove(dest);
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		for (Origin<Integer> origin : originlist) {
			if(origin.size()!=0){
				return false;
			}
		}
		return true;
	}

	@Override
	public void reverse(int ori, int dest) {
		// TODO Auto-generated method stub
		if(ori>=0 && ori<size() && dest>=0 && dest<size() && ori!=dest && originlist[ori].contains(dest)==true){
			originlist[ori].remove(dest);
			if(reachable(ori)[dest]==0)
				originlist[dest].add(ori);
			else originlist[ori].add(dest);
		}
	}

	@Override
	public String toString() {
		String r = new String();
		for (int i = 0; i < originlist.length; i++) {
			for (int j = 0; j < originlist.length; j++) {
				if(originlist[j].contains(i)){
					r=r+' '+'1';
				}
				else{
					r=r+' '+'0';
				}
			}
			r=r+'\n';
		}
		return r;
	}

	@Override
	public ArrayList<Integer> getDests(int x) {
		// TODO Auto-generated method stub
		return originlist[x].getDests();
	}
	
	@Override
	public ArrayList<Integer> getOrigins(int x) {
		// TODO Auto-generated method stub
		ArrayList<Integer> ori = new ArrayList<Integer>();
		for (Origin<Integer> origin : originlist) {
			if(origin.contains(x))
				ori.add(origin.getValue());
		}
		return ori;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return originlist.length;
	}
	
	private int[] reachable(int dest){
		
		Stack<Integer> stack = new Stack<Integer>();
		int[] visited = new int[size()];
		stack.push(dest);
		int v;
		while(!stack.isEmpty()){
			v=stack.pop();
			if(visited[v]!=1){
				visited[v]=1;
				for (Integer w : getDests(v)) {
					stack.push(w);
				}
			}
		}	
		return visited;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(originlist);
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
		AdjacencyList other = (AdjacencyList) obj;
		if (!Arrays.equals(originlist, other.originlist))
			return false;
		return true;
	}
	
	@Override
	public AdjacencyList clone(){
		// TODO Auto-generated method stub
		AdjacencyList nova = new AdjacencyList(originlist.length);
		for (Origin<Integer> origin : originlist) {
			for (int i = 0; i < originlist.length; i++) {
				if(origin.contains(i)) nova.add(origin.getValue(),i);
			}
		}
		return nova;
	}

	@Override
	public boolean contains(int ori,int dest) {
		return originlist[ori].contains(dest);
	}
	
}
