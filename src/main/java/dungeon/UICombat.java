package dungeon;

import java.util.ArrayList;

public class UICombat
{
    UI ui;

    public UICombat(UI newUI)
    {
        ui = newUI;
    }

    public ArrayList<Action> combatInput(Combat combat)
    {
        Party pc = combat.pc;
        Party npc = combat.npc;
        int round = combat.round;
        ArrayList<Action> actions = new ArrayList<Action>();

        for(int i=0; i<pc.size(); i++) //TODO: foreach looppppp
        {
            boolean loop = false;
            if(pc.get(i).lp > 0)
            loop = true;

            clear();
            output(stringCombat(pc, npc, round));
            output(pc.get(i).name + "'s turn\n"+"\u001B[90mCurrent status effects: NOT IMPLEMENTED\033[0m\n");
            output("\n\n\n"); //todo is there a more elegant logic to this? who knows.
            
            while(loop) //TODO unclean being here? idk
            {
                clear(3);
                output("What would you like to do?\n"+
                "\033[0m(a)\033[90mttack; "+
                "\033[0m(s)\033[90mkill; "+
                "\033[0m(d)\033[90mefend; "+ 
                "\033[0m(f)\033[90mlee; "+
                "\033[0m(c)\033[90mancel;\u001B[0m");

                String option = input();
                option.toLowerCase();
                if(option.equals("a"))
                {
                    Action action;

                    int target = selectTarget(npc); //Prompts player to select target from enemies
                    action = new Action(pc.get(i), npc, target);
                    actions.add(action);
                    loop = false;
                }
                else
                {
                    output("Invalid option!");
                    wait(1000);
                }
            }
        }

        return actions;
    }

    public void combatLog(Combat combat, String caption)
    {
        clear();
        output(stringCombat(combat.pc, combat.npc, combat.round));
        wait(500);
        output(caption);
        wait(2000); //TODO: maybe make player hit enter after each message
    }

    public int selectTarget(Party party)
    {
        int target = -1;
        
        if(party.size() > 1)
        {
            boolean loop = true;
            while(loop)
            {
                clear(3);
                
                String targets = "";
                for(int i=0; i<party.size(); i++)
                    targets += (i+1)+": \033[90m"+party.get(i).name+";\033[0m ";
                output("Target who?\n"+targets);
                try
                {
                    target = Integer.parseInt(input());
                    if(target > 0 && target < party.size()+1)
                        loop = false;
                    else
                    {
                        output("Invalid input!");
                        wait(1000);
                    }
                }

                catch(Exception e)
                {
                    output("Invalid input!");
                    wait(1000);
                }
            }

            return target-1;
            
        }
        else
            return 0;
    }

    public String stringCombat(Party pc, Party npc, int round)
    {
        //TODO: both are set as "stringPCs" because the verbosity of the pc block is useful for testing
        return "Round " + round +"\n\n"+
        stringNPCs(npc)+"\n"+
        stringPCs(pc)+"\n";
    }

    public String stringPCs(Party party)
    {
        String[][] strings = new String[party.size()][3];

        String toReturn = "";

        for(int i=0; i<party.size(); i++)
        {
            Character c = party.get(i);
            strings[i][0] = "\033[90m|\033[0m"+padString(c.name,8)+"\033[90m|\033[0m";
            strings[i][1] = "\033[90m|\033[31m"+padString("LP:"+c.lp+"/"+c.lpMax,8)+"\033[90m|\033[0m";
            strings[i][2] = "\033[90m|\033[36m"+padString("EP:"+c.ep+"/"+c.epMax,8)+"\033[90m|\033[0m";
        }

        for(int j=0; j<3; j++)
        {
            for(int i=0; i<party.size(); i++)
            toReturn += strings[i][j];

            toReturn += "\n";
        }

        return toReturn;

        // |Martial || Ranger || Mystic |
        // |HP:20/20||HP:10/10||HP:15/15|
        // |EP:10/10||EP:20/20||EP:15/15|

        //      |Martial || Ranger |
        //      |HP:20/20||HP:10/10|
        //      |EP:10/10||EP:20/20|

        //           |Martial |
        //           |HP:20/20|
        //           |EP:10/10|
    }

    public String stringNPCs(Party party) //TODO: is there floating point precision errors
    {
        String[][] strings = new String[party.size()][2];
        
        String toReturn = "";

        for(int i=0; i<party.size(); i++)
        {
            Character c = party.get(i);
            strings[i][0] = "\033[90m|\033[0m"+padString(c.name,8)+"\033[90m|\033[0m";

            if(c.lp == c.lpMax && false) //If Full
            strings[i][1] = "\033[90m|\033[31m  FULL  \033[90m|\033[0m";  //TODO: idk if this is good or not. Maybe just show 100% instead?

            else if(c.lp == 0) //If Dead
            strings[i][1] = "\033[90m|  DEAD  |\033[0m";

            else //HP left
            {
            Integer per = Math.round((float)c.lp / (float)c.lpMax * 100);
            strings[i][1] = "\033[90m|\033[31m"+padString((per.toString()+"%"),8)+"\033[90m|\033[0m";
            }
        }

        for(int j=0; j<2; j++)
        {
            for(int i=0; i<party.size(); i++)
            toReturn += strings[i][j];

            toReturn += "\n";
        }

        return toReturn;
    }

    public String padString(String string, int size)
    {
        if(string.length() <size) //Padding string if too short
        {
            int padSize = (size - string.length());

            String padding = "";
            for (int i=0; i<padSize/2; i++)
            padding += " ";

            //TODO: this code with the .repeat method is nicer, but is not in old java installs. 
            //String padding = " ";
            //padding = padding.repeat(padSize/2);
            
            if(padSize % 2 == 0)
            return padding+string+padding;
            else
            return padding+string+padding+" ";
        }

        else if(string.length() >size) //Cut String if too long
        {
            return string.substring(0,size);
        }

        else return string; //String is exact length
    }

    //TODO: is this a hack or is it good
    private String input() {return ui.input();}
    private void output(String string) {ui.output(string);}
    private void wait(int i) {ui.wait(i);}
    private void clear() {ui.clear();}
    private void clear(int i) {ui.clear(i);}
}