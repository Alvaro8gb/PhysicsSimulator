package simulator.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;


public class PhysicsSimulator {

	private double dt;
	private double time;
	private ForceLaws forceLaws;
	private List<Body> listBodies;
	private List<SimulatorObserver> listObservers;
	
	public PhysicsSimulator(double dt,ForceLaws forceLaws) throws IllegalArgumentException {
		
	
		listObservers = new ArrayList<SimulatorObserver>();
		listBodies = new ArrayList<Body>();
		
		reset();
		setDeltaTime(dt);
		setForceLawsLaws(forceLaws);
	}
	
	public void advance() {

		for(Body b: listBodies) b.resetForce();
		
		forceLaws.apply(listBodies);
		
		for(Body b: listBodies) b.move(dt);
		
		time+=dt;
		
		for(SimulatorObserver o : listObservers) o.onAdvance(listBodies, time);
		 
	}
	public void addBody(Body b) throws IllegalArgumentException {
		
	if(listBodies.contains(b)) throw new IllegalArgumentException("This body alredy exists"+ b);
	
	listBodies.add(b);
	
	for(SimulatorObserver o : listObservers) o.onBodyAdded(listBodies, b);
	
	}
	public void setForceLawsLaws(ForceLaws forceLaws) {
		
		if( forceLaws == null) throw new IllegalArgumentException("Unknown force");
		this.forceLaws = forceLaws;
		
		for(SimulatorObserver o : listObservers) o.onForceLawsChanged( forceLaws.toString());

	}
	public void setDeltaTime(double dt) {
		if(dt < 0) throw new IllegalArgumentException("Wrong time per step");
		this.dt = dt;
		
	}
	public void reset() {
		
		listBodies.clear();
		time = 0;
		
		for(SimulatorObserver o : listObservers) o.onReset(listBodies, time, dt, forceLaws.toString());
	}
	public void addObserver(SimulatorObserver o) {
		
		if(!listObservers.contains(o)) listObservers.add(o);
		
		o.onRegister(listBodies, time, dt, forceLaws.toString());
	}
	public JSONObject getState() {
		JSONObject jPS = new JSONObject();
		JSONArray arrayBodies = new JSONArray();
		
		jPS.put("time", time);
		
		for(Body b: listBodies) arrayBodies.put(b.getState());
		
		jPS.put("bodies",arrayBodies);
		
		return jPS;
	}
	public String toString() {
		return getState().toString();
	}
	
}
