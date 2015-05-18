package gui;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

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
