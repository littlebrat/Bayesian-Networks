package graph;

import java.util.Arrays;

public abstract class Graph
{
	private int dim;
    protected Edge[] grph;
    
    public Graph(Configuration[] cfs) // put all the configurations here
    {
        dim = cfs.length;
        grph = new Edge[dim];
        for (int i = 0; i < cfs.length; i++) {
			grph[i]=new Edge(i,cfs[i]);
		}
    }
    
    abstract public void add(int ori,int dest);
    
    abstract public void remove(int ori,int dest);
    
    abstract public boolean isEmpty();
    
    abstract public void reverse(int ori,int dest);
    
    @Override
	public String toString() {
		return "Graph [dim=" + dim + ", grph=" + Arrays.toString(grph) + "]";
	}
    
    abstract public Parents getParents(int x);
    
    abstract Starter get(int node);
    
    abstract public void refresh();
}
