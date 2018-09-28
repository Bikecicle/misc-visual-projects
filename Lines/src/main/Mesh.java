package main;

import evolution.core.Genome;
import evolution.core.Splicer;

public class Mesh implements Genome {
	
	private static final long serialVersionUID = 1421000939608373115L;
	
	int[] x1;
	int[] x2;
	int[] y1;
	int[] y2;
	
	int count;
	int width;
	int height;
	
	public Mesh(int count, int width, int height) {
		this.count = count;
		this.width = width;
		this.height = height;
	}

	@Override
	public Genome breed(Genome g1) {
		Mesh other = (Mesh) g1;
		Mesh child = new Mesh(count, width, height);
		child.x1 = Splicer.spliceInt(this.x1, other.x1);
		child.x2 = Splicer.spliceInt(this.x2, other.x2);
		child.y1 = Splicer.spliceInt(this.y1, other.y1);
		child.y2 = Splicer.spliceInt(this.y2, other.y2);
		return child;
	}

	@Override
	public void randomize() {
		for (int i = 0; i < count; i++) {
			x1[i] = (int) (Math.random() * width);
			x2[i] = (int) (Math.random() * width);
			y1[i] = (int) (Math.random() * height);
			y2[i] = (int) (Math.random() * height);
		}
	}

	@Override
	public double getScore() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void simulate(boolean[][] edge) {
		
	}

}
