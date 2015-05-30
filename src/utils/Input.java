package utils;

import gui.WrongFileExtension;
import gui.WrongInput;
import gui.WrongScoreType;

/**
 * The class Input is related with input parameters. This class handles with the input files and it gives some feedback related with incorrect parameters defined by the user.
 * 
 * @author Nuno Mendes
 * @author Sofia Silva
 * @author Tiago Ricardo
 * 
 */
public class Input {
	private String train;
	private String test;
	private String score;
	private int randtest;
	private int var;

	/**
	 * The constructor starts by initializing all the fields with the parameters. If no index of a random variable is specified, the constructor initializes the field var with "0" (meaning all random variables).
	 * 
	 * @param	args		The input arguments.
	 * @throws WrongInput	If the input parameters are wrongly inserted when compared with the predefined Input format for this project.
	 * 
	 */
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

	/**
	 * Returns the URL of the training file.
	 * 
	 * 
	 * @throws WrongFileExtension	If the extension of the files is not .csv .
	 * @return train				A string with the url of the training-data file.
	 */
	public String getTrain() throws WrongFileExtension
	{
		if(this.train.endsWith(".csv")==false || this.train.endsWith(".csv")==false ){
			throw new WrongFileExtension();
		}else{
			return train;
		}
	}
	
	/**
	 * Returns the URL of the testing file.
	 * 
	 * 
	 * @throws WrongFileExtension	If the extension of the files is not .csv .
	 * @return train				A string with the url of the testing-data file.
	 */
	public String getTest() throws WrongFileExtension
	{
		if(this.test.endsWith(".csv")==false || this.test.endsWith(".csv")==false ){
			throw new WrongFileExtension();
		}else{
			return test;
		}
	}
	
	/**
	 * Returns the type of score.
	 * 
	 * 
	 * @throws WrongScoreType		If the score type is neither "MDL" or "LL"
	 * @return score				A string with the score type.
	 */
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
	
	/**
	 * Returns the maximum number of random restarts.
	 * 

	 * @return randtest				The maximum number of random restarts.
	 */
	public int getRandtest()
	{
		return randtest;
	}
	
	/**
	 * Returns the index of the random variable.
	 * 

	 * @return var				Index of the random variable.
	 */
	public int getVar()
	{
		return var;
	}
}
