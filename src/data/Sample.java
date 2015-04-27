package data;


import graph.VarValue;

import java.util.ArrayList;

public class Sample 
{
	private int var;
	private ArrayList<VarValue> oclist= new ArrayList<VarValue>();
	
	protected Sample(int v)
	{
		var = v;
	}

	protected void add(int val){
		add(val);
	}
	
	public VarValue get(int pos){
		return oclist.get(pos);
	}

	@Override
	public String toString() {
		return "Sample [var=" + var + ", oclist=" + oclist + "]";
	}

	public int getMax(){
		int cfg=-1;
		for (VarValue varvalue : oclist) {
			if (varvalue.value()>cfg){
				cfg=varvalue.value();
			}		
		}
		return cfg;
	}
	
	protected int size(){
		return size();
	}
	
	
}
