package dungeon;

import java.util.ArrayList;

public class Party //TODO: implement iteratable?
{
    private ArrayList<Character> members;
    
    //Constructors
    public Party()
    {
        members = new ArrayList<Character>();
    }

    public Party(ArrayList<Character> newMembers)
    {
        members = newMembers;
    }

    //Methods
    public void add(Character newMember) {members.add(newMember);}
    public Character get(int number) {return members.get(number);}
    public int size() {return members.size();}

    public void restore() //Full restore LP and EP to all party members
    {
        for(int i=0; i< size(); i++)
            get(i).restore();
    }

    public boolean defeated()
    {
        boolean defeated = true; //Assume defeated until at least one party member is found alive.
        for(Character i : members)
        {
            if(i.lp > 0)
                defeated = false;
        }

        return defeated;
    }
}