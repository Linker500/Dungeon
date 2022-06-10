package dungeon.abilities;

import dungeon.Ability;
import dungeon.Character;
import dungeon.Party;

public class AbilityTemplate extends Ability
{    
    public AbilityTemplate()
    {
        epCost = 0; //Energy Point cost of this ability.
    }

    //Logic for action goes here.
    //This will be called if there is enough EP at the time of use on the user's turn.
    //Return a string describing what the user is doing.
    public String act(Character user, Party party, int target) 
    {
        return user.name+" attacked "+party.get(target).name+" for 5 damage!"; //Example return
    }
} 
