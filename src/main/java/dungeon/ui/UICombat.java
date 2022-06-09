package dungeon.ui;

import java.util.ArrayList;

import dungeon.Combat;
import dungeon.Party;
import dungeon.Character;

import dungeon.Action;
import dungeon.actions.*; //TODO: this shouldn't be here when skills are properly implemented

public class UICombat
{
    UI ui;
    Combat c;

    public UICombat(UI newUI)
    {
        ui = newUI;
    }

    public void init(Combat newC)
    {
        c = newC;
    }

    public ArrayList<Action> combatInput()
    {
        Party pc = c.pc;
        Party npc = c.npc;
        int round = c.round;
        ArrayList<Action> actions = new ArrayList<Action>();

        for(int i=0; i<pc.size(); i++) //TODO: foreach looppppp
        {
            boolean loop = false;
            Character user = pc.get(i);

            if(user.lp > 0)
                loop = true;

            clear();
            outputRaw(stringCombat(pc, npc, round, i));
            output(user.name + "'s turn\n");
            output("Current status effects: NOT IMPLEMENTED\n", Color.L_BLACK);
            output("\n\n"); //todo is there a more elegant logic to this? who knows.
            
            while(loop) //TODO unclean being here? idk
            {
                clear(3);
                output("What would you like to do?\n");
                output("(a)"); output("ttack; ", Color.L_BLACK);
                output("(s)"); output("upport; ", Color.L_BLACK);
                output("(d)"); output("efend; ", Color.L_BLACK);
                output("(f)"); output("lee; ", Color.L_BLACK);
                output("(c)"); output("ancel;\n", Color.L_BLACK);

                String option = input();
                option.toLowerCase();

                switch(option)
                {
                    case("a"):
                    {
                        int target = selectTarget(npc); //Prompts player to select target from enemies
                        
                        if(target == -1) //If targeting was cancelled
                            break;
                        
                        Action action = new Attack(user, npc, target);
                        actions.add(action);
                        loop = false;
                        break;
                    }

                    case("d"):
                    {
                        Action action = new Defend(user, null, -1);
                        actions.add(action);
                        loop = false;
                        break;
                    }

                    case("f"):
                    {
                        output("You cannot flee!");
                        wait(1000);
                        break;
                    }

                    case("c"):
                    {
                        if(i == 0)
                            i = -1;
                        else
                        {
                            if(actions.size() != 0)
                                actions.remove(actions.size()-1);
                            i-=2;
                        }
                        loop = false;
                        break;
                    }

                    default:
                    {
                        output("Invalid option!");
                        wait(1000);
                    }
                }
            }
        }

        return actions;
    }

    public void message(String string)
    {
        clear();
        printInfo();
        output(string+"\n", Color.L_BLACK);
        output("Press Enter to continue...");
        input();
    }

    private void printInfo()
    {
        outputRaw(stringCombat(c.pc, c.npc, c.round));
    }

    public void combatLog(String caption, Action action)
    {
        clear();  
        //TODO: targeted character is always red. Change depending on what is done. If healed, blue, if cursed, purple, etc.
        printInfo();
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
                outputRaw("Target who?\n"+targets+"\033[0m(c)\033[90mancel;\033[0m");
                String input = input();
                input.toLowerCase();

                try
                {
                    target = Integer.parseInt(input);
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
                    if(input.equals("c"))
                        return -1;
                    
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
    private void output(String string, Color color) {ui.output(string, color);}
    private void output(String string, Modifier modifier) {ui.output(string, modifier);}
    private void output(String string, Color color, Modifier modifier) {ui.output(string, color, modifier);}
    private void outputRaw(String string) {ui.outputRaw(string);}
    private void setCol(Color color) {ui.setCol(color);}
    private void setMod(Modifier modifier) {ui.setMod(modifier);}
    private void wait(int i) {ui.wait(i);}
    private void clear() {ui.clear();}
    private void clear(int i) {ui.clear(i);}
}