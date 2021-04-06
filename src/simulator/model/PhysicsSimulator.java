package simulator.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class PhysicsSimulator {

	private double dt;
	private double time;
	private ForceLaws forces;
	private List<Body> listCuerpos;
	
	public PhysicsSimulator(double dt,ForceLaws forces) throws IllegalArgumentException {
		
		if(dt < 0) throw new IllegalArgumentException("Wrong time per step");
		
		if( forces == null) throw new IllegalArgumentException("Unknown force");
		
		this.dt = dt;
		this.forces= forces;
		listCuerpos = new ArrayList<Body>();
		time = 0;
		
	}
	
	public void advance() {

		for(Body b: listCuerpos) b.resetForce();
		
		forces.apply(listCuerpos);
		
		for(Body b: listCuerpos) b.move(dt);
		
		time+=dt;
		 
	}
	public void addBody(Body b) throws IllegalArgumentException {
		
	if(listCuerpos.contains(b)) throw new IllegalArgumentException("This body alredy exists"+ b);
	
	listCuerpos.add(b);
	
	}
	public JSONObject getState() {
		JSONObject jPS = new JSONObject();
		JSONArray arrayBodies = new JSONArray();
		
		jPS.put("time", time);
		
		for(Body b: listCuerpos) arrayBodies.put(b.getState());
		
		jPS.put("bodies",arrayBodies);
		
		return jPS;
	}
	public String toString() {
		return getState().toString();
	}
	

}
