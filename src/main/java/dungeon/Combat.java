package dungeon; //TODO make combat package?

import java.util.ArrayList;

public class Combat //Combat scenario. Two partyies fighting.
                    //Most likely PC vs NPC, but NPC v NPC is possible too.
{
    UI ui;
    Party pc;
    Party npc;
    int round;

    public Combat(UI newUI, Party newPc, Party newNpc)
    {
        pc = newPc;
        npc = newNpc;
        ui = newUI;
    }

    public boolean start() //TODO special logic for if PC party is defeated vs NPC party
    {
        ui.combat.init(this);

        boolean end = false;
        for(round=1; !end; round++)
            end = runRound(round);
        
        if(pc.defeated())
        {
            ui.combat.message("Your party has fallen...");
            return false;
        }
        
        return true;
    }

    private boolean runRound(int numb)
    {
        boolean end = false;

        ArrayList<Action> actions = ui.combat.combatInput(); //Quene PC Actions using player input for the entire party, ignoring specific character's ai.

        for(int i=0; i<npc.size(); i++) //Quene NPC Actions using their included ai.
        {
            if(npc.get(i).lp >0) //Only give turns to living characters. Necromancy bad. //TODO: foreach loop
                actions.add(npc.get(i).act(npc, pc));
        }

        for(Action i : actions)
        {
            if(i.getUser().lp > 0)
                ui.combat.combatLog(i.use(), i); //TODO; this and ui.combat.combatInput() dumb. Should just be ui.combat.log()?
            
            if(pc.defeated() || npc.defeated())
            {
                end = true;
                break;
            }
        }

        for(int i=0; i<pc.size(); i++) //TODO: foreach loop
            pc.get(i).endTurn();

        for(int i=0; i<npc.size(); i++)
            npc.get(i).endTurn();

        return end;
    }
}