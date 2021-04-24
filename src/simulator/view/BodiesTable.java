package simulator.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import simulator.control.Controller;
import simulator.model.NoForce;
import simulator.model.PhysicsSimulator;

public class BodiesTable extends JPanel {
	
	private static final long serialVersionUID = 1L;
	//tabla
	private JTable tabla;
	//modelo de la tabla
	private BodiesTableModel btm;
	
	BodiesTable(Controller ctrl) {
	
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder( BorderFactory.createLineBorder(Color.black, 2),"Bodies",TitledBorder.LEFT, TitledBorder.TOP));
		btm = new BodiesTableModel(ctrl);

		init();
		
	
	}
	private void init() {
		//tabla
		tabla = new JTable(btm);
		add(new JScrollPane(tabla));
		
	}
	 public static void main(String args[]) {

		  JFrame j = new JFrame();
		  j.add( new BodiesTable(new Controller(new PhysicsSimulator(20,new NoForce()),null,null)));
		  j.setVisible(true);
		  j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   }
}
