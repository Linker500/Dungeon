package dungeon;

public class Character
{
    public String name;
    
    public int lpMax; //Max Lifepoints
    public int epMax; //Max EnergyPoints
    public int lp; //Current LifePoints
    public int ep; //Current EnergyPoints

    public Character(String newName, int newLpMax, int newEpMax)
    {
        name = newName;
        lpMax = newLpMax;
        epMax = newEpMax;
        restore();
    }

    public void restore () //Full restore
    {
        lp = lpMax;
        ep = epMax;
    }
}