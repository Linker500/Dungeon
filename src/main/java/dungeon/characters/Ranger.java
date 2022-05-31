package dungeon.characters;
import dungeon.Character;

//Import intelligence you need
import dungeon.intelligences.AttackSpam; 

public class Ranger extends Character
{
    public Ranger()
    {
        intelligence = new AttackSpam();

        name = "Ranger";

        str = 2; //Strength (Attack power and physical abilities)
        agi = 5; //Agility (Iniative and precision abilities)
        foc = 3; //Focus (EP and magical abilities)
        vit = 1; //Vitality (LP, and defensive abilities)

        init(); //Resets LP and EP stats, guard state, etc.
    }
}