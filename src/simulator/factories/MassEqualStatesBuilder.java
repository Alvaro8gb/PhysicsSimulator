<<<<<<< Updated upstream:PhysicsSimulator/src/simulator/factories/MassEqualStatesBuilder.java
package simulator.factories;

import simulator.control.StateComparator;

public class MassEqualStatesBuilder extends Builder<StateComparator> {

}
=======
package simulator.factories;

import org.json.JSONObject;

import simulator.control.MassEqualStates;
import simulator.control.StateComparator;


public class MassEqualStatesBuilder extends Builder<StateComparator> {

	public MassEqualStatesBuilder() {
		super("masseq", "Mass equal states comparator");
	}
	protected MassEqualStates createTheInstance(JSONObject data) {
		return new MassEqualStates();
	}
	protected JSONObject createData() {
		return new JSONObject();
	}

}
>>>>>>> Stashed changes:src/simulator/factories/MassEqualStatesBuilder.java
