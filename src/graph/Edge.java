package graph;

import java.util.ArrayList;

public class Edge 
{
	protected ArrayList<Node> nodelist;
	protected Starter strt;
	
	protected Edge(int str,Configuration c)
	{
		nodelist = new ArrayList<Node>();
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

	@Override
	public String toString() {
		return "Edge [nodelist=" + nodelist + ", strt=" + strt + "]";
	}
	
}
