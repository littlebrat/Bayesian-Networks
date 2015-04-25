package graph;

public class Node 
{
	private int node;
	
	
	protected Node(int no)
	{
		node = no;
	}
	
	protected int value()
	{
		return node;
	}

	@Override
	public String toString() {
		return "Node [node=" + node + "]";
	}
	
}
