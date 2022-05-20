package dungeon;

public class Game
{
    public static void main(String[] args)
    {
        //sandbox();
        dungeonTest();
    }

    public static void dungeonTest()
    {
        UI ui = new UI();
        Party pc = new Party();
        pc.add(new Character("Martial",20,10));
        pc.add(new Character("Ranger",15,15));
        pc.add(new Character("Mystic",10,20));

        Explore explore = new Explore(ui, pc);
    }

    public static void combatTest()
    {
        UI ui = new UI();
        
        Party pc = new Party();
        pc.add(new Character("Martial",20,10));
        pc.add(new Character("Ranger",15,15));
        pc.add(new Character("Mystic",10,20));
        
        Party npc = new Party();
        npc.add(new Character("Mouse",2,3));
        npc.add(new Character("Rat",3,5));
        npc.add(new Character("Mouse",2,3));

        Combat combat = new Combat(ui, pc, npc);
        combat.start();
    }

    public static void sandbox() //Trash testing function
    {



    //**************************************************************//
        while(true){} //Just to not enter rest of code 
    }
}