package sandbox.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import sandbox.Creature;
import sandbox.Environment;

public class EnvironmentPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Environment environment;
	
	public static final int DX = 50;
	public static final int DY = 50;
	
	private ArrayList<Creature> creature;
	
	public EnvironmentPanel(Environment e){
		this.environment = e;
		this.creature = new ArrayList<Creature>();
	}
	
	public void addCreature(Creature c){
		this.creature.add(c);
	}

	@Override
	public void paint(Graphics g){
		super.paint(g);
		
		int env[][] = this.environment.getEnvironment();
		g.setColor(Color.LIGHT_GRAY);
		g.drawLine(0, 0, 0, env[0].length * DY);
		g.drawLine(0, 0, env.length * DX, 0);
		
		for (int i = 0; i < env.length; i++){
			g.drawLine((i + 1) * DX, 0, (i + 1) * DX, env[0].length * DY);
		}
		for (int i = 0; i < env[0].length; i++){
			g.drawLine(0, (i + 1) * DY, env.length * DX, (i + 1) * DY);
		}
		
		for (int i = 0; i < env.length; i++){
			for (int j = 0; j < env[i].length; j++){
				switch(env[i][j]){
				case Environment.DIRT:
					g.setColor(Color.RED);
					g.fillRect(i * DX, j * DY, DX, DY);
					break;
				case Environment.WALL:
					g.setColor(Color.GRAY);
					g.fillRect(i * DX, j * DY, DX, DY);
					break;
				default:
					break;
				}
			}
		}
		
		List<Creature> creatureList = (creature.isEmpty()) ? this.environment.getCreatures() : creature;
		
		for (Creature c : creatureList){
			g.setColor(Color.GREEN);
			g.fillRect(c.getX() * DX, c.getY() * DY, DX, DY);
		}
	}
}
