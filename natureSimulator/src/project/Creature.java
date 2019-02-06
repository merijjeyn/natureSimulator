package project;


import game.*;
import naturesimulator.Action;
import naturesimulator.LocalInformation;


/**
 * The parent class of the all creatures. Implements Drawable interface.
 * @author Muhammed Enes Toptas
 *
 */
public abstract class  Creature implements Drawable {
	private double health;
	protected int X;
	protected int Y;
	
	/**
	 * The constructor method for the Creature class
	 * @param x X coordinate of the Creature
	 * @param y Y coordinate of the Creature
	 * @param health initial health of the Creature
	 */
	public Creature(int x, int y, double health) {
		this.X=x;
		this.Y=y;
		this.health= health;
	}
	
	/**
	 * Chooses the best possible action and returns it
	 * @param information	Parameter of LocalInformation
	 * @return Chosen action 
	 */
	public abstract Action chooseAction (LocalInformation information);
	
	/**
	 * Getter for current health of the creature.
	 * @return health value
	 */
	public double getHealth() {
		return health;
	}
	
	/**
	 * Setter for the health value of the creature.
	 * @param health intended health to set.
	 */
	public void setHealth(double health) {
		this.health = health;
	}
	
	/**
	 * Getter for x coordinate of the creature.
	 * @return x coordinate of the creature
	 */
	public int getX() {
		return X;
	}
	
	/**
	 * Getter for y coordinate of the creature.
	 * @return y coordinate of the creature.
	 */
	public int getY() {
		return Y;
	}
	
	/**
	 * Moves the creature to the wanted direction 
	 * and decreases its health by one.
	 * @param direction direction of the movement
	 */
	public void move(Direction direction) {
		//determining the direction using 4 if statements.
		if (direction == Direction.UP)
			Y--;
		if (direction == Direction.DOWN)
			Y++;
		if (direction == Direction.RIGHT)
			X++;
		if (direction == Direction.LEFT)
			X--;
		health--;
	}
	
	/**
	 * Makes the creature attack to the other creature,
	 * attacking instantly kills the attacked creature and the attacking creature gets
	 * all the health, also moves to that direction.
	 * @param creature creature that's been attacked
	 */
	public void attack(Creature creature) {
		health+=creature.getHealth();
		//if health exceeds maximum health, this equalizes health to maximum health.
		if(health>20.0)
			health=20.0;
		creature.setHealth(0.0);
		X = creature.X;
		Y = creature.Y;
	}
	
	/**
	 * As a result of reproduction, creates a new creature in the given direction.
	 * Every creature subclass should implement this method in its own way.
	 * @param direction direction of the newly created child creature relative to the parent one
	 * @return the new creature
	 */
	public abstract Creature reproduce(Direction direction);
	
	/**
	 * Keeps the creature on its place.
	 * Every creature subclass should implement this in its own way.
	 */
	public abstract void stay();
		
}
