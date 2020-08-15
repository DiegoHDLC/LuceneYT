package vista.paneles;

import javax.swing.JPanel;

import controlador.Coordinador;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;

public class PanelTexto extends JPanel {

	/**
	 * Create the panel.
	 */private Coordinador miCoordinador;
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		

	public void setCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
	}
}
