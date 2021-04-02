package simulator.factories;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.ForceLaws;
import simulator.model.MovingTowardsFixedPoint;

public class MovingTowardsFixedPointBuilder extends Builder<ForceLaws> {

	public MovingTowardsFixedPointBuilder() {
		super("mtcp", "Moving Towards Fixed Point");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected ForceLaws createTheInstance(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		double g = jsonObject.has("g")?jsonObject.getDouble("g"): 9.81;
		
		Vector2D c;
		if(jsonObject.has("c")) {
			JSONArray pos = jsonObject.getJSONArray("c");
			c = new Vector2D(pos.getDouble(0),pos.getDouble(1));
		}
		else c = new Vector2D(0,0);
		
		return new MovingTowardsFixedPoint(g,c);
	}

	@Override
	protected JSONObject createData() {
		// TODO Auto-generated method stub
		JSONObject data = new JSONObject();
		data.put("c", "MTFP c");
		data.put("g", "MTFP g");
		return data;
	}

}
