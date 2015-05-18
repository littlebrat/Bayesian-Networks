package utils;

import gui.WrongFileExtension;
import gui.WrongInput;
import gui.WrongScoreType;

public class Input {
	private String train;
	private String test;
	private String score;
	private int randtest;
	private int var;
	
	public Input(String[] args) throws WrongInput
	{
		if(args.length<4 || args.length>5){
			throw new WrongInput();
		}else{
			train = args[0];
			test = args[1];			
			score = args[2];
			randtest = Integer.parseInt(args[3]);
			if(args.length==4){
				var=0;
			}else{
				var = Integer.parseInt(args[4]);
			}
		}
	}
	
	
	public String getTrain() throws WrongFileExtension
	{
		if(this.train.endsWith(".csv")==false || this.train.endsWith(".csv")==false ){
			throw new WrongFileExtension();
		}else{
			return train;
		}
	}
	
	public String getTest() throws WrongFileExtension
	{
		if(this.test.endsWith(".csv")==false || this.test.endsWith(".csv")==false ){
			throw new WrongFileExtension();
		}else{
			return test;
		}
	}
	
	public String getScore() throws WrongScoreType
	{
		if(score.equals("MDL")==false && score.equals("LL")==true){
			return score;
		}else{
				if (score.equals("LL")==false && score.equals("MDL")==true) {
					return score;
				}else{
					throw new WrongScoreType();
				}
		}
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
