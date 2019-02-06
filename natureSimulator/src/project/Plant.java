package project;

import java.awt.Color;
import java.util.List;

import game.Direction;
import naturesimulator.Action;
import naturesimulator.LocalInformation;
import ui.GridPanel;

/**
 * Plant class extending the Creature parent class.
 * @author Muhammed Enes Toptas
 *
 */
public class Plant extends Creature {
	
	private static final double MaxHealth =1.0;
	
	/**
	 * Constructs the first plants created. 
	 * @param x initial x coordinate of the newly created plant
	 * @param y initial y coordinate of the newly created plant
	 */
	public Plant(int x, int y ) {
		super(x,y,0.5);
	}
	
	/**
	 * Constructor used by the reproduction method.
	 * @param x initial x coordinate of the plant created by reproduction
	 * @param y initial y coordinate of the plant created by reproduction
	 * @param health initial health value of the plant created by reproduction
	 */
	public Plant(int x, int y, double health ) {
		super(x,y,health);
	}
	
	/**
	 * Draws the plant on the grid pannel according to its current health
	 */
	@Override
	public void draw(GridPanel panel) {
		if (getHealth()==1.0) panel.drawSquare(X, Y,new Color(0,90,0));
		if (getHealth()>=0.75 && getHealth()<1.0) panel.drawSquare(X, Y,new Color(2,100,0));
		if (getHealth()<0.75 && getHealth()>0.50) panel.drawSquare(X, Y,new Color(2,143,0));
		if (getHealth()<=0.50 && getHealth()>0.25) panel.drawSquare(X, Y,new Color(2,190,0));
		if (getHealth()<=0.25 && getHealth()>0.0) panel.drawSquare(X, Y,Color.GREEN);
		
	}

	/**
	 * Chooses the best action possible according to games rules.
	 */
	@Override
	public Action chooseAction(LocalInformation information) {
		List<Direction> freeDirections = information.getFreeDirections();
		Direction possibleDirection = LocalInformation.getRandomDirection(freeDirections);
		if (getHealth()>=0.75&&possibleDirection!=null)
			return new Action(Action.Type.REPRODUCE,possibleDirection);
		else 
			return new Action(Action.Type.STAY);
		
	}
	
	/**
	 * Reproduces the plant. Parent plant loses some health during the reproduction.
	 */
	@Override
	public Creature reproduce(Direction direction) {
		double babyHealth=getHealth()/10.0;
		setHealth(getHealth()*7.0/10.0);
		if(direction==Direction.UP)
			return new Plant(X,Y-1,babyHealth);
		if(direction==Direction.DOWN)
			return new Plant(X,Y+1,babyHealth);
		if(direction==Direction.RIGHT)
			return new Plant(X+1,Y,babyHealth);
		if(direction==Direction.LEFT)
			return new Plant(X-1,Y,babyHealth);
		return null;
	}
	
	/**
	 * Plant stands as it cannot reproduce, it gets healthier over time by staying.
	 */
	@Override
	public void stay() {
		if (getHealth() < MaxHealth) {
			setHealth(getHealth()+0.05);
			if(getHealth()>MaxHealth)
				setHealth(1.00);
		}
	}
}
