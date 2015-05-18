package gui;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * The class PopUpHelp extends JFrame and it's a window that contains a brief instructions manual on how to run the application. 
 * 
 * @author Nuno Mendes
 * @author Sofia Silva
 * @author Tiago Ricardo
 * 
 */
@SuppressWarnings("serial")
public class PopUpHelp extends JFrame{
	public PopUpHelp(){
		setTitle("Help");
	    setSize(1000,520);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(HIDE_ON_CLOSE);
	    setVisible(true);
	    setLayout(new BorderLayout());
	    java.net.URL imgURL = PopUpHelp.class.getResource("helpscreen.png");
	    ImageIcon fundo = new ImageIcon(imgURL);
	    JLabel background=new JLabel(fundo);
	    add(background);
	    background.setLayout(null);
		
	}
}
