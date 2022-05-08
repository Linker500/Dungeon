package dungeon;

public class IntAttack implements Intelligence
{
    public IntAttack()
    {

    }

    public Action act(Character user, Party party, Party foes) //Always attack a random party member. That's... it. For testing purposes obviously.
    {
        return new Action(user, foes, 0);
    }
}