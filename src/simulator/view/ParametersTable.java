package simulator.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import org.json.JSONObject;


public class ParametersTable extends JPanel {


	private static final long serialVersionUID = 1L;
	private ParametersTableModel tableModel;
	private JTable table;

	ParametersTable(JSONObject lawInfo) {
		
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder( BorderFactory.createLineBorder(Color.BLUE, 2)));
		tableModel = new ParametersTableModel(lawInfo) {
			@Override
			public boolean isCellEditable(int rowIndex,int colIndex) {
				return colIndex == 1;
			}
		};
		//setSize(500,200);
		init();
		
	
	}
	private void init() {
		
		table = new JTable(tableModel);
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();

		JTableHeader header = table.getTableHeader();
		header.setDefaultRenderer(tcr);
		add(new JScrollPane(table));
		
	}
	public void setObj(JSONObject ob) {
		tableModel.setObj(ob);
	}

}
