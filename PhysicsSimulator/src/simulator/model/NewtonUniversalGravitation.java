package simulator.model;

import java.util.List;

import simulator.misc.Vector2D;

public class NewtonUniversalGravitation implements ForceLaws{
	
	protected double G;

	public NewtonUniversalGravitation(double c) {
		this.G = c;
	}
	private Vector2D fuerzaij(Body bi, Body bj) {

		Vector2D dij = new Vector2D();
		double fij,d;
		
		dij = bj.getPosition().minus(bi.getPosition()); //pj - pi
		d = dij.magnitude(); // |pj - pi|
		
		if(d > 0) {
			fij = G* bj.getMass() * bi.getMass() /(d * d);
		}
		else fij = 0.0;
		
		return dij.direction().scale(fij);
	}
	public void apply(List<Body> bs) {
		
		for( Body bi: bs) {
			bi.resetForce();
			for(Body bj : bs) {
				bi.addForce(fuerzaij(bi,bj));
			}
		}
	}

	
	

}
