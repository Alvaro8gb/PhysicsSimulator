package simulator.factories;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.Body;

public class BasicBodyBuilder extends Builder<Body>{

	public BasicBodyBuilder() {
		super("basic","Default Body");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Body createTheInstance(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		String id;
		Vector2D v;
		Vector2D p;
		double m;
		JSONArray pos;
		id = jsonObject.getString("id");
		pos = jsonObject.getJSONArray("p");
		p = new Vector2D(pos.getDouble(0),pos.getDouble(1));
		pos = jsonObject.getJSONArray("v");
		v = new Vector2D(pos.getDouble(0),pos.getDouble(1));
		m = jsonObject.getDouble("m");
		return new Body(id,v,p,m);
	}

	@Override
	protected JSONObject createData() {
		// TODO Auto-generated method stub
		JSONObject data = new JSONObject();
		data.put("id", "Body id");
		data.put("p", "Body position");
		data.put("v", "Body speed");
		data.put("m", "Body mass");

		return data;
	}

}
