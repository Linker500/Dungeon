package dungeon;

import java.util.ArrayList;

public class Combat
{
    Party pc;
    Party npc;
    UI ui;

    public Combat(Party newPc, Party newNpc)
    {
        pc = newPc;
        npc = newNpc;
        ui = new UI(); //TODO: don't just make this here, initialize it once for the entire game somewhere else
    }

    public void start()
    {
        boolean end = false;
        for(int numb=1; !end; numb++)
            end = round(numb);
    }

    public boolean round(int numb)
    {
        ArrayList<Action> actions = ui.combat(pc, npc, numb);

        //Quene PC Actions using player input for the entire party, ignoring specific character's ai.
        
        for(int i=0; i<npc.size(); i++) //Quene NPC Actions using their included ai.
        {
            if(npc.get(i).lp >0) //Only give turns to living characters. Necromancy bad. //TODO: foreach loop
            {
                actions.add(npc.get(i).act(npc, pc));
            }
        }

        for(Action i : actions)
            ui.output(i.use());

        //If either party is defeated, or if flee, return true;

        return false;
    }
}