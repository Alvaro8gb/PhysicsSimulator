package simulator.factories;

import java.util.List;

import org.json.JSONObject;

import simulator.model.Body;
import simulator.model.ForceLaws;
import simulator.model.NewtonUniversalGravitation;


public class NewtonUniversalGravitationBuilder extends Builder<ForceLaws>{

	public NewtonUniversalGravitationBuilder() {
		super("nlug","Newton Universal Gravitation Law");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected ForceLaws createTheInstance(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		double G = jsonObject.has("G")? jsonObject.getDouble("G"):6.67E-11;
		return new NewtonUniversalGravitation(G);
	}

	@Override
	protected JSONObject createData() {
		// TODO Auto-generated method stub
		JSONObject data = new JSONObject();
		data.put("G", "NUGL G");
		return data;
	}
	

}
