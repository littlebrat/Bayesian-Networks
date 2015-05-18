package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JFileChooser;


/**
 * The class SwingApplication extends JFrame and it's the "front-page" of our GUI. The user can interact with this menu of the GUI through JButtons and JFileChoosers that allow him 
 * to run the program in a more user-friendly interface. This menu is divided into 3 big sections, the file/parameter(s) selection, the run and save operations and the Help/Info frames. The Help and Info frames
 * provide information about the group members, name of the project and brief instructions on how to navigate in the GUI. The user needs to define the files and the parameters before running the application. The run and save operations
 * run the program and besides showing the results, they also allow the user the option to save the results into a text file.
 * 
 * @author Nuno Mendes
 * @author Sofia Silva
 * @author Tiago Ricardo
 * 
 */

	@SuppressWarnings("serial")
	public class SwingApplication extends JFrame{
		
		private JButton trainbutton;
		private JButton testbutton;
		private JButton runbutton;
		private JButton savebutton;
		private JButton helpbutton;
		private JButton infobutton;
		private JButton parameterbutton;
		JFileChooser trainFile;
		JFileChooser testFile;
		JFileChooser resultsFile;
		String randomrest;
		String score;
		String var;
		String trainurl;
		String testurl;
		String[] input;
		ArrayList<String> data;
		Commands cmd;
		
/**
 *	Besides initializing the necessary fields and attributes, this constructor defines all the actions for the several JButtons through the callback mechanism addActionListener. If the user closes the application, it immediately exits the program. 
 * 	The trainbutton and testbutton open the JFileChooser for both train file and test file, respectively. The helpbutton and infobutton open new frames that provide relevant information to the user. The parameterbutton opens
 * 	a new window that allows the user to select the parameters for the program. This parameters are stored in the fields built specifically for that purpose. The runbutton runs the program with the parameters picked by the user while
 * 	the savebutton's main function is to record the program results into a text file.
 *
 * @see JButton
 * @see JFileChooser
 * @see ArrayList
 * 
 * 
 */
			public SwingApplication(){ 
				trainFile=new JFileChooser();
				testFile= new JFileChooser();
				resultsFile=new JFileChooser();
				randomrest=new String();
				score=new String();
				var=new String();
				testurl=new String();
				trainurl=new String();
				input=new String[5];
				data=new ArrayList<String> ();
				
				
				setTitle("Learning Dynamic Bayesian Networks");
			    setSize(1000,550);
			    setLocationRelativeTo(null);
			    setDefaultCloseOperation(EXIT_ON_CLOSE);
			    setVisible(true);
			   
			    setLayout(new BorderLayout());
			    URL imgURL = SwingApplication.class.getResource("newInterfaceData.png");
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
							}		
							
						});
						
						option.var.addActionListener(new ActionListener(){

							@Override
							public void actionPerformed(ActionEvent arg0) {
								var=option.getVar();
							}
							
						});
						

						option.score.addActionListener(new ActionListener(){

							@Override
							public void actionPerformed(ActionEvent arg0) {
								score=option.getScore();
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
														
						int encontrouVal = resultsFile.showSaveDialog(resultsFile);
						if (encontrouVal == JFileChooser.APPROVE_OPTION) {
								File f = resultsFile.getSelectedFile();
								String resultsURL= f.getPath();
								
								try {
									printFile(input,resultsURL,data);
									PopUpMessage saved=new PopUpMessage("DataSaved.png");
									saved.setResizable(false);
								}catch(FileNotFoundException e1){
									System.err.println("File not Found");
								}catch(UnsupportedEncodingException e2){
									System.err.println("Encoding not Supported");
								}
						}	
				}	
			});
				runbutton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						input[0]=trainurl;
						input[1]=testurl;
						input[2]=score;
						input[3]=randomrest;
						input[4]=var;
						
						
						cmd = new Commands(input);
						cmd.setResizable(false);
						data=cmd.getString();
						}
					
				});
		}
	/**
	 *	The printFile method writes the results obtained after running the programming into a text file chosen by the user.
	 *
	 * @throws FileNotFoundException	 
	 * @throws UnsupportedEncodingException
	 * @see ArrayList
	 * 
	 */
			public void printFile(String[] input,String url,ArrayList<String> data) throws FileNotFoundException, UnsupportedEncodingException{
				PrintWriter file = new PrintWriter(url, "UTF-8");
				String dbn=data.get(0);
				String sbn=data.get(1);
				String s=data.get(2);
				String time1=data.get(3);
				String time2=data.get(4);
				file.println("Dynamic Bayesian Networks\n");
				file.println("Train File: "+input[0]+"\n");
				file.println("Test File: "+input[1]+"\n" );
				file.println("Score: "+input[2]+"\n");
				file.println("Random Restarts: "+input[3]+"\n");
				if(input[4]=="0"){
					file.println("Variable: ALL\n");
				}else{
					file.println("Variable: "+input[4]+"\n");
				}
				file.println("Building DBN: "+ time1+" time\n");
				file.println("Initial Network:\n");
				file.println(sbn);
				file.println("Transition Network:\n");
				file.println(dbn);
				file.println("Performing inference:\n");
				file.println(s);
				file.println("Inferring with the DBN:" +time2+" time");
				file.close();
				
			}
			
	}
	