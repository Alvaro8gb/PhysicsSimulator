package simulator.factories;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.Body;
import simulator.model.MassLossingBody;

public class MassLosingBodyBuilder extends Builder <Body>{

	public MassLosingBodyBuilder() {
		super("mlb","a body who lose mass");
	}
	
	protected MassLossingBody createTheInstance(JSONObject data) {
		
		Vector2D p = createVector(data.getJSONArray("p"));
		Vector2D v = createVector(data.getJSONArray("v"));
		double m = data.getDouble("m");
		String id = data.getString("id");
		double freq = data.getDouble("freq");
		double factor = data.getDouble("factor");
		
		return new MassLossingBody(id,p,v,m,factor,freq);
	}
	private Vector2D createVector(JSONArray jsonV) {
		return new Vector2D (jsonV.getDouble(0),jsonV.getDouble(1));
	}
	
	protected JSONObject createData() {
	
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
