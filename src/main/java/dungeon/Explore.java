package dungeon; //TODO make dungeon package?

public class Explore //Dungeon scenario, with PCs exploring dungeon
{
   UI ui;
   Map map;
   Party pc;
   int x;
   int y;

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
      while(true)
      {
         round();
      }
   }

   public boolean round()
   {
      ui.explore.exploreInput(this);
      return false;
   }
}