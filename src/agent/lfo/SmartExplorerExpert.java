package agent.lfo;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import sandbox.Creature;
import sandbox.Direction;
import sandbox.Environment;
import sandbox.MovementAction;

public class SmartExplorerExpert extends SmartExpert {

	private int initX;
	private int initY;
	
	private int world[][];
	
	private ArrayList<Point> openSpaces;
	
	private Random r;
	
	public SmartExplorerExpert(int size, Creature c) {
		super(size, c);
		this.r = new Random();
		this.openSpaces = new ArrayList<Point>();
		
		initX = c.getX();
		initY = c.getY();
		
		world = new int[size][size];
		int w[][] = box.getEnvironment();
		for (int i = 0; i < w.length; i++){
			for (int j = 0; j < w[0].length; j++){
				if (w[i][j] == Environment.DIRT){
					world[i][j] = 1;
				}else{
					world[i][j] = 0;
				}
				
				if (w[i][j] != Environment.WALL){
					this.openSpaces.add(new Point(i, j));
				}
			}
		}
		
		this.openSpaces.remove(new Point(initX, initY));
	}
	
	private MovementAction selectDirection(Point newDir, Point oldDir, Direction d){
//		
//		if (newDir.getY() < oldDir.getY()){
//			switch (d){
//			case NORTH:
//				return MovementAction.MOVE_UP;
//			case SOUTH:
//				return MovementAction.MOVE_DOWN;
//			case EAST:
//				return MovementAction.MOVE_RIGHT;
//			case WEST:
//				return MovementAction.;
//			}
//		}else if (newDir.getY() > oldDir.getY()){
//			switch (d){
//			case NORTH:
//				return MovementAction.MOVE_DOWN;
//			case SOUTH:
//				return MovementAction.MOVE_UP;
//			case EAST:
//				return MovementAction.TURN_RIGHT;
//			case WEST:
//				return MovementAction.TURN_LEFT;
//			}
//		}else if (newDir.getX() > oldDir.getX()){
//			switch (d){
//			case NORTH:
//				return MovementAction.TURN_RIGHT;
//			case SOUTH:
//				return MovementAction.TURN_LEFT;
//			case EAST:
//				return MovementAction.MOVE_UP;
//			case WEST:
//				return MovementAction.MOVE_DOWN;
//			}
//		}else if (newDir.getX() < oldDir.getX()){
//			switch (d){
//			case NORTH:
//				return MovementAction.TURN_LEFT;
//			case SOUTH:
//				return MovementAction.TURN_RIGHT;
//			case EAST:
//				return MovementAction.MOVE_DOWN;
//			case WEST:
//				return MovementAction.MOVE_UP;
//			}
//		}
//		
		return MovementAction.STAND;
	}

	@Override
	public MovementAction testAction(Creature c) {
		this.openSpaces.remove(new Point(c.getX(), c.getY()));
		final Creature cc = c;
		Collections.sort(this.openSpaces, new Comparator<Point>(){
			
			@Override
			public int compare(Point o1, Point o2) {
				double d1 = o1.distance(cc.getX(), cc.getY()) * 10;
				double d2 = o2.distance(cc.getX(), cc.getY()) * 10;
				return (int) (d1 - d2);
			}
			
		});
		MovementAction action = this.nextSmartDirection(c);
		if (action != null){
			return action;
		}
		if (r.nextDouble() >= 0.75){
			int value = r.nextInt(Direction.values().length);
			return calculateMovement(c.getDir(), Direction.values()[value]);
		}else{
			return selectDirection(this.openSpaces.get(0), new Point(c.getX(), c.getY()), c.getDir());
		}
	}

}
