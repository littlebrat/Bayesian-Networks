package gui;

import java.awt.BorderLayout;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
 * 	The index of the random variable from which the user wants to know the most probable value is defined in the textVar field while the number of maximum random restarts is defined inside the textRandRest field.
 * 	By pressing the JButtons randomrest,var and score, the user is saving the parameters inside variables that can be used to run application.
 * 	Since there are only two available types of scores, two JRadioButtons and they are grouped into a ButtonGroup in order to keep active one of the buttons at a time.
 *
 * @see JButton
 * @see JTextField
 * @see JRadioButton
 * @see ButtonGroup
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
	
/**
 *	The method returns a string with the value defined in the textRandRest field.
 *
 * @see JTextField
 * @return	rr		The maximum number of random restarts.
 */
		
	String getRR(){
		String rr=new String();
		rr=textRandRest.getText();
		if(rr==null){
			rr="0";
		}
		return rr;
		
	}

	/**
	 *	The method returns a string with the value defined for the index of the random variable.
	 *	If no value was selected, all the random variables are considered.
	 * @see JTextField
	 * @return	var		The index of the random variable.
	 */
	
	String getVar(){
		String var=new String();
		var=textVar.getText();
		if(var.isEmpty()==true){
			var="0";
		}
		return var;
	}
	
	
	/**
	 *	The method returns a string with the type of Score defined with the JRadioButtons.
	 * @see JRadioButton
	 * @return	score		The type of score.
	 */
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
