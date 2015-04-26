package graph;

import java.util.ArrayList;

public class BayesNet extends Graph
{
	
	public BayesNet(Configuration[] cfs) 
	{
		super(cfs);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void add(int ori, int dest) 
	{
		// TODO Auto-generated method stub
		grph[ori].add(dest);
	}

	@Override
	public void remove(int ori, int dest)
	{
		// TODO Auto-generated method stub
		grph[ori].remove(dest);
	}

	@Override
	public boolean isEmpty() 
	{
		// TODO Auto-generated method stub
		for (Edge edge : grph) 
		{
			if(!edge.isEmpty())
				return false;
		}
		return true;
	}

	@Override
	public void reverse(int ori, int dest) 
	{
		// TODO Auto-generated method stub
		this.remove(ori, dest);
		this.add(dest,ori);
	}

	@Override
	public Parent getParents(int x) 
	{
		// TODO Auto-generated method stub
		Parent prts = new Parent();
		for (Edge edge : grph) 
		{
			if(edge.contains(x) && edge.getStarter().value()!=x)
				prts.add()
		}
		return null;
	}

	@Override
	Starter get(int node) 
	{
		// TODO Auto-generated method stub
		return grph[node].getStarter();
	}

	@Override
	public void refresh() 
	{
		// TODO Auto-generated method stub
		
	}
	
	
}
