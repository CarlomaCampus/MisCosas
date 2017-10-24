import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class ListenerBotones implements ActionListener {

	String accion;
	JTextField numero;
	JLabel operacion;

	public ListenerBotones(String accion, JTextField numero, JLabel operacion) {

		this.accion = accion;
		this.numero = numero;
		this.operacion = operacion;

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String numerobuff = numero.getText(); // para ahorrar hacer un get cada vez, ya que no va a variar hasta después del switch
		boolean error = false;

		
		if(accion.equals("C")) numero.setText("");
		else if (accion.equals("CE")) {
			
			numero.setText("");
			operacion.setText("");
			ValoresGuardados.borrar=false;
			ValoresGuardados.numeroanterior = "0";
			ValoresGuardados.operacion = "SUMA";
		
		
		} else {
		
		
		try {
			Double.parseDouble(numerobuff); //casos especiales, "" que es "0" y "." que es "0."
		} catch (Exception f) {
			
			if (!numerobuff.equals("") && !numerobuff.equals(".")) {
				
				error = true;
				numero.setText("No válido. Pulse CE.");
				
			} else if (numerobuff.equals(".") && (!accion.equals(".") && !accion.equals("+") && !accion.equals("-") && !accion.equals("*") && !accion.equals("/") && !accion.equals("="))) {
				
				error = true;
				numero.setText("0."+accion);
				
			} else if (numerobuff.equals(".")) {
				
				error = true;
				numero.setText(".");
				
			}

		}
		
		if(!error) {
		switch (accion) {
		
		case ".":
		case "0":
		case "1":
		case "2":
		case "3":
		case "4":
		case "5":
		case "6":
		case "7":
		case "8":
		case "9":  if (ValoresGuardados.borrar) { //si la última acción ha sido una operación (el método operar cambia este valor)
		numerobuff = "";
		ValoresGuardados.borrar = false;
		}
		numero.setText(numerobuff+accion);
		break;
		
		case "+": operar(numerobuff, numero); ValoresGuardados.operacion = "SUMA"; operacion.setText("+"); break;
		case "-": operar(numerobuff, numero); ValoresGuardados.operacion = "RESTA"; operacion.setText("-"); break;
		case "*": operar(numerobuff, numero); ValoresGuardados.operacion = "MULTIPLICACION"; operacion.setText("*"); break;
		case "/": operar(numerobuff, numero); ValoresGuardados.operacion = "DIVISION"; operacion.setText("/"); break;
		case "=": operar(numerobuff, numero); ValoresGuardados.operacion = "IGUAL A"; operacion.setText(""); break;
		
		}
		
		numerobuff = numero.getText(); // refrescamos el buff
		

		}
	
		try { Double.parseDouble(numerobuff); } catch (Exception f) { if (!numerobuff.equals("") && !numerobuff.equals(".")) numero.setText("No válido. Pulse CE."); }
		}	
	
	}
	
	
	void operar(String numerobuff, JTextField numero) {
		
		if (!numerobuff.equals("")) {
		switch(ValoresGuardados.operacion) {
	
		case "SUMA": numerobuff = Double.parseDouble(ValoresGuardados.numeroanterior)+(Double.parseDouble(numerobuff))+""; break;
		case "RESTA": numerobuff = Double.parseDouble(ValoresGuardados.numeroanterior)-(Double.parseDouble(numerobuff))+"";  break;
		case "MULTIPLICACION":  numerobuff = Double.parseDouble(ValoresGuardados.numeroanterior)*(Double.parseDouble(numerobuff))+"";  break;
		case "DIVISION": numerobuff = Double.parseDouble(ValoresGuardados.numeroanterior)/(Double.parseDouble(numerobuff))+"";  break;
		case "IGUAL A": operacion.setText(""); break;
		
		}
		
		if(Double.parseDouble(numerobuff) % 1 == 0) numero.setText((int)Double.parseDouble(numerobuff)+"");
		else {numero.setText(numerobuff);}
		
		
		ValoresGuardados.numeroanterior = numerobuff;
		
		} else {
			
		ValoresGuardados.numeroanterior = "0";	
		
		}
		
		ValoresGuardados.borrar = true;
		
		
	}
	

}
