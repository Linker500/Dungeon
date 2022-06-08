package dungeon;
import dungeon.intelligences.AttackSpam;

public class Character
{
    public String name;
    public Party party;

    public int lpMax; //Max Lifepoints
    public int epMax; //Max EnergyPoints
    public int lp; //Current LifePoints
    public int ep; //Current EnergyPoints

    public int str; //Strength (Attack power and physical abilities)
    public int agi; //Agility (Iniative and precision abilities)
    public int foc; //Focus (EP and magical abilities)
    public int vit; //Vitality (LP, and defensive abilities)

    public boolean guard; //If character is guarding. Static halving of all damage taken post damage reduction

    public Intelligence intelligence;

    public Character()
    {
        intelligence = new AttackSpam();

        str = 1;
        agi = 1;
        foc = 1;
        vit = 1;

        init();
    }

    public Character(String newName, int newLpMax, int newEpMax)
    {
        intelligence = new AttackSpam();

        str = 1;
        agi = 1;
        foc = 1;
        vit = 1;

        name = newName;
        lpMax = newLpMax;
        epMax = newEpMax;
        
        init();
    }

    public void init()
    {
        lpMax = vit * 5;
        epMax = foc * 5;

        restore();
        guard = false;
    }

    public Action act(Party party, Party foes)
    {
        return intelligence.act(this, party, foes);
    }

    public void endTurn() //Calls upon things end of turn, like reseting guard, and checking status effects (usually to just increment timer)
    {
        guard = false;
    }

    public void heal(int healed) //Gain lp
    {
        lp += healed;

        if(lp > lpMax)
            lp = lpMax;
    }

    public void damage(int damaged) //Lose lp
    {
        if(guard)
            lp -= damaged/2;
        else
            lp -= damaged;

        if(lp < 0)
            lp = 0;
    }

    public void refresh(int refreshed) //Gain ep
    {
        ep += refreshed;

        if(ep > epMax)
            ep = epMax;
    }

    public void drain(int drained) //Lose ep
    {
        ep -= drained;

        if(ep < 0)
            ep = 0;
    }

    //Attempts to drain Energy Points, and returns true if successful
    //Meant for ability usage
    public boolean useEP(int drained)
    {
        if(ep < drained)
            return false;

        ep -= drained;
        return true;
    }

    public void restore() //Full restore
    {
        lp = lpMax;
        ep = epMax;
    }

    public void neutralize() //Remove all buffs and debuffs
    {

    }
}