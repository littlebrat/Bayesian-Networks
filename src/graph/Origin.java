package graph;

import java.util.ArrayList;

public class Origin extends Node{
	
	protected ArrayList<Destination> destlist = new ArrayList<Destination>();
	
	protected Origin(int n) {
		super(n);
		// TODO Auto-generated constructor stub
	}
	
	protected int size() {
		return destlist.size();
	}
	
	protected ArrayList<Integer> getDests(){
		ArrayList<Integer> res = new ArrayList<Integer>();
		for (Destination dest : destlist) {
			res.add(dest.getValue());
		}
		return res;
	}
	
	protected boolean isEmpty(){
		if(destlist.size()==0)	return true;
		return false;
	}
	
	protected boolean contains(int node){
		return destlist.contains(new Destination(node));
	}
	
	protected void add(int node) {
		// TODO Auto-generated method stub
		if(!contains(node))
			destlist.add(new Destination(node));
	}
	
	protected void remove(int node) {
		// TODO Auto-generated method stub
		destlist.remove(new Destination(node));
	}
	
}
