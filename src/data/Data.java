package data;


import java.util.ArrayList;

public abstract class Data {
	private ArrayList<TimeSample> datalist= new ArrayList<TimeSample>();
	 
	abstract public void readData(String url);  
	  
	@Override
	public String toString() {
		return "Data [datalist=" + datalist + "]";
	}
	
}
