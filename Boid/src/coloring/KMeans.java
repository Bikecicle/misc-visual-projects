package coloring;

import java.util.ArrayList;
import java.util.List;

import general.Actor;
import general.Vector;

public class KMeans {

	Actor[] actors;
	Centroid[] centroids;

	int k;

	public KMeans(int k, Actor[] actors, int xBound, int yBound) {
		this.k = k;
		this.actors = actors;
		centroids = new Centroid[k];
		for (int i = 0; i < k; i++) {
			int[] color = { (int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256) };
			centroids[i] = new Centroid(Math.random() * xBound, Math.random() * yBound, color);
		}
	}

	public void update() {
		while (true) {
			for (Centroid centroid : centroids) {
				centroid.domain.clear();
			}
			for (Actor actor : actors) {
				double minDist = Double.POSITIVE_INFINITY;
				int min = 0;
				for (int i = 0; i < k; i++) {
					double dist = actor.getKinematic().position.distance(centroids[i].pos);
					if (dist < minDist) {
						minDist = dist;
						min = i;
					}
				}
				centroids[min].domain.add(actor);
			}
			boolean changed = false;
			for (Centroid centroid : centroids) {
				changed = changed || centroid.update();
			}
			if (!changed) {
				for (Centroid centroid : centroids) {
					for (Actor actor : centroid.domain) {
						actor.setColor(centroid.color);
					}
				}
				break;
			}
		}
	}

	private class Centroid {

		Vector pos;
		List<Actor> domain;
		int[] color;

		public Centroid(double x, double y, int[] color) {
			pos = new Vector(x, y);
			domain = new ArrayList<>();
			this.color = color;
		}

		public boolean update() {
			double xp = pos.x;
			double yp = pos.y;
			double xSum = 0;
			double ySum = 0;
			for (Actor a : domain) {
				xSum += a.getKinematic().position.x;
				ySum += a.getKinematic().position.y;
			}
			pos.x = xSum / domain.size();
			pos.y = ySum / domain.size();
			return Math.abs(pos.x - xp) > 0.01 && Math.abs(pos.y - yp) > 0.01;
		}
	}

}
