package simulator.factories;

import org.json.JSONObject;

import simulator.model.NewtonUniversalGravitation;


public class NewtonUniversalGravitationBuilder extends Builder<NewtonUniversalGravitation>{

	private static final double G = 6.67E-11;
	
	public NewtonUniversalGravitationBuilder() {
		super("nlug", "esto es la fuerza gravitacional de newton");
	}

	public NewtonUniversalGravitation createTheInstance(JSONObject data) {	
		double _g = data.has("G")? data.getDouble("G"): G;
		return new NewtonUniversalGravitation(_g) ;
		
	}

	public JSONObject createData() {
	
		JSONObject data = new JSONObject();
		
		data.put("G", "the gravitacional constant");
		
		return data;
	}
}
