package dungeon;

import java.util.Scanner;
//import java.util.ArrayList;

public class UI
{
   UIExplore explore;
   UICombat combat;
   Scanner in;

   public UI()
   {
      in = new Scanner(System.in);
      explore = new UIExplore(this);
      combat = new UICombat(this);
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