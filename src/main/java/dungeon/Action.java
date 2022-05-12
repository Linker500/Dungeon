package dungeon;

public class Action
{
    Character user; //Person using said action
    Party party; //Party that is being target. Usually opponent party, but can be allied one as well.
    int target; //Which member of party to affect


    public Action(Character nUser, Party nParty, int nTarget)
    {
        user = nUser;
        party = nParty;
        target = nTarget;
    }

    public String use() //TODO: this class needs to be interface or abstract for other actions (attack, defend, skills, etc, this is hardcoded to attack for testing though)
    {
        user.drain(1);
        party.get(target).damage(5);
        return user.name+" attacked "+party.get(target).name+" for 5 damage!";
    }
}