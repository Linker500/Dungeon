package dungeon;

public class Dungeon
{
   public static void main(String[] args)
   {
      //sandbox();
      
      Party pc = new Party();
         pc.add(new Character("Martial",20,10));
         pc.add(new Character("Ranger",15,15));
         pc.add(new Character("Mystic",10,20));

      Party npc = new Party();
         npc.add(new Character("Mouse",2,3));
         npc.add(new Character("Rat",3,5));
         npc.add(new Character("Mouse",2,3));

      Combat combat = new Combat(pc, npc);
      combat.start();
   }

   public static void sandbox() //Trash testing function
   {
      System.out.println("1");
      System.out.println("2");
      System.out.println("3");
      System.out.print("\033[2A\033[0J");
      System.out.println("4");
      System.out.println("5");
      while(true){} //Just to not enter rest of code 
   }

   //TODO: idk where this goes maybe in a util class? is here for now though
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

   public static String stringNPCs(Party party) //TODO: fix floating point precision?
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
      else
         return string; //String is exact length
   }
}