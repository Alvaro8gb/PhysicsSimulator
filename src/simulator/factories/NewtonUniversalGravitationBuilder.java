<<<<<<< Updated upstream:PhysicsSimulator/src/simulator/factories/NewtonUniversalGravitationBuilder.java
package simulator.factories;

import simulator.model.ForceLaws;


public class NewtonUniversalGravitationBuilder extends Builder<ForceLaws>{

}
=======
package simulator.factories;

import org.json.JSONObject;

import simulator.model.ForceLaws;
import simulator.model.NewtonUniversalGravitation;


public class NewtonUniversalGravitationBuilder extends Builder<ForceLaws>{

	private static final double G = 6.67E-11;
	
	public NewtonUniversalGravitationBuilder() {
		super("nlug","Newton’s law of universal gravitation");
	}

	protected NewtonUniversalGravitation createTheInstance(JSONObject data) {	
		double _g = data.has("G")? data.getDouble("G"): G;
		return new NewtonUniversalGravitation(_g) ;
	}

	protected JSONObject createData() {
	
		JSONObject data = new JSONObject();
		
		data.put("G", "the universal gravitacional constant :" + G);
		
		return data;
	}
}
>>>>>>> Stashed changes:src/simulator/factories/NewtonUniversalGravitationBuilder.java