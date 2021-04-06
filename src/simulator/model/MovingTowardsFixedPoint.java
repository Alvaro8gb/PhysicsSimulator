package simulator.model;

import java.util.List;

import simulator.misc.Vector2D;

public class MovingTowardsFixedPoint implements ForceLaws {
	
	private double _g;
	private Vector2D _c;
	
	public MovingTowardsFixedPoint(double _g, Vector2D _c) {
		this._g= _g;
		this._c = _c;
	}
	public void apply(List<Body> bs) {
		
		for(Body b: bs) b.addForce(f(b)); 
			  
	}
	private Vector2D f(Body b) {
		return _c.minus(b.getPosition()).direction().scale(_g*b.getMass());// Fi = m/g * di
	}
	public String toString() {
		return "Moving towars a fixed point with g : " + _g + " and center : " +_c;
	}
		
}
