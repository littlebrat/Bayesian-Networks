package utils;

import java.awt.BorderLayout;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class PopUpOptions extends JFrame{
	private JTextField textRandRest;
	private JTextField textVar;
	
	JButton randomrest;
	JButton var;
	JButton score;
	JRadioButton scoreLL;
	JRadioButton scoreMDL;
	ButtonGroup scoregroup;
	
	
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
