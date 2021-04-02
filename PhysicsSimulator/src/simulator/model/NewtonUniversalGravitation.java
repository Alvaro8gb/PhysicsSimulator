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
			
			for(Body j : bs) if(!i.equals(j)) i.addForce(f(i,j));	
		}
		
	}
	private Vector2D f(Body i,Body j) {
		
		Vector2D p = new Vector2D();
		double d ,mag;
		
		p = j.getPosition().minus(i.getPosition()); //pj-pi
				
		d = p.magnitude(); //|pj-pi|
		
		mag = d>0?(_G * i.getMass() * j.getMass() / (d*d)):0.0; // fij = G* mi*mj /d^2
		
		return p.direction().scale(mag); // dij *  fij
	}
	
	public String toString() {
		return "Newton Universal Gravitacional law with G : " + _G;
	}

}
