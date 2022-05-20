package dungeon; //TODO make dungeon package?

public class Dungeon //Dungeon scenario, with PCs exploring dungeon
{
   UI ui;
   Map map;
   Party pc;

   public Dungeon(UI newUI, Party newPC)
   {
      ui = newUI;
      pc = newPC;
      map = new Map();
   }

   public Dungeon(UI newUI, Map newMap, Party newPC)
   {
      ui = newUI;
      map = newMap;
      pc = newPC;
   }
}