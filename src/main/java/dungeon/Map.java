package dungeon;

public class Map
{
   Tile room = new Tile("\033[90m. . . . .\033[0m","\033[90m . . . . \033[0m","\033[90m. . . . .\033[0m",false);
   Tile wall = new Tile("\033[90m──┴───┴──\033[0m","\033[90m─┴──┴──┴─\033[0m","\033[90m──┴───┴──\033[0m",true);
   Tile oobTile = wall;

   Tile r = room;
   Tile w = wall;
   //TODO this is tempppp
   Tile map[][] = {
      {r,r,r,w,w,w,r,r},
      {w,r,r,r,r,r,r,r},
      {w,r,w,r,r,r,w,w},
      {w,r,w,w,w,r,r,w},
      {w,r,w,w,w,r,r,w},
      {w,r,r,w,w,r,r,w},
      {r,r,r,w,w,r,r,w},
      {r,r,w,w,w,r,r,w}};

   public Map()
   {

   }
   
   public Tile get(int x, int y)
   {
      try {return map[y][x];}
      catch(ArrayIndexOutOfBoundsException e)
      {
         return oobTile;
      }
   }
}