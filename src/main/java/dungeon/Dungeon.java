package dungeon;

public class Dungeon
{
   public static void main(String[] args)
   {
      Party p = new Party();

      Character a = new Character("Dave",10,0);
      Character b = new Character("Deve",10,0);
      b.lp = 8;
      Character c = new Character("Dive",10,0);
      c.lp = 0;

      p.add(a);
      p.add(b);
      p.add(c);

      System.out.println(stringPCs(p));
   }


   public static String stringPCs(Party party)
   {
      String[][] strings = new String[party.size()][3];
      
      String toReturn = "";

      for(int i=0; i<party.size(); i++)
      {
         Character c = party.get(i);
         strings[i][0] = "|"+padString(c.name,8)+"|";
         strings[i][1] = "|"+padString("LP:"+c.lp+"/"+c.lpMax,8)+"|";
         strings[i][2] = "|"+padString("EP:"+c.ep+"/"+c.epMax,8)+"|";
      }

      for(int j=0; j<3; j++)
      {
         for(int i=0; i<party.size(); i++)
            toReturn += strings[i][j];

         toReturn += "\n";
      }

      return toReturn;

      // |Martial || Ranger || Mystic |
      // |HP:20/20||HP:10/10||HP:15/15|
      // |EP:10/10||EP:20/20||EP:15/15|

      //      |Martial || Ranger |
      //      |HP:20/20||HP:10/10|
      //      |EP:10/10||EP:20/20|

      //           |Martial |
      //           |HP:20/20|
      //           |EP:10/10|
   }

   public static String stringNPCs(Party party) //TODO: fix floating point precision
   {
      String[][] strings = new String[party.size()][2];
      
      String toReturn = "";

      for(int i=0; i<party.size(); i++)
      {
         Character c = party.get(i);
         strings[i][0] = "|"+padString(c.name,8)+"|";

         if(c.lp == c.lpMax) //If Full
            strings[i][1] = "|  FULL  |";  //TODO: idk if this is good or not. Maybe just show 100% instead?

         else if(c.lp == 0) //If Dead
            strings[i][1] = "|  DEAD  |";

         else //HP left
         {
            Integer per = Math.round((float)c.lp / (float)c.lpMax * 100);
            strings[i][1] = "|"+padString((per.toString()+"%"),8)+"|";
         }
      }

      for(int j=0; j<2; j++)
      {
         for(int i=0; i<party.size(); i++)
            toReturn += strings[i][j];

         toReturn += "\n";
      }

      return toReturn;
   }

   public static String padString(String string, int size)
   {
      if(string.length() <size) //Padding string if too short
      {
         int padSize = (size - string.length());
         String padding = " ";
         
         padding = padding.repeat(padSize/2);
         
         if(padSize % 2 == 0)
            return padding+string+padding;
         else
            return padding+string+padding+" ";
      }

      else if(string.length() >size) //Cut String if too long
      {
         return string.substring(0,size);
      }

      else return string; //String is exact length
   }
}