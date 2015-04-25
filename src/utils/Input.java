package utils;

public class Input {
	private String train;
	private String test;
	private String score;
	private int randtest;
	private int var;
	
	public Input(String[] args)
	{
		train = args[0];
		test = args[1];
		score = args[2];
		randtest = Integer.parseInt(args[3]);
		var = Integer.parseInt(args[4]);
	}
	
	public String getTrain()
	{
		return train;
	}
	
	public String getTest()
	{
		return test;
	}
	
	public String getScore()
	{
		return score;
	}
	
	public int getRandtest()
	{
		return randtest;
	}
	
	public int getVar()
	{
		return var;
	}
}
