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
        vit = 4; //Vitality (LP, and defensive abilities)
        agi = 3; //Agility (Iniative and precision abilities)
        foc = 2; //Focus (EP and magical abilities)
    
        init(); //Resets LP and EP stats, guard state, etc.
    }
}