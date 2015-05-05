package data;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		int range=0;
		int i;
		int[][] vect;
		int[][] vecttest;
		// TODO Auto-generated method stub
		
		String url=args[0];
		String urltest=args[1];
		DataTrain d= new DataTrain(url);
		DataTest dtest= new DataTest(urltest);
		vect= new int[d.size][d.num_va];
		vecttest= new int[dtest.size][dtest.num_va];
		vect=d.get();
		vecttest=dtest.get();
		for(i=0;i<3;i++){
			range=d.getcfg(i);
			System.out.println("range["+(i+1)+"]:" + range);
		}	
		for(i=0; i<d.size;i++){
				System.out.println("DataTrain(t)["+i+"]:"+vect[i][0]+vect[i][1]+vect[i][2]+"/// DataTrain(t+1):"+vect[i][3]+vect[i][4]+vect[i][5]);	
		}
		for(i=0; i<dtest.size;i++){
			System.out.println("DataTest(t)["+i+"]:"+vecttest[i][0]+vecttest[i][1]+vecttest[i][2]);	
	}
	}

}
