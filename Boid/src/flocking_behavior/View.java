package flocking_behavior;

import coloring.KMeans;
import general.Actor;
import processing.core.PApplet;
import wander_steering.WanderActor;

public class View extends PApplet {

	public static final int characterRadius = 2;
	public static final int boidCount = 800;
	public static final int sharkCount = 40;
	public static final int k = 10;

	private static Actor[] boids;
	private static Actor[] sharks;
	private static KMeans kMeans;

	private static long timestamp;

	public static void main(String[] args) {
		PApplet.main("flocking_behavior.View");
	}

	public void settings() {
		fullScreen();
	}

	public void setup() {
		boids = new Actor[boidCount];
		sharks = new Actor[sharkCount];
		kMeans = new KMeans(k, boids, width, height);
		for (int i = 0; i < boidCount; i++) {
			boids[i] = new BoidActor(Math.random() * width, Math.random() * height, boids, sharks);
			boids[i].getKinematic().xBound = width;
			boids[i].getKinematic().yBound = height;
			boids[i].getKinematic().border = 2 * characterRadius;
			((BoidActor) boids[i]).brightness = (Math.random() / 2) + 0.5;
		}
		for (int i = 0; i < sharkCount; i++) {
			sharks[i] = new WanderActor(Math.random() * width, Math.random() * height);
			sharks[i].getKinematic().xBound = width;
			sharks[i].getKinematic().yBound = height;
			sharks[i].getKinematic().border = 2 * characterRadius;
		}
		for (int i = 0; i < boidCount; i++) {
			((BoidActor) boids[i]).selectTargets();
		}
		timestamp = System.nanoTime();
	}

	public void draw() {
		kMeans.update();
		long timestampPrev = timestamp;
		timestamp = System.nanoTime();
		double dt = (timestamp - timestampPrev) / 1000000000.0;
		for (Actor boid : boids) {
			boid.update(dt);
			renderActor(boid);
		}
		for (Actor shark : sharks) {
			shark.update(dt);
			// renderActor(shark);
		}
	}

	public void mousePressed() {
		for (int i = 0; i < boidCount; i++) {
			((BoidActor) boids[i]).scramble();
		}
	}

	private void renderActor(Actor agent) {
		float x = (float) agent.getKinematic().position.x;
		float y = (float) (height - agent.getKinematic().position.y);
		int[] c = agent.getColor();
		fill(color(c[0], c[1], c[2]));
		noStroke();
		ellipse(x, y, 2.0f * characterRadius, 2.0f * characterRadius);
	}

}
