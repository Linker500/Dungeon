package dungeon;

import java.util.ArrayList;

public class Combat
{
    Party pc;
    Party npc;
    UI ui;
    int round;

    public Combat(Party newPc, Party newNpc)
    {
        pc = newPc;
        npc = newNpc;
        ui = new UI(); //TODO: don't just make this here, initialize it once for the entire game somewhere else
    }

    public void start() //TODO special logic for if PC party is defeated vs NPC party
    {
        boolean end = false;
        for(round=1; !end; round++)
            end = runRound(round);
    }

    public boolean runRound(int numb)
    {
        boolean end = false;

        ArrayList<Action> actions = ui.combatInput(this); //Quene PC Actions using player input for the entire party, ignoring specific character's ai.

        for(int i=0; i<npc.size(); i++) //Quene NPC Actions using their included ai.
        {
            if(npc.get(i).lp >0) //Only give turns to living characters. Necromancy bad. //TODO: foreach loop
                actions.add(npc.get(i).act(npc, pc));
        }

        for(Action i : actions)
        {
            if(i.user.lp > 0)
                ui.combatLog(this, i.use());
            
            if(pc.defeated() || npc.defeated())
            {
                end = true;
                break;
            }
        }

        return end;
    }
}