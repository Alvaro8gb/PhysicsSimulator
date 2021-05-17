package simulator.view;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.table.AbstractTableModel;

import org.json.JSONArray;
import org.json.JSONObject;

public class ParametersTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private JSONObject _lawInfo;
	private final int numberOfCols = 3;
	private final String[] columnNames;
	private List<Object> values;
	private List<String> keys;
	
	ParametersTableModel(JSONObject lawInfo) {
		_lawInfo = lawInfo;
	   columnNames = new String[]{"Key","Value","Description"};
	   keys = getKeys();
	   values = new ArrayList<Object>();
	   setValuesList();
	  
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
			value = values.get(rowIndex);
			break;
		case 2:
			value = _lawInfo.getJSONObject("data").getString(s);
			break;

		}
	return value;
	}
	@Override
	public boolean isCellEditable(int rowIndex,int colIndex) {
		return colIndex == 1;
	}
	@Override
	public void setValueAt(Object value, int row, int col) {
		
		values.set(row, value);
		
		fireTableDataChanged();
	}
	public void setLawInfo(JSONObject ob) {
		_lawInfo = ob;
		keys = getKeys();
		
		setValuesList();
	}
	private void setValuesList() {
		
	  values.clear();
	  for(int i = 0 ; i< keys.size(); i++) values.add(null);
	  fireTableDataChanged();
	}
	public JSONObject createData() {

		JSONObject obj = new JSONObject();
		for(int i = 0; i < keys.size();i++) {
			if(keys.get(i) != null) obj.put(keys.get(i), parseObject (keys.get(i), values.get(i)) );
		}
		
		return obj;
	}
	private Object parseObject(String key,Object value) {
		
		String cad = value.toString();
		
		if(cad.equals("")) return null;
		else {
			if(key.equals("c") && value!= null ) return parseKeyC(cad);
			else return value;
		}
		
	}
	private JSONArray parseKeyC(String cad) {
		
		
		JSONArray array = new JSONArray();
		
		String [] arrayCad = cad.split(",");

		if(arrayCad.length > 3) throw new IllegalArgumentException("Error to recognise : " + cad + "many commas");
		
		try {
			array.put( Double.parseDouble(eliminarCorchete(arrayCad[0])));
			array.put( Double.parseDouble(eliminarCorchete(arrayCad[1])));
			
		}catch(Exception e) {
			 throw new IllegalArgumentException("Error to recognise : " + cad + "\n"+ e.getMessage());
		}
			
		return array;
			
		
	}
	
	private String eliminarCorchete(String cad) {
		
		String nuevaCad = "";
		for(int i = 0; i< cad.length();i++) {
			if(cad.charAt(i) != '[' && cad.charAt(i) != ']')  nuevaCad+= cad.charAt(i);
			
		}
		
		return nuevaCad;
		
	}
	private List<String> getKeys() {
		List<String> x = new ArrayList<>();
		Iterator<String> it = _lawInfo.getJSONObject("data").keySet().iterator();
		while( it.hasNext()) { 
		    String s = (String) it.next();
		    x.add(s);
		  }
		
		return x;
	}

}
