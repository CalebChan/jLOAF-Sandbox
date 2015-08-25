package sandbox;

public class BoxObstacle implements Obstacle{

	private int x;
	private int y;
	private int w;
	private int h;
	
	public BoxObstacle(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	@Override
	public int[][] applyObstacle(int [][] environment){
		
		for (int i = 0; i < this.w; i++){
			for (int j = 0; j < this.h; j++){
				environment[x + i][y + j] = Environment.WALL;
			}
		}
		
		return environment;
	}

}
