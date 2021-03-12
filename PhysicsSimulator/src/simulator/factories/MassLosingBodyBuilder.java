package simulator.factories;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.MassLossingBody;

public class MassLosingBodyBuilder extends Builder <MassLossingBody>{

	public MassLosingBodyBuilder() {
		_type ="mlb";
		_desc = "esto es un cuerpo que pierde masa";
	}
	
	public MassLossingBody createTheInstance(JSONObject data) {
		
		Vector2D p = createVector(data.getJSONArray("p"));
		Vector2D v = createVector(data.getJSONArray("v"));
		double m = data.getDouble("m");
		String id = data.getString("id");
		double freq = data.getDouble("freq");
		double factor = data.getDouble("factor");
		
		return new MassLossingBody(id,p,v,m,freq,factor);
	}
	private Vector2D createVector(JSONArray jsonV) {
		return new Vector2D (jsonV.getDouble(0),jsonV.getDouble(1));
	}
	
	public JSONObject createData() {
	
		JSONObject data = new JSONObject();
		
		data.put("id", "the identifier");
		data.put("m", "the mass");
		data.put("v", "the velocity");
		data.put("p", "the position");
		data.put("freq", "the frequency");
		data.put("factor", "the factor");
		
		return data;
	}

	
}
