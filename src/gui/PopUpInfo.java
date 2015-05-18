package gui;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * The class PopUpInfo extends JFrame and it's a window that contains information about the project and the group members. 
 * 
 * @author Nuno Mendes
 * @author Sofia Silva
 * @author Tiago Ricardo
 * 
 */
@SuppressWarnings("serial")
public class PopUpInfo extends JFrame{

	public PopUpInfo(){
		setTitle("Info");
	    setSize(800,500);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(HIDE_ON_CLOSE);
	    setVisible(true);
	    setLayout(new BorderLayout());
	    java.net.URL imgURL = PopUpInfo.class.getResource("homescreen.png");
	    ImageIcon fundo = new ImageIcon(imgURL);
	    JLabel background=new JLabel(fundo);
	    add(background);
	    background.setLayout(null);
		
	}
	
}
