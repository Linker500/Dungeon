package dungeon.characters;
import dungeon.Character;

//Import intelligence you need
import dungeon.intelligences.AttackSpam; 

public class Mystic extends Character
{
    public Mystic()
    {
        intelligence = new AttackSpam();

        name = "Mystic";

        str = 2; //Strength (Attack power and physical abilities)
        vit = 3; //Vitality (LP, and defensive abilities)
        agi = 4; //Agility (Iniative and precision abilities)
        foc = 5; //Focus (EP and magical abilities)

        init(); //Resets LP and EP stats, guard state, etc.
    }
}