package dungeon.abilities;

import dungeon.Ability;
import dungeon.Character;
import dungeon.Party;

public class Defend extends Ability  
{    
    public Defend()
    {
        name = "Defend";
        epCost = 0; //Energy Point cost of this action.
    }

    public String act(Character user, Party party, int target) 
    {
        user.guard = true;
        return user.name+" defends.";
    }
} 
