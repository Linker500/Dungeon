package dungeon;

public abstract class Action
{
    public Character user; //Person using said action
    public Party party; //Party that is being target. Usually opponent party, but can be allied one as well.
    public int target; //Which member of party to affect
    public int epCost; //Energy point cost to use action

    public String use()
    {
        if(user.useEP(epCost)) //Only use ability if have proper EP
            return act();
            
        return user.name+" does not have enough EP!";
    } 

    public abstract String act(); //Method with unique logic to execute action

    //Standard data return
    public Character getUser() { return user; }
    public Party getParty() { return party; }
    public int getTarget() { return target; }
}