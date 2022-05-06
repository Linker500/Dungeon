import java.util.Scanner;
public class User
{
   Scanner in;
   public User()
   {
      in = new Scanner(System.in);
   }
   
   public void output(String string)
   {
      System.out.print(string);
   }
   
   public String input()
   {
      System.out.print("\n>");
      return in.nextLine();
   }
}