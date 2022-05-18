package dungeon;

import java.util.ArrayList;

public class Map
{
   int map[][] = new Map[8][8];

   public Map()
   {
      map = {
      {0,1,1,0,0,0,1,1},
      {0,1,1,1,1,1,1,1},
      {0,1,0,1,1,1,0,0},
      {0,1,0,0,0,1,1,0},
      {0,1,0,0,0,1,1,0},
      {0,1,1,0,0,1,1,0},
      {1,1,1,0,0,1,1,0},
      {1,1,0,0,0,1,1,0}};  
   }
   
   public void getTile(int x, int y)
   {
      return map[y][x];
   }
}