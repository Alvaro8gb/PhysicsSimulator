package simulator.model;

import java.util.List;

import simulator.misc.Vector2D;

public class NewtonUniversalGravitation {
	
	private static final double G = 6.67E-11;

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
				
		d = j.getPosition().distanceTo(i.getPosition()); //|pj-pi|
		
		if(d!=0) c = G * i.getMass() * j.getMass() / (d*d); //fij
		else return f;
		
		f = p.direction(); // pj - pi vector di
		
		return f.scale(c); // di * fij
	}

}
