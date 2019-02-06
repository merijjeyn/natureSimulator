package project;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import game.Direction;
import naturesimulator.Action;
import naturesimulator.LocalInformation;
import ui.GridPanel;

/**
 * Herbivore class extending Creature parent class.
 * 
 * @author Muhammed Enes Toptas
 */
public class Herbivore extends Creature {

	private static final double MaxHealth =20.0;
	
	/**
	 * Constructs the first herbivores created. 
	 * @param x initial x coordinate of the newly created herbivore
	 * @param y initial y coordinate of the newly created herbivore
	 */
	public Herbivore(int x, int y) {
		super(x,y,10);
	}
	
	/**
	 * Constructor used by the reproduction method.
	 * @param x initial x coordinate of the herbivore created by reproduction
	 * @param y initial y coordinate of the herbivore created by reproduction
	 * @param health initial health value of the herbivore created by reproduction
	 */
	public Herbivore(int x, int y, double health) {
		super(x,y,health);
	}
	
	/**
	 * Draws the herbivore on the grid panel depending on the its health
	 * @param panel
	 */
	@Override
	public void draw(GridPanel panel) {
		if (getHealth()==20.00) panel.drawSquare(X, Y, new Color (255,51,0));
		if (getHealth()>=18.0 && getHealth()<20.00) panel.drawSquare(X, Y,new Color(255,0,0));
		if (getHealth()>=16.0 && getHealth()<18.00) panel.drawSquare(X, Y,new Color(250,0,0));
		if (getHealth()>=14.0 && getHealth()<16.00) panel.drawSquare(X, Y,new Color(240,0,0));
		if (getHealth()>=12.0 && getHealth()<14.00) panel.drawSquare(X, Y,new Color(220,0,0));
		if (getHealth()>=10.0 && getHealth()<12.00) panel.drawSquare(X, Y,new Color(200,0,0));
		if (getHealth()>=8.0 && getHealth()<10.0) panel.drawSquare(X, Y,new Color(160,0,0));
		if (getHealth()>=6.0 && getHealth()<8.0) panel.drawSquare(X, Y,new Color(120,0,0));
		if (getHealth()>=4.0 && getHealth()<6.0) panel.drawSquare(X, Y,new Color(80,0,0));
		if (getHealth()>=2.0 && getHealth()<4.0) panel.drawSquare(X, Y,new Color(40,0,0));
		if (getHealth()<2.0 && getHealth()>0.0) panel.drawSquare(X, Y,new Color(0,0,0));
	}
	
	/**
	 * Chooses the best action available for the herbivore with respect to game rules.
	 */
	@Override
	public Action chooseAction(LocalInformation information) {
		//gets the free directions
		List<Direction> freeDirections = information.getFreeDirections();
		//chooses one of them
		Direction possibleDirection = LocalInformation.getRandomDirection(freeDirections);
		if(getHealth()==20&&possibleDirection!=null)
			return new Action(Action.Type.REPRODUCE,possibleDirection);
		else if (isThereAnyone(information) !=null)
			return new Action(Action.Type.ATTACK, isThereAnyone(information));
		else if (getHealth()>1.0)
			return new Action(Action.Type.MOVE,possibleDirection);
		else 
			return new Action(Action.Type.STAY);
	}
	
	/**
	 * Reproduces the herbivore. Reproduction makes the herbivore lose some of its health.
	 */
	@Override
	public Creature reproduce(Direction direction) {
		setHealth(getHealth()*2.0/5.0);
		if(direction==Direction.UP)
			return new Herbivore(X,Y-1,MaxHealth/5.0);
		if(direction==Direction.DOWN)
			return new Herbivore(X,Y+1,MaxHealth/5.0);
		if(direction==Direction.RIGHT)
			return new Herbivore(X+1,Y,MaxHealth/5.0);
		if(direction==Direction.LEFT)
			return new Herbivore(X-1,Y,MaxHealth/5.0);
		return null;
	}
	
	/**
	 * Herbivore stands still and loses 0.1 health every tick. Because java doesn't decrease exactly 0.1 there is an if statement.
	 */
	@Override
	public void stay() {
		if (getHealth()>0.1&&getHealth()<0.1001)
			setHealth(0);
		else 
			setHealth(getHealth()-0.1);
		
	}
	
	/**
	 * Checks whether there is an attackable creature nearby.
	 * @param information information about nearby creatures
	 * @return random one of the possible attack directions.
	 */
	public static Direction isThereAnyone(LocalInformation information){
		List<Direction> creatureDirections = new ArrayList<>();
		if(information.getCreatureDown()!= null && information.getCreatureDown() instanceof Plant )
			creatureDirections.add(Direction.DOWN);
		if(information.getCreatureUp()!= null && information.getCreatureUp() instanceof Plant )
			creatureDirections.add(Direction.UP);
		if(information.getCreatureRight()!= null && information.getCreatureRight() instanceof Plant )
			creatureDirections.add(Direction.RIGHT);
		if(information.getCreatureLeft()!= null && information.getCreatureLeft() instanceof Plant )
			creatureDirections.add(Direction.LEFT);

		return LocalInformation.getRandomDirection(creatureDirections);
	}
	
}
