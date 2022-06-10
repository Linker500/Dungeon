package dungeon.abilities;

import dungeon.Ability;
import dungeon.Character;
import dungeon.Party;

//Basic attack to one target
public class Attack extends Ability
{    
    public Attack()
    {
        epCost = 0; //Energy Point cost of this ability.
    }

    public String act(Character user, Party party, int target)
    {
        party.get(target).damage(user.str);
        return user.name+" attacked "+party.get(target).name+" for "+user.str+" damage!";
    }
}