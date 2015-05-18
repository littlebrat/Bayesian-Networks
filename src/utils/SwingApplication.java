package utils;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JFileChooser;


	@SuppressWarnings("serial")
	public class SwingApplication extends JFrame{
		
		private JButton trainbutton;
		private JButton testbutton;
		private JButton runbutton;
		private JButton savebutton;
		private JButton helpbutton;
		private JButton infobutton;
		private JButton parameterbutton;
		@SuppressWarnings("unused")
		private Commands cmd;
		JFileChooser trainFile;
		JFileChooser testFile;
		JFileChooser resultsFile;
		String randomrest;
		String score;
		String var;
		String trainurl;
		String testurl;
		String[] input;
		
		
			public SwingApplication() { 
				trainFile=new JFileChooser();
				testFile= new JFileChooser();
				resultsFile=new JFileChooser();
				randomrest=new String();
				score=new String();
				var=new String();
				testurl=new String();
				trainurl=new String();
				input=new String[5];
				
				setTitle("Learning Dynamic Bayesian Networks");
			    setSize(1000,550);
			    setLocationRelativeTo(null);
			    setDefaultCloseOperation(EXIT_ON_CLOSE);
			    setVisible(true);
			   
			    setLayout(new BorderLayout());
			    URL imgURL = SwingApplication.class.getResource("newInterfaceData.png");
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
				
				parameterbutton=new JButton("");
				parameterbutton.setBounds(74,287,200,50);
				parameterbutton.setOpaque(true);
				parameterbutton.setContentAreaFilled(false);
				parameterbutton.setBorderPainted(false);
				background.add(parameterbutton);
				
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
						int encontrouVal = trainFile.showOpenDialog(trainFile);
						if (encontrouVal == JFileChooser.APPROVE_OPTION) {
							File file = trainFile.getSelectedFile();
							trainurl= file.getPath();
							
						}			
						}});
				
				testbutton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						testFile.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
						int encontrouVal = testFile.showOpenDialog(testFile);
						if (encontrouVal == JFileChooser.APPROVE_OPTION) {
							File file = testFile.getSelectedFile();
							testurl= file.getPath();
							
						}			
						}});
				
				parameterbutton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						final PopUpOptions option = new PopUpOptions();
						option.setResizable(false);
						option.randomrest.addActionListener(new ActionListener(){

							@Override
							public void actionPerformed(ActionEvent arg0) {
								randomrest=option.getRR();
								System.out.println("RandomRest: "+randomrest);
							}		
							
						});
						
						option.var.addActionListener(new ActionListener(){

							@Override
							public void actionPerformed(ActionEvent arg0) {
								var=option.getVar();
								System.out.println("Var: "+var);
							}
							
						});
						

						option.score.addActionListener(new ActionListener(){

							@Override
							public void actionPerformed(ActionEvent arg0) {
								score=option.getScore();
								System.out.println("Score: "+score);
							}
							
							
						});
						
						
					}
				});
				
				
				
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
			
				savebutton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					input[0]=trainurl;
					input[1]=testurl;
					input[2]=score;
					input[3]=randomrest;
					input[4]=var;
				}	
			});
				runbutton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						int encontrouVal = resultsFile.showSaveDialog(resultsFile);//abre uma janela para escolher o directorio onde vai guardar o ficheiro
							if (encontrouVal == JFileChooser.APPROVE_OPTION) {
									File f = resultsFile.getSelectedFile();
									String resultsURL= f.getPath();
									System.out.println(resultsURL);
									Commands cmd = new Commands(input,resultsURL);
									cmd.setResizable(false);
							}
						}
					
			
				});
		}
			
	}
	