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
		return this.actionHistory.get(this.actionHistory.size() - 1);
	}

	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
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
