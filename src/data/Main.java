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
		for(i=0;i<d.num_va;i++){
			range=d.getcfg(i);
			System.out.println("range["+(i+1)+"]:" + range);
		}	
		System.out.println(vect);
		System.out.println(vecttest);
	}

}
