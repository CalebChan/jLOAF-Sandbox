package agent.lfo.expert;

import agent.lfo.DirtBasedAgent;
import sandbox.Creature;
import sandbox.Environment;
import sandbox.MovementAction;

public class EqualFixedSequenceExpert extends DirtBasedAgent{
    
    private int num;
    
    public EqualFixedSequenceExpert (Creature c, Environment environment) {
        super(c, environment);
        num = 0;
    }

    @Override
    public MovementAction testAction(Creature c) {
        num++;
        if (num==1)
            return MovementAction.MOVE_LEFT;
        if (num==2)
            return MovementAction.MOVE_DOWN;
        if (num==3)
            return MovementAction.MOVE_LEFT;
        if (num==4)
            return MovementAction.STAND;
        if (num==5)
            return MovementAction.MOVE_UP;
        if (num==6)
            return MovementAction.MOVE_RIGHT;
        if (num==7)
            return MovementAction.MOVE_DOWN;
        if (num==8)
            return MovementAction.STAND;
        if (num==9)
            return MovementAction.MOVE_UP;
        if (num==10)
            return MovementAction.MOVE_RIGHT;
        if (num==11)
            return MovementAction.STAND;
        if (num==12)
            return MovementAction.MOVE_DOWN;
        if (num==13)
            return MovementAction.MOVE_LEFT;
        if (num==14)
            return MovementAction.MOVE_UP;
        num = 0;
        return MovementAction.MOVE_RIGHT;
        
    }
    
}
