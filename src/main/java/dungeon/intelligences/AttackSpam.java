package dungeon.intelligences;

import dungeon.Intelligence;
import dungeon.Character;
import dungeon.Party;

import dungeon.Action;
import dungeon.Ability;

import dungeon.abilities.Attack;

public class AttackSpam implements Intelligence
{
    public Action act(Character user, Party party, Party foes) //Always attack a random party member. That's... it. For testing purposes obviously.
    {
        //TODO: attack random person, and not an alive one
        return new Action(new Attack(), user, foes, 0);
    }
}