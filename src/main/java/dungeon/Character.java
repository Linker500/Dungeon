package dungeon;

public abstract class Character
{
    String name;
    
    int lpMax; //Max Lifepoints
    int epMax; //Max EnergyPoints
    int lp; //Current LifePoints
    int ep; //Current EnergyPoints

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