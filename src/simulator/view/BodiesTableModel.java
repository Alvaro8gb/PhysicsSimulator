package simulator.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class BodiesTableModel extends AbstractTableModel implements SimulatorObserver {
// ...
	private List<Body> _bodies;
	private final int numberOfCols = 5;
	
	BodiesTableModel(Controller ctrl) {
	_bodies = new ArrayList<>();
	ctrl.addObserver(this);
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
	 
		switch(column) {
		
		case 0 : return "id";
		case 1 : return "Mass";
		case 2 : return "Position";
		case 3 : return "Velocity";
		case 4 : return "Force";
		
		}
		
		return null;
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
	 return null;
	}
	
	
	// SimulatorObserver methods
	// ...
	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String fLawsDesc) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onReset(List<Body> bodies, double time, double dt, String fLawsDesc) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onAdvance(List<Body> bodies, double time) {
		// TODO Auto-generated method stub
		
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
