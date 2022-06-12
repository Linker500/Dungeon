package dungeon.abilities;

import dungeon.Ability;
import dungeon.Character;
import dungeon.Party;

public class Deletion extends Ability  
{    
    public Deletion()
    {
        name = "Deletion";
        epCost = 1; //Energy Point cost of this ability.
    }

    public String act(Character user, Party party, int target)
    {
        party.get(target).damage(99999);
        return user.name+" literally deleted "+party.get(target).name+" lmao";
    }
}