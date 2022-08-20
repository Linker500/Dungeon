package dungeon.ui;

import java.util.Scanner;
//import java.util.ArrayList;

public class UI
{
   public UIExplore explore;
   public UICombat combat;
   public UITown town;
   public Scanner in;

   private Modifier modifierGlobal; //Current color
   private Color colorGlobal; //Current modifier

   public UI()
   {
      in = new Scanner(System.in);
      explore = new UIExplore(this);
      combat = new UICombat(this);
      town = new UITown(this);
      modifierGlobal = Modifier.NORMAL;
      colorGlobal = Color.WHITE;
   }
   
   public void message(String string)
   {
      clear();
      setCol(Color.L_BLACK); output(string+"\n");
      setCol(Color.WHITE); output("Press Enter to continue...");
      //outputRaw("\u001B[90m"+string+"\u001B[0m\nPress Enter to continue...");
      input();
   }

   public void output(String string)
   {
      output(string, colorGlobal, modifierGlobal);
   }

   public void output(String string, Color color) { output(string, color, modifierGlobal); }

   public void output(String string, Modifier modifier) { output(string, colorGlobal, modifier); }

   public void output(String string, Color color, Modifier modifier)
   {
      //Escapecode + Modifier + Color + String + Reset
      String mod = modString(modifier);
      String col = colString(color); 
      System.out.print("\033"+mod+col+string+"\033[0;37m");
   }

   public void outputRaw(String string)
   {
      System.out.print(string);
   }
   
   public String input()
   {
      System.out.print("\n>");
      return in.nextLine();
   }
   public void setMod(Modifier modifier) { modifierGlobal = modifier; }

   private String modString(Modifier modifier)
   {
      switch(modifier)
      {
         case NORMAL:
            return "[0;";
         case BOLD:
            return "[1;";
         case UNDERLINE:
            return "[2;";
         default:
            return "[0;";
      }
   }

   public void setCol(Color color) { colorGlobal = color; }
   
   private String colString(Color color)
   {
      switch(color)
      {
         case BLACK:
            return "30m";
         case RED:
            return "31m";
         case GREEN:
            return "32m";
         case YELLOW:
            return "33m";
         case BLUE:
            return "34m";
         case PURPLE:
            return "35m";
         case CYAN:
            return "36m";
         case WHITE:
            return "37m";

         case L_BLACK:
            return "90m";
         case L_RED:
            return "91m";
         case L_GREEN:
            return "92m";
         case L_YELLOW:
            return "93m";
         case L_BLUE:
            return "94m";
         case L_PURPLE:
            return "95m";
         case L_CYAN:
            return "96m";
         case L_WHITE:
            return "97m";

         default:
            return "37;";
      }
   }

   public void reset()
   {
      colorGlobal = Color.WHITE;
      modifierGlobal = Modifier.NORMAL;
   }

   public void clear()
   {
      System.out.print("\033[H\033[2J");
   }

   public void clear(int lines)
   {
      System.out.print("[%s]\r\033["+lines+"A\033[0J");
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