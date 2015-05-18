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

@SuppressWarnings("serial")
public class Commands extends JFrame{
	JTextArea cmd;
	JScrollPane scroll;
	JFrame f;
	protected BayesianNetwork dbn=null;
	protected BayesianNetwork sbn;
	protected double time1;
	protected double time2;
	protected ArrayList<String> dados;
	
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
				dados=new ArrayList<String> ();
			
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
				dados=save(dbn,sbn,s,time1,time2);
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
		
		public BayesianNetwork getDBN(){
			return dbn;
		}
		
		public ArrayList<String> save(BayesianNetwork dbn,BayesianNetwork sbn, String s,double time1,double time2) {
			ArrayList<String> dados= new ArrayList<String>();
			dados.add(dbn.toString());
			dados.add(sbn.toString());
			dados.add(s);
			dados.add(Double.toString(time1));
			dados.add(Double.toString(time2));
			return dados;
			
		}

		public ArrayList<String> getString() {
			
			return dados;
		}
	
}
