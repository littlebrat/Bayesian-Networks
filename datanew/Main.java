package data;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		DataTrain d= new DataTrain();
		String url=args[0];
		d.readDatas(url);
		System.out.println(d);
	}

}
