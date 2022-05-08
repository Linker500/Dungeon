package dungeon;

public class IntAttack implements Intelligence
{
    public IntAttack()
    {

    }

    public Action act(Party party, Party foes) //Always attack a random party member. That's... it. For testing purposes obviously.
    {
        for(int i=0; i<party.alive(); i++)
        {
            
        }
    }
}