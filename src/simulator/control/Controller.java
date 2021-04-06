package simulator.control;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import simulator.factories.Factory;
import simulator.model.Body;
import simulator.model.PhysicsSimulator;

public class Controller {

	private PhysicsSimulator _sim;
	private  Factory<Body> _bodiesFactory;
	
	public Controller( PhysicsSimulator _sim,Factory<Body> _bodiesFactory) {
		this._bodiesFactory = _bodiesFactory;
		this._sim = _sim;
	}
	
	public void loadBodies(InputStream in) {
		JSONObject jsonInput = new JSONObject(new JSONTokener(in));
		JSONArray bodies = jsonInput.getJSONArray("bodies");
		
		for(int i = 0;i< bodies.length();i++) _sim.addBody(_bodiesFactory.createInstance(bodies.getJSONObject(i)));
		
	}
	public void run(int steps, OutputStream out, InputStream expOut, StateComparator cmp) throws NotEqualStatesException {
		
		JSONObject expOutJo = null;
		JSONObject currState = null;
		JSONObject expState = null;
		
		if(expOut !=null) expOutJo = new JSONObject (new JSONTokener(expOut));
			
		if(out == null) out = new OutputStream() { public void write(int b) throws IOException {}}; //asigna uno que no escribe nada
		
		PrintStream p =  new PrintStream(out); 
	
		 p.println("{");
		 p.println("\"states\": [");
	      
		for(int i = 0 ; i < steps-1;i++) {
			currState = _sim.getState();
			p.println(currState);
			p.print(',');
			
			if(expOutJo != null) {
				expState = expOutJo.getJSONArray("states").getJSONObject(i);
				if(!cmp.equal(currState,expState)) throw new NotEqualStatesException(i,currState,expState);
			}
			_sim.advance();
			
		}
		
		p.println(currState);
		
		 p.println("]");
		 p.println("}");
			
	}

}