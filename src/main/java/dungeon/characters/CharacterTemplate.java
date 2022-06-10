package dungeon.characters;
import dungeon.Character;

//Import intelligence you need
import dungeon.intelligences.IntelligenceTemplate; 

public class CharacterTemplate extends Character
{
    public CharacterTemplate()
    {
        intelligence = new IntelligenceTemplate();

        name = "Name";

        str = 1; //Strength (Attack power and physical abilities)
        vit = 1; //Vitality (LP, and defensive abilities)
        agi = 1; //Agility (Iniative and precision abilities)
        foc = 1; //Focus (EP and magical abilities)

        init(); //Resets LP and EP stats, guard state, etc.
    }
}