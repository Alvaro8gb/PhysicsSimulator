package simulator.model;

import java.util.List;

import simulator.misc.Vector2D;

public class NewtonUniversalGravitation implements ForceLaws {
	protected double _G;
	
	public NewtonUniversalGravitation(double _G) {
		this._G = _G;
	}

	public void apply(List<Body> bs) {
			
		for(Body i: bs) {
			
			for(Body j : bs) if(i!=j) i.addForce(f(i,j));	
		}
		
	}
	private Vector2D f(Body i,Body j) {
		
		Vector2D p = new Vector2D();
		Vector2D f = new Vector2D();
		double c,d ;
		
		p = j.getPosition().minus(i.getPosition()); //pj-pi
				
		d = p.magnitude();//|pj-pi|
		
		if(d>0) c = _G* i.getMass() * j.getMass() / (d*d); //fij
		else return f;
		
		f = p.direction(); // pj - pi vector di
		
		return f.scale(c); // di * fij

	}
	
	public String toString() {
		return null;
	}

}
