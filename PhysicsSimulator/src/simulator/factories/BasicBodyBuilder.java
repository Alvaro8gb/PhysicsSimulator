package simulator.factories;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.Body;

public class BasicBodyBuilder extends Builder<Body>{

	public BasicBodyBuilder() {
		super("basic", "esto es un objeto basico");
	}
	public Body createTheInstance(JSONObject data) {
		
		Vector2D p = createVector(data.getJSONArray("p"));
		Vector2D v = createVector(data.getJSONArray("v"));
		double m = data.getDouble("m");
		String id = data.getString("id");
		
		return new Body(id,p,v,m);
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
		
		return data;
	}

}
