package dungeon;

import java.util.ArrayList;

public class Combat
{
    Party PC; //TODO these names are temporary as idk what will technically differentiate them at this time.
    Party NPC;

    public Combat(PCParty newPC, NPCParty newNPC)
    {
        PC = newPC;
        NPC = newNPC;
    }

    public void start()
    {
        
    }

    public void turn()
    {

        ArrayList
        for(int i=0; i<NPC.size(); i++) //Quene NPC ACtions
        {
            if(NPC.get(i).lp >0) //Only give turns to living players. Necromancy bad.
            {
                NPC.get(i).act();
            }
        }
    }
}