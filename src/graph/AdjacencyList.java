package graph;

import java.util.ArrayList;

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
			originlist[ori].add(dest);
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
				if(originlist[i].contains(j)){
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
	
	public static void main(String args[])
	{
		Graph m = new AdjacencyList(3);
		m.add(1, 1);
		m.add(1,2);
		m.add(2, 1);
		System.out.println(m);
		System.out.println(m.getDests(1));
	}
	
}
