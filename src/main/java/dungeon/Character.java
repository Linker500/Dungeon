package dungeon;

public class Character
{
    public String name;
    
    public int lpMax; //Max Lifepoints
    public int epMax; //Max EnergyPoints
    public int lp; //Current LifePoints
    public int ep; //Current EnergyPoints

    private Intelligence intelligence;

    public Character()
    {
        intelligence = new IntAttack();

        name = "Name";
        lpMax = 10;
        epMax = 10;
        restore();
    }

    public Character(String newName, int newLpMax, int newEpMax)
    {
        intelligence = new IntAttack();

        name = newName;
        lpMax = newLpMax;
        epMax = newEpMax;
        restore();
    }

    public Action act(Party party, Party foes)
    {
        return intelligence.act(this, party, foes);
    }

    public void heal(int healed) //Gain lp
    {
        lp += healed;

        if(lp > lpMax)
            lp = lpMax;
    }

    public void damage(int damaged) //Lose lp
    {
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

        if(ep <0)
            ep = 0;
    }

    public void restore() //Full restore
    {
        lp = lpMax;
        ep = epMax;
    }
}