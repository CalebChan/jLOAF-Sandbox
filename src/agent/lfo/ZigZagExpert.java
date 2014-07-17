
package agent.lfo;

import sandbox.Creature;
import sandbox.MovementAction;
import sandbox.sensor.Sensor;

public class ZigZagExpert extends DirtBasedAgent{
    
    private Boolean Right;
    private Boolean Down;
    private Boolean End;
    private Boolean First;
    
    public ZigZagExpert (int size, Creature c){
        super(size, c);
        Right = true;
        Down = true;
        End = false;
        First = true;
    }
    
    @Override
    public MovementAction testAction(Creature c) {
        Sensor s = c.getSensor();
        if (First) {
            First = false;
            return MovementAction.MOVE_RIGHT;
        }
        if (Right){
            if (Down){
                if (End) {
                    if ((int)s.getSense(c.getDir() + DirtBasedAgentSenseConfig.DISTANCE_SUFFIX).getValue()==-1){
                        Down = false;
                    }
                    End = false;
                    Right = false;
                    return MovementAction.MOVE_RIGHT;
                }
                else if (!End) {
                    if ((int)s.getSense(c.getDir() + DirtBasedAgentSenseConfig.DISTANCE_SUFFIX).getValue()==-1) {
                        End = true;
                        return MovementAction.MOVE_RIGHT;
                    }
                    return MovementAction.FORWARD;
                }
            }
            else if(!Down) {
                if (End) {
                    if ((int)s.getSense(c.getDir() + DirtBasedAgentSenseConfig.DISTANCE_SUFFIX).getValue()==-1){
                        Down = true;
                    }
                    End = false;
                    Right = false;
                    return MovementAction.MOVE_LEFT;
                }
                else if (!End) {
                    if ((int)s.getSense(c.getDir() + DirtBasedAgentSenseConfig.DISTANCE_SUFFIX).getValue()==-1) {
                        End = true;
                        return MovementAction.MOVE_LEFT;
                    }
                    return MovementAction.FORWARD;
                }
            }
        }
        else if (!Right){
            if (!Down){
                if (End) {
                    if ((int)s.getSense(c.getDir() + DirtBasedAgentSenseConfig.DISTANCE_SUFFIX).getValue()==-1){
                        Down = true;
                    }
                    End = false;
                    Right = true;
                    return MovementAction.MOVE_RIGHT;
                }
                else if (!End) {
                    if ((int)s.getSense(c.getDir() + DirtBasedAgentSenseConfig.DISTANCE_SUFFIX).getValue()==-1) {
                        End = true;
                        return MovementAction.MOVE_RIGHT;
                    }
                    return MovementAction.FORWARD;
                }
            }
            else if(Down) {
                if (End) {
                    if ((int)s.getSense(c.getDir() + DirtBasedAgentSenseConfig.DISTANCE_SUFFIX).getValue()==-1){
                        Down = false;
                    }
                    End = false;
                    Right = true;
                    return MovementAction.MOVE_LEFT;
                }
                else if (!End) {
                    if ((int)s.getSense(c.getDir() + DirtBasedAgentSenseConfig.DISTANCE_SUFFIX).getValue()==-1) {
                        End = true;
                        return MovementAction.MOVE_LEFT;
                    }
                    return MovementAction.FORWARD;
                }
            }
        }
    return MovementAction.STAND;
    }
}
