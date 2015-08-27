package sandbox;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import sandbox.sensor.Sensor;

public class Environment {
	
	public static final int DIRT = 2;
	public static final int WALL = 1;
	
	public static final int CLOSE = 1;
	public static final int FAR = 2;
	
	private int sandboxMap[][];
	
	private List<Creature> creatureList;
	
	public Environment(int width, int height){
		sandboxMap = new int[width][height];
		this.creatureList = new ArrayList<Creature>();
	}
	
	public void addCreature(Creature c){
		this.creatureList.add(c);
	}
	
	public void addObstacle(Obstacle obstacle){
		this.sandboxMap = obstacle.applyObstacle(getEnvironment());
	}
	
	public void updateSensor(Creature c){
		if (!creatureList.contains(c)){
			return;
		}
		Sensor s = c.getSensor();
		s.updateSenses(this);
	}
	
	public boolean makeMove(MovementAction action, Creature c){
		if (!this.creatureList.contains(c)){
			return false;
		}
		
		Point canMakeMove = canMove(action, c);
		if (canMakeMove == null){
			c.updateCreature(c.getX(), c.getY(), new ActionHistory(action, false));
			return false;
		}
		c.updateCreature(canMakeMove.x, canMakeMove.y, new ActionHistory(action, true));
		if (this.sandboxMap[canMakeMove.x][canMakeMove.y] == DIRT){
			this.sandboxMap[canMakeMove.x][canMakeMove.y] = 0;
		}
		return true;
	}
	
	private Point canMove(MovementAction action, Creature c){
		if (!this.creatureList.contains(c)){
			return null;
		}
		
		Point newPoint;
		switch(action){
		case MOVE_UP:
			newPoint = new Point(c.getX(), c.getY() - 1);
			break;
		case MOVE_DOWN:
			newPoint = new Point(c.getX(), c.getY() + 1);
			break;
		case MOVE_LEFT:
			newPoint = new Point(c.getX() - 1, c.getY());
			break;
		case MOVE_RIGHT:
			newPoint = new Point(c.getX() + 1, c.getY());
			break;
		case STAND:
		default:	
			newPoint = new Point(c.getX(), c.getY());
			break;
		}
		
		if (newPoint.x < 0 || newPoint.y < 0 || newPoint.x >= this.sandboxMap.length || newPoint.y >= this.sandboxMap[0].length){
			return null;
		}
		if (this.sandboxMap[newPoint.x][newPoint.y] != WALL){
			return newPoint;
		}
		return null;
	}
	
	public void addDirt(int x, int y){
		this.sandboxMap[x][y] = DIRT;
	}
	
	public int[][] getEnvironment(){
		return sandboxMap;
	}
}
