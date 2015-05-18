package gui;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * The class PopUpMessage extends JFrame and it's a window that is meant to give some feedback to the user about a specific task. In this project, this class is used to inform the user that the data was saved into the text file.
 * 
 * @author Nuno Mendes
 * @author Sofia Silva
 * @author Tiago Ricardo
 * 
 */
@SuppressWarnings("serial")
public class PopUpMessage extends JFrame{
	public PopUpMessage(String url){
		setTitle("Message");
	    setSize(192,70);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(HIDE_ON_CLOSE);
	    setVisible(true);
	    setLayout(new BorderLayout());
	    java.net.URL imgURL = PopUpHelp.class.getResource(url);
	    ImageIcon fundo = new ImageIcon(imgURL);
	    JLabel background=new JLabel(fundo);
	    add(background);
	    background.setLayout(null);
		
	}
}
