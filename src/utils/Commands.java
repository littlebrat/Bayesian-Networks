package utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import bayes.BayesDyn;
import bayes.BayesStatic;
import bayes.BayesianNetwork;
import data.DataTest;
import data.DataTrain;

@SuppressWarnings("serial")
public class Commands extends JFrame{
	JTextArea terminal;
	JScrollPane scroll;
	JFrame f;
		public Commands(String[] input,String url){
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
				int randomrest=in.getRandtest();
				int var=in.getVar();
				double timetobuild = System.currentTimeMillis();
				double time1;
				double time2;
				PrintWriter file = new PrintWriter(url, "UTF-8");
				cmd.append("Learning...\n");
				cmd.append("Train File:	"+in.getTrain()+"\n"+"Test File:	"+in.getTest()+"\n"+"Score:	"+score+"\n"+"Random Restarts:	"+randomrest+"\n"+"Variable Index:	"+var+"\n");
				BayesianNetwork dbn = new BayesDyn(dtrain.get(),dtest.get(),score,dtrain.getNames(),100);
				dbn.setRestarts(randomrest);
				dbn.greedyHill();
				time1=(System.currentTimeMillis()-timetobuild)/1000;
				BayesianNetwork sbn = new BayesStatic(dtrain.get(),dtest.get(),score,dtrain.getNames(),100);
				sbn.setRestarts(randomrest);
				sbn.greedyHill();
				
				
				cmd.append("Building DBN: 	"+time1+" time\n");
				cmd.append("Initial network:\n");
				cmd.append(sbn.toString());
				cmd.append("Transition network:\n");
				file.write(dbn.toString());
				file.close();
				cmd.append(dbn.toString());
				String s=new String();
				cmd.append("Performing inference:\n");
				//var specified
				if(var!=0){
					timetobuild=System.currentTimeMillis();
					int[] pred = dbn.getPredictions(var);
					time2=(System.currentTimeMillis()-timetobuild)/1000;
					for(int i=0; i<pred.length;i++){
						s+= "->instance "+i+":	"+pred[i]+"\n";
					}
				}else{
					timetobuild=System.currentTimeMillis();
					//var not specified
					int[][]pred=dbn.getAllPredictions();
					time2=(System.currentTimeMillis()-timetobuild)/1000;
					for(int i=0; i<pred.length;i++){
						s+= "->instance "+i+":	";
						for(int j=0;j<dtrain.num_va;j++){
							s+=pred[i][j]+",";
						}
						s=s.substring(0,s.length()-1);
						s+="\n";
					}
					
				}
				cmd.append(s);
				cmd.append("Inferring with the DBN:" +time2+" time");
				
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
	
}
