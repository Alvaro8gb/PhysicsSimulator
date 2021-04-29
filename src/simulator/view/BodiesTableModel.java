package simulator.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class BodiesTableModel extends AbstractTableModel implements SimulatorObserver {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// ...
	private List<Body> _bodies;
	private final int numberOfCols = 5;
	private final String[] columnNames;
	
	BodiesTableModel(Controller ctrl) {
	_bodies = new ArrayList<>();
	ctrl.addObserver(this);
	this.columnNames=new String[]{"Id","Mass","Position","Velocity","Force"};
	}
	@Override
	public int getRowCount() {
	 return _bodies.size();
	}
	@Override
	public int getColumnCount() {
	 return numberOfCols;
	}
	@Override
	public String getColumnName(int column) {
	 
		return columnNames[column];
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
			Object value = null;
			Body b = _bodies.get(rowIndex);
			switch(columnIndex) {
			case 0:
				value = b.getId();
				break;
			case 1:
				value = b.getMass();;
				break;
			case 2:
				value = b.getPosition();
				break;
			case 3:
				value = b.getVelocity();
				break;
			case 4:
				value = b.getForce();
				break;

			}
		return value;
	}
	
	
	// SimulatorObserver methods
	// ...
	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String fLawsDesc) {
		// TODO Auto-generated method stub
		this._bodies = bodies;
		fireTableStructureChanged();
	}
	@Override
	public void onReset(List<Body> bodies, double time, double dt, String fLawsDesc) {
		// TODO Auto-generated method stub
		this._bodies = bodies;
		fireTableStructureChanged();
	}
	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {
		// TODO Auto-generated method stub
		this._bodies = bodies;
		fireTableStructureChanged();
	}
	@Override
	public void onAdvance(List<Body> bodies, double time) {
		// TODO Auto-generated method stub
		this._bodies = bodies;
		fireTableStructureChanged();
	}
	@Override
	public void onDeltaTimeChanged(double dt) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onForceLawsChanged(String fLawsDesc) {
		// TODO Auto-generated method stub
		
	}
}
