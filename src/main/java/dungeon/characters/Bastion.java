package dungeon.characters;
import dungeon.Character;

//Import intelligence you need
import dungeon.intelligences.AttackSpam; 

public class Bastion extends Character
{
    public Bastion()
    {
        intelligence = new AttackSpam();

        name = "Bastion";

        str = 4; //Strength (Attack power and physical abilities)
        vit = 5; //Vitality (LP, and defensive abilities)
        agi = 2; //Agility (Iniative and precision abilities)
        foc = 3; //Focus (EP and magical abilities)

        init(); //Resets LP and EP stats, guard state, etc.
    }
}