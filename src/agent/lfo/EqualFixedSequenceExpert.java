package agent.lfo;

import sandbox.Creature;
import sandbox.MovementAction;

public class EqualFixedSequenceExpert extends DirtBasedAgent{
    
    private int num;
    
    public EqualFixedSequenceExpert (int size, Creature c) {
        super(size, c);
        num = 0;
    }

    @Override
    public MovementAction testAction(Creature c) {
        num++;
        if (num==1)
            return MovementAction.MOVE_LEFT;
        if (num==2)
            return MovementAction.BACKWARD;
        if (num==3)
            return MovementAction.MOVE_LEFT;
        if (num==4)
            return MovementAction.STAND;
        if (num==5)
            return MovementAction.FORWARD;
        if (num==6)
            return MovementAction.MOVE_RIGHT;
        if (num==7)
            return MovementAction.BACKWARD;
        if (num==8)
            return MovementAction.STAND;
        if (num==9)
            return MovementAction.FORWARD;
        if (num==10)
            return MovementAction.MOVE_RIGHT;
        if (num==11)
            return MovementAction.STAND;
        if (num==12)
            return MovementAction.BACKWARD;
        if (num==13)
            return MovementAction.MOVE_LEFT;
        if (num==14)
            return MovementAction.FORWARD;
        num = 0;
        return MovementAction.MOVE_RIGHT;
        
    }
    
}
