package gui;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import utils.Input;
import bayes.BayesDyn;
import bayes.BayesStatic;
import bayes.BayesianNetwork;
import data.DataTest;
import data.DataTrain;

/**
 * The class Commands extends JFrame opens a scrollable JTextArea in which the user can see the results before saving them into a file. This class handles the file names and parameters 
 * in order to run the algorithms.
 * 
 * @author Nuno Mendes
 * @author Sofia Silva
 * @author Tiago Ricardo
 * 
 */

@SuppressWarnings("serial")
public class Commands extends JFrame{
	JTextArea cmd;
	JScrollPane scroll;
	JFrame f;
	protected BayesianNetwork dbn=null;
	protected BayesianNetwork sbn;
	protected double time1;
	protected double time2;
	protected ArrayList<String> data;
	
	/**
	 * Besides opening a JTextArea where the data is printed, the constructor also runs the algorithms and it prints the results on the JTextArea.
	 * 
	 * @param	input		An array of strings with the input parameters.
	 * @see JTextArea
	 * @see JScrollPane
	 * @see BayesianNetwork
	 * @see Input
	 * 
	 */
		public Commands(String[] input){
			try{
				JFrame f = new JFrame();
			    JTextArea cmd = new JTextArea( );
			    JScrollPane scroll = new JScrollPane(cmd); 
			    f.setSize(900, 500);
			    f.getContentPane().add(scroll);
			    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			    f.setVisible(true);
				Input in = new Input(input);
				DataTrain dtrain=new DataTrain(in.getTrain());
				DataTest dtest=new DataTest(in.getTest());
				String score = new String();		
				score=in.getScore();
				data=new ArrayList<String> ();
			
				int randomrest=in.getRandtest();
				int var=in.getVar();
				double timetobuild = System.currentTimeMillis();
				
				cmd.append("Train File:	"+in.getTrain()+"\n"+"Test File:	"+in.getTest()+"\n"+"Score:	"+score+"\n"+"Random Restarts:	"+randomrest+"\n"+"Variable Index:	"+var+"\n");
				
				dbn = new BayesDyn(dtrain.get(),score,dtrain.getNames(),100);
				dbn.setRestarts(randomrest);
				dbn.greedyHill();
				time1=(System.currentTimeMillis()-timetobuild)/1000;
				sbn = new BayesStatic(dtrain.getInitData(input[0]),score,dtrain.getNames(),100);
				sbn.setRestarts(randomrest);
				sbn.greedyHill();
				
				
				cmd.append("\nBuilding DBN: 	"+time1+" time\n");
				cmd.append("\nInitial network:\n");
				cmd.append(sbn.toString());
				cmd.append("\nTransition network:\n");
				
				
				cmd.append(dbn.toString());
				String s=new String();
				cmd.append("\nPerforming inference:\n");
				//var specified
				if(var!=0){
					timetobuild=System.currentTimeMillis();
					int[] pred = dbn.getPredictions(var,dtest.get());
					time2=(System.currentTimeMillis()-timetobuild)/1000;
					for(int i=0; i<pred.length;i++){
						s+= "->instance "+i+":	"+pred[i]+"\n";
					}
				}else{
					timetobuild=System.currentTimeMillis();
					//var not specified
					int[][]pred=dbn.getAllPredictions(dtest.get());
					time2=(System.currentTimeMillis()-timetobuild)/1000;
					for(int i=0; i<pred.length;i++){
						s+= "->instance "+i+":	";
						for(int j=0;j<dtrain.getNumVA();j++){
							s+=pred[i][j]+",";
						}
						s=s.substring(0,s.length()-1);
						s+="\n";
					}
					
				}
				cmd.append(s);
				cmd.append("\nInferring with the DBN:" +time2+" time");
				data=save(dbn,sbn,s,time1,time2);
				}catch(WrongInput e){
					System.err.println("Correct Input Format: data-train, data-test, score, random-restarts, vars");
					System.exit(0);
				}catch(WrongFileExtension err){
					System.err.println("Correct File Extension: .csv");
					System.exit(1);
				}catch(WrongScoreType escore){
					System.err.println("Score Types: MDL or LL (Case Sensitive)");
					System.exit(2);
				}catch(IOException inputerr){
					System.err.println("File does not exist.");
					System.exit(3);
				}
		       
			
		}
/**
 * This method saves the data inside an ArrayList of Strings.
 * 
 * @param dbn		Dynamic Bayesian Network
 * @param sbn		Static 	Bayesian Network
 * @param s			String with the results of the inferences.
 * @param time1		The time spent to build the DBN model.
 * @param time2		The time spent to infer with the DBN model.
 * @see ArrayList
 * @see BayesianNetwork
 * @see SwingApplication
 * @return	data	An organized ArrayList of Strings that contains the obtained results.
 */
		public ArrayList<String> save(BayesianNetwork dbn,BayesianNetwork sbn, String s,double time1,double time2) {
			ArrayList<String> data= new ArrayList<String>();
			data.add(dbn.toString());
			data.add(sbn.toString());
			data.add(s);
			data.add(Double.toString(time1));
			data.add(Double.toString(time2));
			return data;
			
		}

/**
 * This method allows other classes to access the protected element data that contains the obtained results.
 * 
 * @see ArrayList
 * @return data		An ArrayList of strings that contains the results obtained after running the application.
 * 
 */
		public ArrayList<String> getString() {
			
			return data;
		}
	
}
