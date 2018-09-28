package general;

public class Kinematic extends Static {

	public Vector velocity;
	public double rotation;
	
	public int xBound = 800;
	public int yBound = 600;
	public int border = 10;

	public Kinematic(double posx, double posy) {
		super(posx, posy);
		velocity = new Vector(0, 0);
		rotation = 0;
	}
	
	public Kinematic() {
		super(0, 0);
		velocity = new Vector(0, 0);
		rotation = 0;
	}
	
	public void update(SteeringOutput steering, double maxSpeed, double time) {
		if (steering != null) {
			position = position.add(velocity.scale(time));
			orientation += rotation * time;
			velocity = velocity.add(steering.linear.scale(time));
			rotation += steering.angular * time;
			if (velocity.magnitude() > maxSpeed)
				velocity = velocity.normalize().scale(maxSpeed);
			
			if (position.x > xBound + border)
				position.x -= xBound + 2 * border;
			else if (position.x < -border)
				position.x += xBound + 2 * border;
			
			if (position.y > yBound + border)
				position.y -= yBound + 2 * border;
			else if (position.y < -border)
				position.y += yBound + 2 * border;
		}
	}

	public void update(KinematicSteeringOutput steering, double time) {
		if (steering != null) {
			position = position.add(velocity.scale(time));
			orientation += rotation * time;
			velocity = steering.velocity;
			rotation = steering.rotation;
		}
	}

	public Static getStatic() {
		return new Static(position, orientation);
	}

}
