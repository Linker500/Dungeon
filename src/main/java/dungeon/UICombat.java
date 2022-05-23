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
            output(stringCombat(pc, npc, round, i));
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

    public void combatLog(Combat combat, String caption, Action action)
    {
        clear();
        
        //TODO: targeted character is always red. Change depending on what is done. If healed, blue, if cursed, purple, etc.
        output("Round " + combat.round +"\n\n");

        output(stringCombat(combat.pc, combat.npc, combat.round));

        //TODO: idfk if this logic sucks or not it feels awful having to calculate what party the action is coming from after tossing that info away

        output(stringNPCs(combat.npc,));
        output(stringPCs(combat.pc));
        
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
        return stringCombat(pc,npc,round,-1);
    }

    public String stringCombat(Party pc, Party npc, int round, int member) //TODO: this can't give fine enough control for all the details maybe get rid of this and just use split functions
    {
        return "Round " + round +"\n\n"+
        stringNPCs(npc)+"\n"+
        stringPCs(pc,member,"\033[0m",true)+"\n";
    }

    public String stringPCs(Party party, int member, String color, boolean bold) //TODO: perhaps make ansi color object to pass
    {
        String[][] strings = new String[party.size()][3];

        String toReturn = "";

        for(int i=0; i<party.size(); i++)
        {
            Character c = party.get(i);

            if(i == member) //special coloring/bold for specific active party member
            {
                if(bold)
                {
                    strings[i][0] = color+"╔ \033[0m"+padString(c.name,8)+color+" ╗\033[0m";
                    strings[i][1] = color+"║ \033[31m"+padString("LP:"+c.lp+"/"+c.lpMax,8)+color+" ║\033[0m";
                    strings[i][2] = color+"╚ \033[36m"+padString("EP:"+c.ep+"/"+c.epMax,8)+color+" ╝\033[0m";
                }
                else
                {
                    strings[i][0] = color+"┌ \033[0m"+padString(c.name,8)+color+" ┐\033[0m";
                    strings[i][1] = color+"│ \033[31m"+padString("LP:"+c.lp+"/"+c.lpMax,8)+color+" │\033[0m";
                    strings[i][2] = color+"└ \033[36m"+padString("EP:"+c.ep+"/"+c.epMax,8)+color+" ┘\033[0m";
                }
            }
            else //Everyone else
            {
                strings[i][0] = "\033[90m┌ \033[0m"+padString(c.name,8)+"\033[90m ┐\033[0m";
                strings[i][1] = "\033[90m│ \033[31m"+padString("LP:"+c.lp+"/"+c.lpMax,8)+"\033[90m │\033[0m";
                strings[i][2] = "\033[90m└ \033[36m"+padString("EP:"+c.ep+"/"+c.epMax,8)+"\033[90m ┘\033[0m";

            }
        }

        for(int j=0; j<3; j++)
        {
            for(int i=0; i<party.size(); i++)
            toReturn += strings[i][j];

            toReturn += "\n";
        }

        return toReturn;

        // ┌ Martial  ┐┌  Ranger  ┐┌  Mystic  ┐
        // │ HP:20/20 ││ HP:10/10 ││ HP:15/15 │
        // └ EP:10/10 ┘└ EP:20/20 ┘└ EP:15/15 ┘

        //       ┌ Martial  ┐┌  Ranger  ┐
        //       │ HP:20/20 ││ HP:10/10 │
        //       └ EP:10/10 ┘└ EP:20/20 ┘

        //             ┌ Martial  ┐
        //             │ HP:20/20 │
        //             └ EP:10/10 ┘
    }

    public String stringPCs(Party party)
    {
        return stringPCs(party, -1, null, false);
    }

    public String stringNPCs(Party party, int member, String color, boolean bold) //TODO: is there floating point precision errors
    {
        String[][] strings = new String[party.size()][2];
        
        String toReturn = "";

        for(int i=0; i<party.size(); i++)
        {
            Character c = party.get(i);
            String hp;

            if(c.lp == c.lpMax && false) //If Full
                hp = "  FULL  ";  //TODO: disabled right now idk if this is good or not. Maybe just show 100% instead?

            else if(c.lp == 0) //If Dead
                hp = "  DEAD  ";

            else //HP left
            {
                Integer per = Math.round((float)c.lp / (float)c.lpMax * 100);
                hp = per.toString()+"%";
            }

            if(i == member) //special coloring/bold for specific active party member
            {
                if(bold)
                {
                    strings[i][0] = color+"╔ \033[0m"+padString(c.name,8)+color+" ╗\033[0m";
                    strings[i][1] = color+"╚ \033[31m"+padString(hp,8)+color+" ╝\033[0m";
                }
                else
                {
                    strings[i][0] = color+"┌ \033[0m"+padString(c.name,8)+color+" ┐\033[0m";
                    strings[i][1] = color+"└ \033[31m"+padString(hp,8)+color+" ┘\033[0m";
                }
            }
            else //Everyone else
            {
                strings[i][0] = "\033[90m┌ \033[0m"+padString(c.name,8)+"\033[90m ┐\033[0m";
                strings[i][1] = "\033[90m└ \033[31m"+padString(hp,8)+"\033[90m ┘\033[0m";
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

    public String stringNPCs(Party party)
    {
        return stringNPCs(party, -1, null, false);
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