package simulator.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.NoForce;
import simulator.model.PhysicsSimulator;

public class BodiesTable extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JTable table;
	private BodiesTableModel btm;
	
	BodiesTable(Controller ctrl) {
	
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder( BorderFactory.createLineBorder(Color.black, 2),"Bodies",TitledBorder.LEFT, TitledBorder.TOP));
		btm = new BodiesTableModel(ctrl);

		init();
		
	
	}
	private void init() {

		table = new JTable(btm);
		
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();

		JTableHeader header = table.getTableHeader();
		header.setDefaultRenderer(tcr);
		
		add(new JScrollPane(table));
		
	}
}
