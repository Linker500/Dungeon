package dungeon; //TODO make dungeon package?

public class Explore //Dungeon scenario, with PCs exploring dungeon
{
   enum Threat
   {
      None,
      Low,
      Medium,
      High
   }

   UI ui;
   Map map;
   Party pc;
   int x;
   int y;
   //int threat = 0; //TODO: make this an enum
   Threat threat = Threat.None;
   double encRate = 0.25; //TODO: move to map object

   

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

      while(true)
      {
         round();
      }
   }

   private boolean round()
   {
      ui.explore.exploreInput();
      raiseEncLevel();
      return false;
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
