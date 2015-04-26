package graph;

import java.util.ArrayList;

public class Edge 
{
	private ArrayList<Node> nodelist= new ArrayList<Node>();
	private Starter strt;
	
	protected Edge(int str,Configuration c)
	{
		strt=new Starter(str,c);
	}
	
	protected void add(int numb)
	{
		nodelist.add(new Node(numb));
	}
	
	protected void remove(int numb)
	{
		nodelist.remove(new Node(numb));
	}
	
	protected Starter getStarter()
	{
		return strt;
	}

	protected boolean isEmpty()
	{
		if(nodelist.isEmpty())
			return true;
		else return false;
	}
	
	protected boolean contains(int node)
	{
		return nodelist.contains(new Node(node));
	}
	
	@Override
	public String toString() {
		return "Edge [nodelist=" + nodelist + ", strt=" + strt + "]";
	}
	
}
