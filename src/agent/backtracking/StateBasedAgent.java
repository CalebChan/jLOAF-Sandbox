package agent.backtracking;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import agent.AbstractSandboxAgent;
import agent.AgentState;
import sandbox.Creature;
import sandbox.MovementAction;

public abstract class StateBasedAgent extends AbstractSandboxAgent {

	public StateBasedAgent(int size, Creature c){
		super(size, c);
		resetInternalState();
	}
	
	protected abstract void resetInternalState();
	
	public void runAgent(int iterations){
		for (int i = 0; i < iterations; i++){
			Creature c = this.creature;
			MovementAction action = testAction(c);
			boolean hasTouched = (boolean)c.getSensor().getSense(StateBasedAgentSenseConfig.TOUCH).getValue();
			double sonar = (double)c.getSensor().getSense(StateBasedAgentSenseConfig.SONAR).getValue();
			int sound = (int)c.getSensor().getSense(StateBasedAgentSenseConfig.SOUND).getValue();
			state.add(new BacktrackingAgentState(hasTouched, sonar, sound, action));
			box.makeMove(action, c);
		}
	}
	
	public void saveTrace(String filename) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
			for  (AgentState s : state){
				if (s instanceof BacktrackingAgentState){
					BacktrackingAgentState b = (BacktrackingAgentState)s;
					int touch = (b.isHasTouched()) ? 1 : 0;
					double sonar = b.getSonar();
					int sound = b.getSound();
					writer.append(touch + "|" + sonar + "|" + sound + "|" + s.getAction().toString() + "\n");
				}
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
