package dungeon;

public abstract class Ability
{
    public int epCost; //Energy point cost to use action

    public String use(Character user, Party party, int target)
    {
        if(user.useEP(epCost)) //Only use ability if have proper EP
            return act(user,party,target);
            
        return user.name+" does not have enough EP!";
    } 

    public abstract String act(Character user, Party party, int target); //Method with unique logic to execute action
}