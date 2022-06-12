package dungeon;

public abstract class Ability
{
    public int epCost; //Energy point cost to use action
    public String name; //Ingame name of ability

    public String use(Character user, Party party, int target)
    {
        if(user.useEP(epCost)) //Only use ability if have proper EP
            return act(user,party,target);
            
        return user.name+" does not have enough EP to use " + name + "!";
    } 

    public abstract String act(Character user, Party party, int target); //Method with unique logic to execute action

    public String toString()
    {
        return name;
    }
}