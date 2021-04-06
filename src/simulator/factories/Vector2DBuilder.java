package simulator.factories;

import org.json.JSONArray;

import simulator.misc.Vector2D;


public class Vector2DBuilder {

	public Vector2D createTheVector(JSONArray data) {
		
		if(data.length() !=2) throw new IllegalArgumentException("Not 2D Vector");
		return new Vector2D (data.getDouble(0),data.getDouble(1));

	}
	
	protected JSONArray getInfo() {
	
		JSONArray data = new JSONArray();
		
		data.put("the coor x");
		data.put("the coor y");
		
		return data;
	}
}
