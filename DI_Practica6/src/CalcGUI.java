
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;


import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalcGUI extends JFrame {

	
	public CalcGUI() {
		
		

		setIconImage(new ImageIcon("iconopequeño.png").getImage());
		setBounds(300, 300, 360, 600);
		Color bg = new Color(178, 230, 255);
		JPanel p = new JPanel();
		p.setBorder(BorderFactory.createBevelBorder(0, new Color(130, 188, 255), new Color(35, 137, 255)));
		p.setBackground(bg);
		p.setLayout(new GridBagLayout());
		add(p);
		
		
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(6, 6, 6, 6);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0;


		ImageIcon icono = new ImageIcon("icono.png");
		JLabel calcico = new JLabel(icono);
		
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		p.add(calcico, c);

		
		JTextField numero = new JTextField();
	
		
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.weightx = 1;
		p.add(numero, c);
		c.weightx = 0;
		
		JLabel titulo = new JLabel("<html><font color=#005882 size=6>Calculadora 1.0</font></html>");

		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 4;
		c.gridheight = 1;
		p.add (titulo, c);

		

		JLabel operacion = new JLabel(" ");

		c.gridx = 2;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		p.add (operacion, c);
		
		JButton c1 = new JButton("C");
		c1.setBackground(new Color(162, 193, 216));
		
		c.gridx = 3;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 0;
		
		p.add(c1, c);
		
		JButton ce = new JButton("CE");
		ce.setBackground(new Color(162, 193, 216));
		
		c.gridx = 4;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.weightx = 0;
		
		
		p.add(ce, c);


		JPanel botones = new JPanel();
		botones.setLayout(new GridLayout(4, 4, 3, 3));
		botones.setBackground(bg);
		
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 5;
		c.gridheight = 1;
		
		p.add (botones, c);

		JButton[] botonesarray = new JButton[16];
		
		
		botonesarray[0] = new JButton("7");
		botonesarray[1] = new JButton("8");
		botonesarray[2] = new JButton("9");
		botonesarray[3] = new JButton("/");
		botonesarray[4] = new JButton("4");
		botonesarray[5] = new JButton("5");
		botonesarray[6] = new JButton("6");
		botonesarray[7] = new JButton("*");
		botonesarray[8] = new JButton("1");
		botonesarray[9] = new JButton("2");
		botonesarray[10] = new JButton("3");
		botonesarray[11] = new JButton("-");
		botonesarray[12] = new JButton(".");
		botonesarray[13] = new JButton("0");
		botonesarray[14] = new JButton("=");
		botonesarray[15] = new JButton("+");

		
		for (JButton boton : botonesarray) 
		{
			
		boton.setBackground(new Color(141, 205, 239));
		botones.add(boton);
		boton.addActionListener(new ListenerBotones(boton.getText(), numero, operacion));
		boton.setText("<html><h1>"+boton.getText()+"</h1></html>");
		}

		ce.addActionListener(new ListenerBotones("CE", numero, operacion));
		c1.addActionListener(new ListenerBotones("C", numero, operacion));
		
		
		


		setVisible(true);
		
		
	}
}
