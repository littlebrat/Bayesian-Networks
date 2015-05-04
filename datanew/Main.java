package data;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		int range=0;
		int i;
		// TODO Auto-generated method stub
		DataTrain d= new DataTrain();
		DataTest dtest= new DataTest();
		String url=args[0];
		String urltest=args[1];
		dtest.readData(urltest);
		d.readData(url);
		System.out.println("Data Train t:"+ d.training[0]);
		System.out.println("Data Train t+1:"+d.training[1]);
		for(i=0;i<3;i++){
			range=d.getcfg(i);
			System.out.println("range["+(i+1)+"]:" + range);
		}
		
		System.out.println("Data Test:"+dtest.testing);
	}

}
