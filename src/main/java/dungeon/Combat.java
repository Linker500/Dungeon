package dungeon;

import java.util.ArrayList;

public class Combat
{
    Party party1; //TODO these names are temporary as idk what will technically differentiate them at this time.
    Party party2;

    public Combat(Party newParty1, Party newParty2)
    {
        party1 = newParty1;
        party2 = newParty2;
    }

    public void start()
    {
        
    }

    public void turn()
    {
        for(int i=0; i<party1.size(); i++)
        {
            if(party1.get(i).lp > 0) //Only give turns to alive players. Necromancy bad.
            {

            }
        }

        for(int i=0; i<party2.size(); i++)
        {
            if(party2.get(i).lp >0) //Only give turns to alive players. Necromancy bad.
            {
                party1
            }
        }

        party1.act(party2);
        party2.act(party1);
    }
}