package dungeon.actions;

import dungeon.Action;
import dungeon.Character;
import dungeon.Party;

public class ActionTemplate extends Action  
{    
    public ActionTemplate(Character nUser, Party nParty, int nTarget)
    {
        user = nUser; //The Character who uses the action
        party = nParty; //The party that is being target. Can be friend or foe.
        target = nTarget; //The integer target of the party
        epCost = 0; //Energy Point cost of this action.
    }

    //Logic for action goes here.
    //This will be called if there is enough EP at the time of use on the user's turn.
    //Return a string describing what the user is doing.
    public String act() 
    {
        return user.name+" attacked "+party.get(target).name+" for 5 damage!"; //Example return
    }
} 
