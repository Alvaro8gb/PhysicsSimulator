package simulator.model;

import java.util.List;
import java.util.Random;

import simulator.misc.Vector2D;

public class CircularAleatoryForce implements ForceLaws{

	protected Random _rand;
	protected Long seed;
	
	public CircularAleatoryForce(long seed) {
		this.seed = seed;
		_rand = new Random(seed);
	}
	public void apply(List<Body> bs) {
		
		for(Body b: bs) b.addForce(f(b)); 
			  
	}
	private Vector2D f(Body b) {
		
		double valorAleat = _rand.nextDouble() * -1; 
		
		return b.getPosition().direction().scale(b.getMass() * valorAleat);

	}
	public String toString() {
		return " Circular Aleatory Force with seed : " + seed;
	}

}
