package data;

/**
 * The interface Data handles with the data from input files, and its methods are associated with reading operations that 
 * allow the user to manipulate the data from a specific .csv file with the input format considered for this project.
 *
 * @author Nuno Mendes
 * @author Sofia Silva
 * @author Tiago Ricardo
 * 
 */

public interface Data {
	/**
	 * The method get() iterates over an ArrayList that includes the data read from the input files in order to put it into an array of integers.
	 * In the case of the training data, the array is bi-dimensional and it's two columns are relative to the values of the random variables in time-slices t and t+1, respectively.  
	 *
	 * @return data		Array of integers with the data retrieved from the input files. 
	 */
	public int[][] get();
	
	/**
	 * The method allows the user to access the names of the random variables. This access is made through an array of Strings with the data from the input files. 
	 *
	 * @return names	Array of strings with the names of the random variables.
	 */
	public String[] getNames();
	
	/**
	 * The method returns the number of random variables.
	 *
	 * @return num_va	Number of Random Variables
	 */
	public int getNumVA();
	
	/**
	 * The method returns the number of events.
	 *
	 * @return size		Number of Events.
	 */
	public int getSize();
}
