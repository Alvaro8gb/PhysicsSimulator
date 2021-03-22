package simulator.model;

import java.util.List;

import simulator.misc.Vector2D;

public class NewtonUniversalGravitation implements ForceLaws{
	
	protected double G = 6.67E-11;

	public NewtonUniversalGravitation(double c) {
		this.G = c;
	}
	public Vector2D fuerzaij(Body bi, Body bj) {
		Vector2D Fij = new Vector2D();
		Vector2D dij = new Vector2D();
		double fij,d;
		
		dij = bj.getPosition().minus(bi.getPosition()); //pj - pi
		d = bj.getPosition().distanceTo(bi.getPosition()); // |pj - pi|
		
		if(d != 0) {
			fij = G* ((bj.getMass() * bi.getMass()) /(d * d));
			Fij = dij.direction();
			Fij = Fij.scale(fij);
		}
		
		return Fij;
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
