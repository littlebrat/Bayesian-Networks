package gui;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


/**
 * The class PopUpOptions extends JFrame and it's a window that contains several fields that allow the user to pick the desired parameters to run the application.
 * 
 * @author Nuno Mendes
 * @author Sofia Silva
 * @author Tiago Ricardo
 * 
 */
@SuppressWarnings("serial")
public class PopUpOptions extends JFrame{
	JTextField textRandRest;
	JTextField textVar;
	
	JButton randomrest;
	JButton var;
	JButton score;
	JRadioButton scoreLL;
	JRadioButton scoreMDL;
	ButtonGroup scoregroup;

/**
 *	Besides initializing the necessary fields and attributes, this constructor defines all the actions for the several JButtons through the callback mechanism addActionListener. 
 * 	The index of the random variable
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
	
	public PopUpOptions(){
		setTitle("Parameters");
	    setSize(500,520);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(HIDE_ON_CLOSE);
	    setVisible(true);
	    setLayout(new BorderLayout());
	    java.net.URL imgURL = PopUpHelp.class.getResource("OptionsPanel.png");
	    ImageIcon fundo = new ImageIcon(imgURL);
	    JLabel background=new JLabel(fundo);
	    add(background);
	    background.setLayout(null);
	    
	    textRandRest = new JTextField();
	    textRandRest.setHorizontalAlignment(JTextField.CENTER);
	    textRandRest.setBounds(70, 150, 60, 40);
		background.add(textRandRest);
		
		textVar = new JTextField();
		textVar.setHorizontalAlignment(JTextField.CENTER);
	    textVar.setBounds(70, 245, 60, 40);
		background.add(textVar);
		
		scoreMDL = new JRadioButton();
		scoreMDL.setBounds(155,327,60,40);
		scoreMDL.setOpaque(false);
		scoreMDL.setBorderPainted(false);
		background.add(scoreMDL);
		
		scoreLL = new JRadioButton("", true);
		scoreLL.setBounds(155,373,60,40);
		scoreLL.setOpaque(false);
		scoreLL.setBorderPainted(false);
		background.add(scoreLL);
		
		ButtonGroup scoregroup = new ButtonGroup();
		scoregroup.add(scoreLL);
		scoregroup.add(scoreMDL);
		
		
		randomrest=new JButton("");
	    randomrest.setBounds(333,143,46,49);
		randomrest.setOpaque(true);
		randomrest.setContentAreaFilled(false);
		randomrest.setBorderPainted(false);
		background.add(randomrest);
		
		
		var=new JButton("");
	    var.setBounds(333,235,46,49);
		var.setOpaque(true);
		var.setContentAreaFilled(false);
		var.setBorderPainted(false);
		background.add(var);
		
		
		
		score=new JButton("");
	    score.setBounds(333,341,46,49);
		score.setOpaque(true);
		score.setContentAreaFilled(false);
		score.setBorderPainted(false);
		background.add(score);
	    
	}
	
	String getRR(){
		String rr=new String();
		rr=textRandRest.getText();
		if(rr==null){
			rr="0";
		}
		return rr;
		
	}
	
	String getVar(){
		String var=new String();
		var=textVar.getText();
		if(var.isEmpty()==true){
			var="0";
		}
		return var;
	}
	
	String getScore(){
		String score=new String();
		if(scoreLL.isSelected()==true){
			score="LL";
		}
		if(scoreMDL.isSelected()==true){
			score="MDL";
		}
		return score;
	}
}
