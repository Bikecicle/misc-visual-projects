package main;

import general.Vector;

public class Point {

	public Vector pos;
	public int[] color;

	public Point(Vector pos) {
		this.pos = pos;
	}

	public void setColor(int[] color) {
		this.color = color;
	}

}
