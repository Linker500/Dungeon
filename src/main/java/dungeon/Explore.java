package dungeon; //TODO make dungeon package?

public class Explore //Dungeon scenario, with PCs exploring dungeon
{
   UI ui;
   Map map;
   Party pc;

   public Explore(UI newUI, Party newPC)
   {
      ui = newUI;
      pc = newPC;
      map = new Map();
   }

   public Explore(UI newUI, Map newMap, Party newPC)
   {
      ui = newUI;
      map = newMap;
      pc = newPC;
   }
}