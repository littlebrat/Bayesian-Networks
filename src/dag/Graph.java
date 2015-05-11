package dag;

import java.util.ArrayList;

public interface Graph {
	
	public void add(int ori,int dest);
	
	public void remove(int ori,int dest);
	
	public boolean isEmpty();
	
	public void reverse(int ori,int dest);
	
	public String toString();
	
	public ArrayList<Integer> getDests(int x);
	
	public ArrayList<Integer> getOrigins(int x);
	
	public int size();
	
	public boolean equals(Object obj);
	
	public int hashCode();
	
	public Graph clone();
	
}
