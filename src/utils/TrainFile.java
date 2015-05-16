package utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;

import data.DataTrain;

public class TrainFile implements ActionListener {
	JFileChooser trainFile;
	@Override
	public void actionPerformed(ActionEvent e) {
		
		trainFile.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int encontrouVal = trainFile.showOpenDialog(trainFile);//abre uma janela para escolher o diretorio para abrir o ficheiro
		if (encontrouVal == JFileChooser.APPROVE_OPTION) {//ve se o utilizador indicou um diretorio
			File file = trainFile.getSelectedFile();//criar um objecto com o ficheiro seleccionado
			String fileURL= file.getPath();
			try {
				int[][] vect;
				DataTrain d= new DataTrain(fileURL);
				vect= new int[d.size][d.num_va];
				vect=d.get();
				for(int i=0; i<d.size;i++){
					System.out.println("DataTrain(t)["+i+"]:"+vect[i][0]+vect[i][1]+vect[i][2]+"/// DataTrain(t+1):"+vect[i][3]+vect[i][4]+vect[i][5]);	
			}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
	}			
	
	
	}
}