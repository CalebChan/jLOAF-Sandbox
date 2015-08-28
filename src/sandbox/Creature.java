package sandbox;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import sandbox.sensor.Sensor;

public abstract class Creature implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	private Direction dir;
	
	protected Sensor sensor;
	
	protected List<ActionHistory> actionHistory;
	
	public Creature(Creature c){
		this(c.x, c.y, c.dir);
	}
	
	public Creature(int x, int y, Direction dir){
		this.x = x;
		this.y = y;
		this.dir = dir;
		
		this.actionHistory = new ArrayList<ActionHistory>();
	}
	
	public abstract Sensor getSensor();
	
	public void addAction(ActionHistory action){
		this.actionHistory.add(action);
	}
	
	public ActionHistory getLastActionHistory(){
		if (this.actionHistory.isEmpty()){
			return null;
		}
		return this.actionHistory.get(this.actionHistory.size() - 1);
	}

	/**
	 * This method will move the creature to that location without updating the action history
	 * @param x
	 * @param y
	 */
	public void moveCreature(int x, int y, Direction d){
		this.x = x;
		this.y = y;
		this.dir = d;
	}
	
	public void updateCreature(int x, int y, ActionHistory action){
		moveCreature(x, y, this.dir);
		addAction(action);
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public Direction getDir() {
		return dir;
	}
	public void setDir(Direction dir) {
		this.dir = dir;
	}
	
	@Override
	public String toString(){
		return "X:" + x + ",Y:" + y + ",D:" + dir.toString();
	}
	
	@Override
	public boolean equals(Object o){
		if (!(o instanceof Creature)){
			return false;
		}
		Creature c = (Creature)o;
		if (this.x != c.x || this.y != c.y || !this.dir.equals(c.dir)){
			return false;
		}
		return true;
	}
	
}
