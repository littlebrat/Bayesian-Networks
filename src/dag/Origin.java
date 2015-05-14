package dag;

import java.util.ArrayList;

public class Origin<T> extends Node<T>{
	
	protected ArrayList<Destination<T>> destlist = new ArrayList<Destination<T>>();
	
	protected Origin(T element) {
		super(element);
		// TODO Auto-generated constructor stub
	}
	
	protected int size() {
		return destlist.size();
	}
	
	protected ArrayList<T> getDests(){
		ArrayList<T> res = new ArrayList<T>();
		for (Destination<T> dest : destlist) {
			res.add(dest.getValue());
		}
		return res;
	}
	
	protected boolean isEmpty(){
		if(destlist.size()==0)	return true;
		return false;
	}
	
	protected boolean contains(T node){
		return destlist.contains(new Destination<T>(node));
	}
	
	protected void add(T node) {
		// TODO Auto-generated method stub
		if(!contains(node))
			destlist.add(new Destination<T>(node));
	}
	
	protected void remove(T node) {
		// TODO Auto-generated method stub
		destlist.remove(new Destination<T>(node));
	}
	
}
