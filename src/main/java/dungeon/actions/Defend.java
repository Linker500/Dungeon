package dungeon.actions;

import dungeon.Action;
import dungeon.Character;
import dungeon.Party;

public class Defend extends Action  
{    
    public Defend(Character nUser, Party nParty, int nTarget)
    {
        user = nUser; //The Character who uses the action
        party = nParty; //The party that is being target. Can be friend or foe.
        target = nTarget; //The integer target of the party
        epCost = 0; //Energy Point cost of this action.
    }

    public String act() 
    {
        user.guard = true;
        return user.name+" defends.";
    }
} 
