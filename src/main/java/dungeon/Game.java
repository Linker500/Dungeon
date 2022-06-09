package dungeon;

//TODO: these imports are temp
import dungeon.ui.*;
import dungeon.characters.*;

public class Game
{
    public static void main(String[] args)
    {
        //sandbox();
        
        exploreTest();
        //combatTest();
        //uiExperiment();

        System.out.println("\033[0m\n"); //Change text back to white and linebreak on program end
    }

    public static void sandbox() //Trash testing function
    {
        UI ui = new UI();
        
        ui.output("Boring Output");
        System.out.println("\nBoring Println");
        ui.reset();
        ui.output("Boring Reset");

    //**************************************************************//
        while(true){} //Just to not enter rest of code 
    }

    public static void exploreTest()
    {
        UI ui = new UI();
        UICombat uiCombat = new UICombat(ui);

        Party pc = new Party();
        pc.add(new Martial());
        pc.add(new Ranger());
        pc.add(new Mystic());

        Explore explore = new Explore(ui, pc);

        explore.start();
    }

    public static void combatTest()
    {
        UI ui = new UI();
        
        Party pc = new Party();
        pc.add(new Martial());
        pc.add(new Ranger());
        pc.add(new Mystic());     

        Party npc = new Party();
        npc.add(new Character("Mouse",2,3));
        npc.add(new Character("Rat",3,5));
        npc.add(new Character("Mouse",2,3));

        Combat combat = new Combat(ui, pc, npc);
        combat.start();
    }

    public static void uiExperiment()
    {
        System.out.print(
        "\033[90m┌\033[0m  Mouse   \033[90m┐┌\033[0m   Rat    \033[90m┐\033[31m┌\033[0m  Mouse   \033[31m┐\n"+
        "\033[90m└\033[31m   100%   \033[90m┘└\033[31m   100%   \033[90m┘\033[31m└\033[31m   100%   \033[31m┘\n"+
        "\n"+
        "\033[0m╔ \033[0mMartial  \033[0m╗\033[90m┌  \033[0mRanger  \033[90m┐┌  \033[0mMystic  \033[90m┐\n"+
        "\033[0m║ \033[31mHP:20/20 \033[0m║\033[90m│ \033[31mHP:10/10 \033[90m││ \033[31mHP:15/15 \033[90m│\n"+
        "\033[0m╚ \033[36mEP:10/10 \033[0m╝\033[90m└ \033[36mEP:20/20 \033[90m┘└ \033[36mEP:15/15 \033[90m┘\n"
        );
    }
}