package simulator.factories;
import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.ForceLaws;
import simulator.model.MovingTowardsFixedPoint;

public class MovingTowardsFixedPointBuilder extends Builder<ForceLaws> {

	private static final double g = 9.81;
	private static final Vector2D c = new Vector2D();
	
	public MovingTowardsFixedPointBuilder() {
		super("mtfp","Moving towards a fixed point");
	}
	protected MovingTowardsFixedPoint createTheInstance(JSONObject data) {
		Vector2DBuilder b = new Vector2DBuilder();
		
		double _g = data.has("g")? data.getDouble("g"): g;
		Vector2D _c = data.has("c")? b.createTheVector(data.getJSONArray("c")): c;
		
		return new MovingTowardsFixedPoint(_g,_c);
	}
	protected JSONObject createData() {
		JSONObject data = new JSONObject();
		
		data.put("g", "the the gravitational constant : "+ g);
		data.put("c", "the center point " + c);
		
		return data;
	}

	

}
