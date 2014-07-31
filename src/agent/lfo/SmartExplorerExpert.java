package agent.lfo;

import sandbox.Creature;
import sandbox.MovementAction;
import sandbox.Obstacle;

public class SmartExplorerExpert extends SmartExpert {

	private int initX;
	private int initY;
	
	private int world[][];
	
	public SmartExplorerExpert(int size, Creature c) {
		super(size, c);
		
		initX = c.getX();
		initY = c.getY();
		
		world = new int[size][size];
		int w[][] = box.getWorld();
		for (int i = 0; i < w.length; i++){
			for (int j = 0; j < w[0].length; j++){
				if (w[i][j] == Obstacle.DIRT.ordinal()){
					world[i][j] = 1;
				}else{
					world[i][j] = 0;
				}
			}
		}
	}

	@Override
	public MovementAction testAction(Creature c) {
		// TODO Auto-generated method stub
		return null;
	}

}
