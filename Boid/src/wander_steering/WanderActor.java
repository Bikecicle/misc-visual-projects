package wander_steering;

import algorithm.Wander;
import general.Actor;
import general.Kinematic;

public class WanderActor implements Actor {

	private Kinematic kinematic;
	private Wander wander;
	private double maxSpeed = 100;

	public WanderActor(double posx, double posy) {
		kinematic = new Kinematic(posx, posy);
		wander = new Wander(kinematic, Math.PI * 2, Math.PI, Math.PI / 32, Math.PI / 8, 200, 100, Math.PI / 2, 0,
				800);
	}

	public Kinematic getKinematic() {
		return kinematic;
	}

	public void update(double time) {
		kinematic.update(wander.getSteering(), maxSpeed, time);
	}

	@Override
	public int[] getColor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setColor(int[] c) {
		// TODO Auto-generated method stub
		
	}
}
