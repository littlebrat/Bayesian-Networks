package utils;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JFileChooser;

import data.DataTest;
import data.DataTrain;

	@SuppressWarnings("serial")
	public class SwingApplication extends JFrame{
		
		private JButton trainbutton;
		private JButton testbutton;
		private JButton runbutton;
		private JButton savebutton;
		private JButton helpbutton;
		private JButton infobutton;
		//private JButton parameterbutton;
		JFileChooser trainFile=new JFileChooser();
		JFileChooser testFile= new JFileChooser();
		
			public SwingApplication() { 
				setTitle("Learning Dynamic Bayesian Networks");
			    setSize(2000,500);
			    setLocationRelativeTo(null);
			    setDefaultCloseOperation(EXIT_ON_CLOSE);
			    setVisible(true);
			    setLayout(new BorderLayout());
			    URL imgURL = SwingApplication.class.getResource("InterfaceData.png");
			    System.out.println(imgURL);
			    ImageIcon fundo = new ImageIcon(imgURL);
			    JLabel background=new JLabel(fundo);
			    add(background);
			    background.setLayout(null);
			    
			    trainbutton=new JButton("");
			    trainbutton.setBounds(73,91,200,50);
				trainbutton.setOpaque(true);
				trainbutton.setContentAreaFilled(false);
				trainbutton.setBorderPainted(false);
				background.add(trainbutton);
				
				testbutton=new JButton("");
				testbutton.setBounds(73,192,200,50);
				testbutton.setOpaque(true);
				testbutton.setContentAreaFilled(false);
				testbutton.setBorderPainted(false);
				background.add(testbutton);
				
				runbutton=new JButton("");
				runbutton.setBounds(351,295,100,100);
				runbutton.setOpaque(true);
				runbutton.setContentAreaFilled(false);
				runbutton.setBorderPainted(false);
				background.add(runbutton);
				
				infobutton=new JButton("");
				infobutton.setBounds(22,370,100,100);
				infobutton.setOpaque(true);
				infobutton.setContentAreaFilled(false);
				infobutton.setBorderPainted(false);
				background.add(infobutton);
				
				savebutton=new JButton("");
				savebutton.setBounds(502,295,100,100);
				savebutton.setOpaque(true);
				savebutton.setContentAreaFilled(false);
				savebutton.setBorderPainted(false);
				background.add(savebutton);
				
				helpbutton=new JButton("");
				helpbutton.setBounds(822,370,100,100);
				helpbutton.setOpaque(true);
				helpbutton.setContentAreaFilled(false);
				helpbutton.setBorderPainted(false);
				background.add(helpbutton);
				setSize(950,510);

				
				trainbutton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						trainFile.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
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
						}});
				
				testbutton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						testFile.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
						int encontrouVal = testFile.showOpenDialog(testFile);//abre uma janela para escolher o diretorio para abrir o ficheiro
						if (encontrouVal == JFileChooser.APPROVE_OPTION) {//ve se o utilizador indicou um diretorio
							File file = testFile.getSelectedFile();//criar um objecto com o ficheiro seleccionado
							String fileURL= file.getPath();
							try {
								int[][] vect;
								DataTest d= new DataTest(fileURL);
								vect= new int[d.size][d.num_va];
								vect=d.get();
								for(int i=0; i<d.size;i++){
									System.out.println("DataTest(t)["+i+"]:"+vect[i][0]+vect[i][1]+vect[i][2]);	
							}
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
						}			
						}});
				
				infobutton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						PopUpInfo pop = new PopUpInfo();
						pop.setResizable(false);
					}			
				});
				helpbutton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						PopUpHelp pop = new PopUpHelp();
						pop.setResizable(false);
					}			
				});
		
			}
			
	}
	