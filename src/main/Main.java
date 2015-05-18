package main;

import java.io.IOException;

import bayes.BayesDyn;
import bayes.BayesStatic;
import bayes.BayesianNetwork;
import data.DataTest;
import data.DataTrain;
import utils.Input;
import utils.SwingApplication;
import utils.WrongFileExtension;
import utils.WrongInput;
import utils.WrongScoreType;


public class Main {

	public static void main(String[] args){
		if(args[0].equals("-gui")==true){
			SwingApplication app=new SwingApplication();
			app.setResizable(false);
		}else{
			
				try{
					Input in = new Input(args);
					DataTrain dtrain=new DataTrain(in.getTrain());
					DataTest dtest=new DataTest(in.getTest());
					String score = new String();
					
					score=in.getScore();
					int randomrest=in.getRandtest();
					int var=in.getVar();
					double timetobuild = System.currentTimeMillis();
					double time1;
					double time2;
					System.out.println("Parameters:	"+in.getTrain()+" "+in.getTest()+" "+score+" "+randomrest+" "+var+"\n");
					
					
					BayesianNetwork dbn = new BayesDyn(dtrain.get(),score,dtrain.getNames(),100);
					dbn.setRestarts(randomrest);
					dbn.greedyHill();
					time1=(System.currentTimeMillis()-timetobuild)/1000;
					BayesianNetwork sbn = new BayesStatic(dtrain.getInitData(args[0]),score,dtrain.getNames(),100);
					sbn.setRestarts(randomrest);
					sbn.greedyHill();
					
					
					System.out.println("Building DBN: 	"+time1+" time\n");
					System.out.println("Initial network:\n");
					System.out.println(sbn);
					System.out.println("Transition network:\n");
					System.out.println(dbn);
					String s=new String();
					System.out.println("Performing inference:\n");
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
					System.out.println(s);
					System.out.println("Inferring with the DBN:" +time2+" time");
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
}
