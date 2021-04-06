<<<<<<< Updated upstream:PhysicsSimulator/src/simulator/factories/NoForceBuilder.java
package simulator.factories;

import simulator.model.ForceLaws;

public class NoForceBuilder extends Builder<ForceLaws>{

}
=======
package simulator.factories;

import org.json.JSONObject;

import simulator.model.ForceLaws;
import simulator.model.NoForce;

public class NoForceBuilder extends Builder<ForceLaws>{

	public NoForceBuilder() {
		super("nf", "No force");
	}
	protected NoForce createTheInstance(JSONObject data) throws IllegalArgumentException {
		return new NoForce();
	}
	protected JSONObject createData() {
		return new JSONObject();
	}
}
>>>>>>> Stashed changes:src/simulator/factories/NoForceBuilder.java
