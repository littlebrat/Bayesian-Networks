import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.JOptionPane;
import javax.swing.filechooser.*;



public class BackgroundImage extends JFrame{
	JButton options;
	JButton thetaBut;
	JButton csvBut;
	JButton saveBut;
	JButton runBut;
	private MixGauss mist;
	private FileInputStream openFile;//obte'm bytes a partir do ficheiro recebido
	private FileInputStream openFili;
	private FileOutputStream writeFile;//cria um ficheiro output correspondente aos resultados obtidos
	private Sample samp;
	
	final JFileChooser csvFile; 
	final JFileChooser thetaFile; 	
	final JFileChooser saveFile; 
	
	   
	public BackgroundImage()
	    {
	    setTitle("AutoPharma® OctanaMS");
	    setSize(1000,585);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    setVisible(true);
	    setLayout(new BorderLayout());
	    java.net.URL imgURL = BackgroundImage.class.getResource("resources/GUIapp.png");
	    ImageIcon fundo = new ImageIcon(imgURL);
	    JLabel background=new JLabel(fundo);
	    add(background);
	    background.setLayout(null);
	    
	    
		options=new JButton("");
		options.setBounds(97,48,195,57);
		options.setOpaque(false);
		options.setContentAreaFilled(false);
		options.setBorderPainted(false);
		background.add(options);
		
		thetaBut=new JButton("");
		thetaBut.setBounds(97,160,318,287);
		thetaBut.setOpaque(true);
		thetaBut.setContentAreaFilled(false);
		thetaBut.setBorderPainted(false);
		background.add(thetaBut);
		
		csvBut=new JButton("");
		csvBut.setBounds(572,160,318,287);
		csvBut.setOpaque(true);
		csvBut.setContentAreaFilled(false);
		csvBut.setBorderPainted(false);
		background.add(csvBut);
		
		runBut=new JButton("");
		runBut.setBounds(462,231,72,74);
		runBut.setOpaque(true);
		runBut.setContentAreaFilled(false);
		runBut.setBorderPainted(false);
		background.add(runBut);
		
		
		saveBut=new JButton("");
		saveBut.setBounds(458,349,79,78);
		saveBut.setOpaque(true);
		saveBut.setContentAreaFilled(false);
		saveBut.setBorderPainted(false);
		background.add(saveBut);
		
	    setSize(1000,584);
		setSize(1000,585);
		
		options.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PopUp pop = new PopUp();
				pop.setResizable(false);
			}			
		});
		
		csvFile= new JFileChooser();
		csvFile.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		
		
		csvBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int encontrouVal = csvFile.showOpenDialog(csvFile);//abre uma janela para escolher o diretorio para abrir o ficheiro
				if (encontrouVal == JFileChooser.APPROVE_OPTION) {//ve se o utilizador indicou um diretorio
					File file = csvFile.getSelectedFile();//criar um objecto com o ficheiro seleccionado
					String fileURL= file.getPath();
					try {
						samp=new ImpSample();//cria uma amostra vazia
						openFile = new FileInputStream(fileURL);//recebe o ficheiro das amostras
						samp.sampleReader(fileURL);//define a amostra de acordo com o ficheiro recebido
						samp.showSample();//mostra a amostra definida na consola
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
			}			
			}});
		
		thetaFile= new JFileChooser();
		thetaFile.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		
		thetaBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int encontrouVal = thetaFile.showOpenDialog(thetaFile);
				if (encontrouVal == JFileChooser.APPROVE_OPTION) {
					File fil = thetaFile.getSelectedFile();
					String filiURL= fil.getPath();
					try {
						mist=new MixGaussImp();//cria uma mistura de gaussianas vazia
						openFili = new FileInputStream(filiURL);
						mist.mixReader(filiURL);//le os dados recebidos
						mist.mix(mist.aa().size(),mist.aa());//cria a mistura gaussiana
						mist.theta();//devolve o conjunto de parametros theta da mistura gaussiana
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		}});

		runBut.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			
			ProbFunctions calc = new ProbFunctionsImp();
			mist=calc.Algorithm(samp,mist);
			java.awt.Toolkit.getDefaultToolkit().beep();
			JOptionPane ran = new JOptionPane();
			JOptionPane.showMessageDialog(ran, "Your Mix is Ready. Press OK to close this dialog!");
			
			}
		});
		
		saveFile= new JFileChooser();
		saveFile.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		
		saveBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int encontrouVal = saveFile.showSaveDialog(saveFile);//abre uma janela para escolher o directorio onde vai guardar o ficheiro
				if (encontrouVal == JFileChooser.APPROVE_OPTION) {
					File fil = saveFile.getSelectedFile();
					String saveURL= fil.getPath();
					try {
						String source = "Here you have your results for your theta:";
						char buffer[] = new char[source.length()];//declara-se o objecto buffer, que permite interpretar em caracteres
						source.getChars(0, source.length(), buffer, 0);
						FileWriter f0 = new FileWriter(saveURL+".txt"); 
						for (int i=0; i < buffer.length; i++) {   
						      f0.write(buffer[i]);
						    }
						
						
						f0.write(System.lineSeparator());
						source = "                 a1:                 a2:                 b1:                 b2:                 w:                 o2:";
						char gogo[] = new char[source.length()];
						source.getChars(0, source.length(), gogo, 0);
						for (int i=0; i < gogo.length; i++) {   
						      f0.write(gogo[i]);
						    }
						f0.write(System.lineSeparator());
						
						for(int j=0;j<mist.length();j++){
							source="";
							Gauss aux = mist.gau(j);
							source+="gauss: "+j+"    ";
							source+=aux.afirst+"  ";
							source+=aux.asecond+"  ";
							source+=aux.bfirst+"  ";
							source+=aux.bsecond+"  ";
							source+=aux.w+"  ";
							source+=aux.sigma;
							char reee[] = new char[source.length()];
							source.getChars(0, source.length(), reee, 0);
							for (int i=0; i < reee.length; i++) {   
							      f0.write(reee[i]);
							    }
							f0.write(System.lineSeparator());
						}
						
						f0.close();
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}			
		});
		}		
}
