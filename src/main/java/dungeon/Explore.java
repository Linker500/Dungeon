package dungeon;

import dungeon.ui.UI;
import dungeon.ui.UIExplore;

public class Explore //Dungeon scenario, with PCs exploring dungeon
{
   enum Threat
   {
      None,
      Low,
      Medium,
      High
   }

   public UI ui;
   public Map map;
   public Party pc;
   public int x;
   public int y;
   //int threat = 0; //TODO: make this an enum
   public Threat threat = Threat.None;
   public double encRate = 0.50; //TODO: move to map object

   public Explore(UI newUI, Party newPC)
   {
      ui = newUI;
      pc = newPC;
      map = new Map();
      x = 0;
      y = 7;
   }

   public Explore(UI newUI, Map newMap, Party newPC)
   {
      ui = newUI;
      map = newMap;
      pc = newPC;
      x = 0;
      y = 7;
   }

   public void start()
   {
      ui.explore.init(this);
      boolean loop = true;

      while(loop)
         loop = round();
      
      ui.message("Game over.");
   }

   private boolean round()
   {
      ui.explore.exploreInput();
      raiseEncLevel();

      if(pc.defeated()) //If party dies either in combat or out of it
         return false;
      
      return true;
   }

   private void raiseEncLevel()
   {
      if(Math.random() < encRate)
      {
         if(threat == Threat.High) //TODO: make a function to randomly generate encounter with encounter list data stored in map object
         {
            ui.explore.message("A wild group of monsters attacks!");

            Party npc = new Party();
            npc.add(new Character("Mouse",2,3));
            npc.add(new Character("Rat",3,5));
            npc.add(new Character("Mouse",2,3));
            Combat combat = new Combat(ui, pc, npc);
            
            combat.start();
               threat = Threat.None;
         }

         else
            threat = Threat.values()[threat.ordinal() + 1]; //Increments threat by 1
      }
   }
}
