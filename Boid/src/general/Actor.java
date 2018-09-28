package general;

public interface Actor {

	public void update(double time);

	public Kinematic getKinematic();
	
	public int[] getColor();
	
	public void setColor(int[] c);
}
