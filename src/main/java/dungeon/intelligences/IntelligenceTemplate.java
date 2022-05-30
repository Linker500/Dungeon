package dungeon.intelligences;

import dungeon.Intelligence;
import dungeon.Character;
import dungeon.Party;

import dungeon.Action;

//Import any actions you are going to use
import dungeon.actions.ActionTemplate; 

public class IntelligenceTemplate implements Intelligence
{
    //Logic for how Character will act. Called 
    //Returns an action to be quened up and will be executed on their turn.
    public Action act(Character user, Party party, Party foes)
    {
        return new ActionTemplate(user, foes, 0); //Example action return
    }
}
