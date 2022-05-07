package dungeon;

import java.util.ArrayList;

//TODO: Do I really need this class? Or can I just use arraylists of party members manually?
public class Party
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

    public void restore()
    {
        for(int i=0; i< size(); i++)
            get(i).restore();
    }
}