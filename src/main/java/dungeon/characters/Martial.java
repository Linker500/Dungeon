package dungeon.characters;
import dungeon.Character;

//Import intelligence you need
import dungeon.intelligences.AttackSpam; 

public class Martial extends Character
{
    public Martial()
    {
        intelligence = new AttackSpam();

        name = "Martial";

        str = 5; //Strength (Attack power and physical abilities)
        agi = 2; //Agility (Iniative and precision abilities)
        foc = 1; //Focus (EP and magical abilities)
        vit = 3; //Vitality (LP, and defensive abilities)
    
        init(); //Resets LP and EP stats, guard state, etc.
    }
}