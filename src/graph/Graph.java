package graph;

public class Graph
{
	protected int n;
    protected Edge[] grph;
    
    Graph(int dim) 
    {
        n = dim;
        grph = new Edge[n];
    }
    
    
}
