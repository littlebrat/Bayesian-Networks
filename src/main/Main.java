package main;

import java.io.IOException;

import bayes.BayesDyn;
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
					String[] nomes;
					Input in = new Input(args);
					DataTrain dtrain=new DataTrain(in.getTrain());
					DataTest dtest=new DataTest(in.getTest());
					String score = new String();
					nomes=new String[dtest.num_va];
					for(int i=0;i<dtrain.num_va;i++){
						System.out.println(dtest.getNames(in.getTrain())[i]);
					}
					
					score=in.getScore();
					int randomrest=in.getRandtest();
					int var=in.getVar();
					double timetobuild = System.currentTimeMillis();
					double time1;
					
					System.out.println("Parameters:	"+in.getTrain()+" "+in.getTest()+" "+score+" "+randomrest+" "+var);
					
					
					BayesianNetwork dbn = new BayesDyn(dtrain.get(),dtest.get(),score,dtrain.getNames(in.getTrain()));
					dbn.greedyHill();
					time1=System.currentTimeMillis()-timetobuild;
					
					System.out.println("Building DBN: 	"+time1);
					System.out.println("Initial network:");
					
					System.out.println("Transition network:");
					System.out.println(dbn);
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