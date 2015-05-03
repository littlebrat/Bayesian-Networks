package dag;

import java.util.ArrayList;
import java.util.Stack;

public class AdjacencyMatrix implements Graph{
	private int[][] mat;
	
	public AdjacencyMatrix(int n){
		mat= new int[n][n];
	}
	
	@Override
	public void add(int ori, int dest) {
		// TODO Auto-generated method stub
		mat[ori][dest]=1;
	}

	@Override
	public void remove(int ori, int dest) {
		// TODO Auto-generated method stub
		mat[ori][dest]=0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {
				if (mat[i][j]==1) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public void reverse(int ori, int dest) {
		// TODO Auto-generated method stub
		mat[ori][dest]=0;
		mat[dest][ori]=1;
	}
	
	public boolean cycleQ(){
		if(size()>1){
			Stack<Integer> stack = new Stack<Integer>();
			int visited[] = new int[size()];
			int through[] = new int[size()];
			
			int i=0;
			while(i<size() && visited[0]!=0){
				int j=0;
				while(j<size() && visited[0]!=0){
					if(mat[i][j]!=0){
						visited[0]=mat[i][j];
						through[0]=-1;
						stack.push(mat[i][j]);
					}
					j++;
				}
				i++;
			}
					
			while(!stack.isEmpty()){
				
			}
			
		}
		return false;
	}

	@Override
	public ArrayList<Integer> getDests(int x) {
		// TODO Auto-generated method stub
		ArrayList<Integer> res = new ArrayList<Integer>();
		for (int i = 0; i < mat.length; i++) {
			if (mat[x][i]!=0) {
				res.add(i);
			}	
		}
		return res;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return mat.length;
	}

	@Override
	public String toString(){
		String s = new String();
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {
				s=s+" "+mat[i][j];
			}
			s=s+'\n';
		}
		return s;
	}
	
}