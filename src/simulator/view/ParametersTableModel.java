package simulator.view;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.table.AbstractTableModel;

import org.json.JSONObject;

import simulator.model.Body;


public class ParametersTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private JSONObject _lawInfo;
	private final int numberOfCols = 3;
	private final String[] columnNames;
	private final List<String> keys;
	
	ParametersTableModel(JSONObject lawInfo) {
		_lawInfo = lawInfo;
	   columnNames = new String[]{"Key","Value","Description"};
	   keys = getKeys();
	}
	@Override
	public int getRowCount() {
	 return _lawInfo.getJSONObject("data").length();
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
		String s = keys.get(rowIndex);
		switch(columnIndex) {
		case 0:
			value = s;
			break;
		case 1:
			value = "hola";
			break;
		case 2:
			value = _lawInfo.getJSONObject("data").getString(s);
			break;

		}
	return value;
	}
	private List<String> getKeys() {
		List<String> x = new ArrayList<>();
		for( Iterator it = _lawInfo.getJSONObject("data").keySet().iterator(); it.hasNext();) { 
		    String s = (String)it.next();
		    x.add(s);
		  }
		return x;
	}

}
