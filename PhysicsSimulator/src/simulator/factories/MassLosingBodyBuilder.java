package simulator.factories;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.Body;
import simulator.model.MassLossingBody;

public class MassLosingBodyBuilder extends Builder <Body>{

	public MassLosingBodyBuilder() {
		super("mlb", "Mass Lossing Body");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Body createTheInstance(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		String id;
		Vector2D v;
		Vector2D p;
		double m;
		double lossFrequency;
		double lossFactor;
		JSONArray pos;
		id = jsonObject.getString("id");
		pos = jsonObject.getJSONArray("p");
		p = new Vector2D(pos.getDouble(0),pos.getDouble(1));
		pos = jsonObject.getJSONArray("v");
		v = new Vector2D(pos.getDouble(0),pos.getDouble(1));
		m = jsonObject.getDouble("m");
		lossFrequency = jsonObject.getDouble("freq");
		lossFactor = jsonObject.getDouble("factor");
		return new MassLossingBody(id, v, p, m,lossFrequency,lossFactor);
	}

	@Override
	protected JSONObject createData() {
		JSONObject data = new JSONObject();
		data.put("id", "MLBody id");
		data.put("p", "MLBody position");
		data.put("v", "MLBody speed");
		data.put("m", "MLBody mass");
		data.put("freq", "MLBody lossFrequency");
		data.put("factor", "MLBody lossFactor");
		return null;
	}

}
