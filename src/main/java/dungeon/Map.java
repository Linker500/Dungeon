package dungeon;

import java.util.ArrayList;

public class Map
{
   Tile room = new Tile(". . . . ."," . . . . ",". . . . .",false);
   Tile wall = new Tile("__|___|__","_|__|__|_","__|___|__",true);
   Tile oobTile = wall;


   Tile r = room;
   Tile w = wall;
   //TODO this is tempppp
   Tile map[][] = {
      {w,r,r,w,w,w,r,r},
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