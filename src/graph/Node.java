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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (node != other.node)
			return false;
		return true;
	}
	
}
