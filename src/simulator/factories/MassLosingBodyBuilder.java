package simulator.factories;

import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.Body;
import simulator.model.MassLossingBody;

public class MassLosingBodyBuilder extends Builder <Body>{

	public MassLosingBodyBuilder() {
		super("mlb","a body who lose mass");
	}
	
	protected MassLossingBody createTheInstance(JSONObject data) {
		
		Vector2DBuilder b = new Vector2DBuilder();
		
		Vector2D p = b.createTheVector(data.getJSONArray("p"));
		Vector2D v = b.createTheVector(data.getJSONArray("v"));
		double m = data.getDouble("m");
		String id = data.getString("id");
		
		double freq = data.getDouble("freq");
		if(freq < 0) throw new IllegalArgumentException("The lossFrequency must be positive");
		
		double factor = data.getDouble("factor");
		if(factor > 1 || factor < 0) throw new IllegalArgumentException("The lossFactor must be a number between 0-1");
		
		return new MassLossingBody(id,p,v,m,factor,freq);
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
