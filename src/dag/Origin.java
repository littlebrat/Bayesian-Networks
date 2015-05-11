package dag;

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
	
	public boolean contains(int node){
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((destlist == null) ? 0 : destlist.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Origin other = (Origin) obj;
		if (destlist == null) {
			if (other.destlist != null)
				return false;
		} else if (!destlist.equals(other.destlist))
			return false;
		return true;
	}
	
	
	
}
