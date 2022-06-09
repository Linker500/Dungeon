package dungeon.ui;

import java.util.Scanner;
//import java.util.ArrayList;

public class UI
{
   //TODO backgrounds... idfk how

   //Modifiers
   private final String normal = "[0;"; //Normal
   private final String bold = "[1;"; //Bold
   private final String underline = "[4;"; //Underline

   //Colors
   private final String BLA ="30m"; //Black
   private final String RED ="31m"; //Red
   private final String GRE ="32m"; //Green
   private final String YEL ="33m"; //Yellow
   private final String BLU ="34m"; //Blue
   private final String PUR ="35m"; //Purple
   private final String CYA ="36m"; //Cyan
   private final String WHI ="37m"; //White

   private final String L_BLA ="90m"; //Light Black
   private final String L_RED ="91m"; //Light Red
   private final String L_GRE ="92m"; //Light Green
   private final String L_YEL ="93m"; //Light Yellow
   private final String L_BLU ="94m"; //Light Blue
   private final String L_PUR ="95m"; //Light Purple
   private final String L_CYA ="96m"; //Light Cyan
   private final String L_WHI ="97m"; //Light White

   public UIExplore explore;
   public UICombat combat;
   public Scanner in;

   public UI()
   {
      in = new Scanner(System.in);
      explore = new UIExplore(this);
      combat = new UICombat(this);
   }
   
   public void message(String string)
   {
      clear();
      output("\u001B[90m"+string+"\u001B[0m\nPress Enter to continue...");
      input();
   }

   public void output(String string)
   {
      //Escapecode + Modifier + Color + String
      //System.out.print("\033"+"[0;"+"30m"+string+);
      System.out.print(string);
   }
   
   public String input()
   {
      System.out.print("\n>");
      return in.nextLine();
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