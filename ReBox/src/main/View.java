package main;

import java.util.ArrayList;
import java.util.List;

import general.Vector;
import processing.core.PApplet;

public class View extends PApplet {

	Box[] p;
	int g = 0;
	List<Point> l;

	double shift = 1;
	double jerk = 0.000001;

	public static void main(String[] args) {
		PApplet.main("main.View");
	}

	public void settings() {
		fullScreen();
	}

	public void setup() {
		p = randomize(20);

	}

	public void draw() {
		for (Box b : p) {
			shift += jerk;
			double s = Math.abs(shift % 2 - 1);
			double dx = Math.random() * s * 2 - s;
			double dy = Math.random() * s * 2 - s;
			double dr = Math.random() * s * 2 - s;
			if (b.x + dx > 0 && b.x + dx < 1) {
				b.x += dx;
			}
			if (b.y + dy > 0 && b.y + dy < 1) {
				b.y += dy;
			}
			if (b.r + dr > 0 && b.r + dr < 1) {
				b.r += dr;
			}
		}
		l = new ArrayList<>();
		int[] color = { 0, 0, 0 };
		traverse(p, 0, 0, width / 2, height / 2, 3, color);
		loadPixels();
		fill(color(Math.abs(256 - g % 512)));
		noStroke();
		for (Point k : l) {
			pixels[(int) (k.pos.x + width * k.pos.y)] = color(Math.abs(256 - g % 512));
		}
		updatePixels();
		g++;
	}

	private void traverse(Box[] p, int x, int y, int w, int h, int d, int[] c) {
		if (d == 0) {
			for (Box b : p) {
				int kx = (int) (2 * w * b.x) + x;
				int ky = (int) (2 * h * b.y) + y;
				int[] kc = new int[3];
				kc[0] = c[0];
				kc[1] = c[1];
				kc[2] = c[2];
				if (ky > 0 && ky < height - 1 && x > 0 && x < width - 1
						&& Math.sqrt(Math.pow(b.x - 0.5, 2) + Math.pow(b.y - 0.5, 2)) < 0.5) {
					Point k = new Point(new Vector(kx, ky));
					k.color = kc;
					l.add(k);
				}
			}
		} else {
			for (Box b : p) {
				int kx = (int) (2 * w * b.x) + x;
				int ky = (int) (2 * h * b.y) + y;
				int kw = (int) (w * b.r);
				int kh = (int) (h * b.r);
				int[] kc = new int[3];
				kc[0] = c[0] + b.c[0];
				kc[1] = c[1] + b.c[1];
				kc[2] = c[2] + b.c[2];
				traverse(p, kx, ky, kw, kh, d - 1, kc);
			}
		}
	}
	
	@Override
	public void mousePressed() {
		shift = 1;
	}

	private Box[] randomize(int n) {
		Box[] p = new Box[n];
		for (int i = 0; i < n; i++) {
			double x = 1;
			double y = 1;
			while (Math.sqrt(Math.pow(x - 0.5, 2) + Math.pow(y - 0.5, 2)) > 0.5) {
				x = Math.random();
				y = Math.random();
			}
			double r = 1;
			while (r > x || r > 1 - x || r > y || r > 1 - y) {
				r = (double) Math.random();
			}
			double q = Math.random() * 2 * Math.PI;
			int[] c = new int[3];
			c[0] = (int) (Math.random() * 256);
			c[1] = (int) (Math.random() * 256);
			c[2] = (int) (Math.random() * 256);
			p[i] = new Box(x, y, r, q, c);
		}
		return p;
	}
}
