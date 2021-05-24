package simulator.factories;
import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.Body;

public class BasicBodyBuilder extends Builder<Body>{

	public BasicBodyBuilder() {
		super("basic", "a basic Body");
	}
	protected Body createTheInstance(JSONObject data) {
		
		Vector2DBuilder b = new Vector2DBuilder();
		
		Vector2D p = b.createTheVector(data.getJSONArray("p"));
		Vector2D v = b.createTheVector(data.getJSONArray("v"));
		double m = data.getDouble("m");
		String id = data.getString("id");
		
		return new Body(id,p,v,m);
	}
	protected JSONObject createData() {
	
		JSONObject data = new JSONObject();
		
		data.put("id", "the identifier");
		data.put("m", "the mass");
		data.put("v", "the velocity");
		data.put("p", "the position");
		
		return data;
	}

}
