package dungeon.actions;

import dungeon.Action;
import dungeon.Character;
import dungeon.Party;

//Basic attack to one target
public class Attack extends Action  
{    
    public Attack(Character nUser, Party nParty, int nTarget)
    {
        user = nUser;
        party = nParty;
        target = nTarget;
        epCost = 1; //TODO: this should be free when done with testing
    }

    public String act()
    {
        party.get(target).damage(5);
        return user.name+" attacked "+party.get(target).name+" for 5 damage!";
    }
}