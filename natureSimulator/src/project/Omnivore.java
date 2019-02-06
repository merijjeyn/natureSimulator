package project;

import game.Direction;
import naturesimulator.Action;
import naturesimulator.LocalInformation;
import ui.GridPanel;

public class Omnivore extends Creature {
	private static final double MaxHealth = 50;

	public Omnivore(int x, int y, double health) {
		super(x, y, 20);
	}

	@Override
	public void draw(GridPanel panel) {
		// TODO Auto-generated method stub

	}

	@Override
	public Action chooseAction(LocalInformation information) {
		// TODO Auto-generated method stub
		return null;
	}

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

	@Override
	public void stay() {
		if (getHealth()>0.1&&getHealth()<0.1001)
			setHealth(0);
		else 
			setHealth(getHealth()-0.1);
		
	}

}
