package dag;

import java.util.ArrayList;
import java.util.Stack;

public class AdjacencyList implements Graph{
	
	protected Origin originlist[];
	
	public AdjacencyList(int dim){
		originlist = new Origin[dim];
		for (int i = 0; i < originlist.length; i++) {
			originlist[i]=new Origin(i);
		}
	}
	
	@Override
	public void add(int ori, int dest) {
		// TODO Auto-generated method stub
		if(ori>=0 && ori<size() && dest>=0 && dest<size())
			if(reachable(dest)[ori]==0){
				originlist[ori].add(dest);
			}
	}

	@Override
	public void remove(int ori, int dest) {
		// TODO Auto-generated method stub
		if(ori>=0 && ori<size() && dest>=0 && dest<size())
			originlist[ori].remove(dest);
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		for (Origin origin : originlist) {
			if(origin.size()!=0){
				return false;
			}
		}
		return true;
	}

	@Override
	public void reverse(int ori, int dest) {
		// TODO Auto-generated method stub
		if(ori>=0 && ori<size() && dest>=0 && dest<size()){
			originlist[ori].remove(dest);
			originlist[dest].add(ori);
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
	
}
