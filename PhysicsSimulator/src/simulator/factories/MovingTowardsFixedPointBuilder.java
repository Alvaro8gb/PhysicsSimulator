package simulator.factories;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.ForceLaws;
import simulator.model.MovingTowardsFixedPoint;

public class MovingTowardsFixedPointBuilder extends Builder<ForceLaws> {

	private static final double g = 9.8;
	private Vector2D c = new Vector2D();
	
	public MovingTowardsFixedPointBuilder() {
		super("mtfp","dscrip");
	}
	public ForceLaws createTheInstance(JSONObject data) {
		double _g = data.has("g")? data.getDouble("g"): g;
		Vector2D _c = data.has("c")? createVector(data.getJSONArray("c")): c;
		
		return new MovingTowardsFixedPoint(_g,_c);
	}
	private Vector2D createVector(JSONArray jsonV) {
		if(jsonV.length() !=2) throw new IllegalArgumentException();
		return new Vector2D (jsonV.getDouble(0),jsonV.getDouble(1));
	}

	public JSONObject createData() {
		JSONObject data = new JSONObject();
		
		data.put("g", "the the gravitational constant : "+ g);
		data.put("c", "the center point " + c);
		
		return data;
	}

	

}
