package dungeon;

import java.util.Scanner;
import java.util.ArrayList;

public class UI
{
   Scanner in;

   public UI()
   {
      in = new Scanner(System.in);
   }

   public ArrayList<Action> combat(Party pc, Party npc, int round) //TODO: this class is redundant and sphaghetti logic. plz fix
   {
      ArrayList<Action> actions = new ArrayList<Action>();

      for(int i=0; i<pc.size(); i++) //TODO: foreach looppppp
      {
         boolean loop = false;
         if(pc.get(i).lp > 0)
            loop = true;

         while(loop)
         {
            clear();
            output(stringCombat(pc.get(i), pc, npc, round));
            output("What would you like to do?\n(a)ttack; (s)kill; (d)efend; (f)lee; (c)ancel;");

            String option = input();
            option.toLowerCase();
            if(option.equals("a"))
            {
               Action action;

               if(npc.size() > 1) //TODO: target select should be a function and not hardcoded for attack as skills will need it
               {
                  int target = -1;
                  boolean loop2 = true;
                  while(loop2)
                  {
                     clear();
                     output(stringCombat(pc.get(i), pc, npc, round));
                     
                     String targets = "";
                     for(int j=1; j<npc.size()+1; j++)
                        targets += j+"; ";
                     output("Attack which target?\n"+targets);
                     try
                     {
                        target = inputInt();
                        if(target > 0 && target < npc.size()+1)
                           loop2 = false;
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

                  action = new Action(pc.get(i), npc, target-1);
                  
               }
               else
                  action = new Action(pc.get(i), npc, 0);

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
   
   public int selectTarget(Party party)
   {
      return 1;
   }
   
   public void output(String string)
   {
      System.out.print(string);
   }
   
   public String input()
   {
      System.out.print("\n>");
      return in.nextLine();
   }

   public int inputInt()
   {
      System.out.print("\n>");
      return in.nextInt();
   }

   public void clear()
   {
      System.out.print("\033[H\033[2J");
   }

   public void temp() //Car return
   {
      System.out.print("[%s]\r")
   }

   //--------------------------------------------------------\\

   public String stringCombat(Character character, Party pc, Party npc, int round)
   {
      //TODO: both are set as "stringPCs" because the verbosity of the pc block is useful for testing
      return "Round " + round +"\n"+
      stringPCs(npc)+"\n"+
      stringPCs(pc)+"\n"+
      character.name + "'s turn\n"+
      "Current status effects: NOT IMPLEMENTED\n";
   }

   public String stringPCs(Party party)
   {
      String[][] strings = new String[party.size()][3];
      
      String toReturn = "";

      for(int i=0; i<party.size(); i++)
      {
         Character c = party.get(i);
         strings[i][0] = "|"+padString(c.name,8)+"|";
         strings[i][1] = "|"+padString("LP:"+c.lp+"/"+c.lpMax,8)+"|";
         strings[i][2] = "|"+padString("EP:"+c.ep+"/"+c.epMax,8)+"|";
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

   public String stringNPCs(Party party) //TODO: fix floating point precision?
   {
      String[][] strings = new String[party.size()][2];
      
      String toReturn = "";

      for(int i=0; i<party.size(); i++)
      {
         Character c = party.get(i);
         strings[i][0] = "|"+padString(c.name,8)+"|";

         if(c.lp == c.lpMax) //If Full
            strings[i][1] = "|  FULL  |";  //TODO: idk if this is good or not. Maybe just show 100% instead?

         else if(c.lp == 0) //If Dead
            strings[i][1] = "|  DEAD  |";

         else //HP left
         {
            Integer per = Math.round((float)c.lp / (float)c.lpMax * 100);
            strings[i][1] = "|"+padString((per.toString()+"%"),8)+"|";
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
         String padding = " ";
         
         padding = padding.repeat(padSize/2);
         
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

   public void wait(int mill)
   {
      try
      {
         Thread.sleep(mill);
      }
      catch (Exception e)
      {
         System.out.println(e);
      }
   }
}