import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;


public class PopUp extends JFrame{
	
	private JTextField textnodes;//nu'mero de no's
	private JTextField textIn;//no' de origem
	private JTextField textOut;//no' de destino
	JButton save1;//cria o grafo com o nu'mero de no's
	JButton save2;//adiciona ao grafo a aresta entre os no's introduzidos
	JLabel inLCD;//etiquetas que indicam onde colocar o no' de origem e destino
	JLabel outLCD;
	GraphComp grf = new GraphCompImp();//inicializa-se um grafo vazio como uma variavel global
	
	public PopUp()
    {
	setTitle("More Options");
    setSize(500,300);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(HIDE_ON_CLOSE);
    setVisible(true);
    setLayout(new BorderLayout());
    java.net.URL imgURL = PopUp.class.getResource("resources/Pop-Up Panel.png");
    ImageIcon fundo = new ImageIcon(imgURL);
    JLabel background=new JLabel(fundo);
    add(background);
    background.setLayout(null);
    
   
	textnodes = new JTextField();
    textnodes.setBounds(160, 74, 40, 40);
	background.add(textnodes);
	
	textIn = new JTextField();
	textIn.setText("");
    textIn.setBounds(160, 195, 40, 40);
	background.add(textIn);
	
	inLCD = new JLabel("In");
	inLCD.setText("IN");
	inLCD.setBounds(210, 195, 40, 40);
	background.add(inLCD);
	
	outLCD = new JLabel("Out");
	outLCD.setText("OUT");
	outLCD.setBounds(290, 195, 40, 40);
	background.add(outLCD);
	
	textOut = new JTextField();
	textOut.setText("");
    textOut.setBounds(240, 195, 40, 40);
	background.add(textOut);
	
	save1=new JButton("");
	save1.setBounds(395,72,48,49);
	save1.setOpaque(false);
	save1.setContentAreaFilled(false);
	save1.setBorderPainted(false);
	background.add(save1);
	
	save2=new JButton("");
	save2.setBounds(395,195,48,49);
	save2.setOpaque(false);
	save2.setContentAreaFilled(false);
	save2.setBorderPainted(false);
	background.add(save2);
    
	save1.addActionListener(new ActionListener() {//accao para fazer o grafo com o nu'mero de no's
		public void actionPerformed(ActionEvent e) {
		int nodes=Integer.parseInt(textnodes.getText());//invoca o nu'mero de no's inserido pelo utilizador
		grf.grafoo(nodes);//cria um grafo com o nu'mero de no's sem arestas
		grf.showLinks();//mostra o grafo construido
		JOptionPane saveNodes = new JOptionPane();
		JOptionPane.showMessageDialog(saveNodes, "Your graph has " + nodes+" nodes. Press OK to close the dialog window.");
		
		}
	});
	
	
	save2.addActionListener(new ActionListener() {//accao para adicionar uma ou mais arestas
		public void actionPerformed(ActionEvent e) {
			
			int in=Integer.parseInt(textIn.getText());//invoca o no' de origem
			int out=Integer.parseInt(textOut.getText());//invoca o no' de destino
			grf.add_edge(in,out);//adiciona a aresta entre os no's definidos
			grf.showLinks();
			JOptionPane saveNodes = new JOptionPane();
			JOptionPane.showMessageDialog(saveNodes, "You added a line from node " + in+" to node "+out+". Press OK to close the dialog window.");

		}
	});
	
    setSize(500,299);
	setSize(500,300);
    }
}
