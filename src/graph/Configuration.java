package graph;

public class Configuration {
	protected VarValue[] val; 
	
	protected Configuration(int cfg[])
	{
		val = new VarValue[cfg.length];
		
		for (int i = 0; i < cfg.length; i++) 
		{
			val[i]=new VarValue(cfg[i]);
		}
	}
	
	protected int get(int pos)
	{
		return val[pos].value();
	}
	
	 protected int length()
	 {
		 return val.length;
	 }
}
