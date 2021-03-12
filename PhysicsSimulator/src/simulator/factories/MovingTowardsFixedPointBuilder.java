package simulator.factories;

import org.json.JSONObject;

import simulator.model.ForceLaws;
import simulator.model.MovingTowardsFixedPoint;

public class MovingTowardsFixedPointBuilder extends Builder<ForceLaws> {

	private static final double g = 9.8;
	
	public ForceLaws createTheInstance(JSONObject data) {
		double _g = data.has("g")? data.getDouble("g"): g;
		
		return new MovingTowardsFixedPoint(_g);
	}

	@Override
	public JSONObject createData() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
