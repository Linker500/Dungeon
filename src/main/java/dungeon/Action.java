package dungeon;

public class Action
{
    public Ability ability; //Ability being used

    public Character user; //Person using said action
    public Party party; //Party that is being target. Usually opponent party, but can be allied one as well.
    public int target; //Which member of party to affect

    public Action(Ability newAbility, Character nUser, Party nParty, int nTarget)
    {
        ability = newAbility;
        user = nUser;
        party = nParty;
        target = nTarget;
    }

    public String act()
    {
        return ability.use(user,party,target);
    }

    //Standard data return
    public Character getUser() { return user; }
    public Party getParty() { return party; }
    public int getTarget() { return target; }
}