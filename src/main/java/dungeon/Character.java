package dungeon;

public abstract class Character
{
    //
    int lpMax; //Max Lifepoints
    int epMax; //Max EnergyPoints
    int lp; //Current LifePoints
    int ep; //Current EnergyPoints

    public Character()
    {
        
    }

    public void restore () //Full restore
    {
        lp = lpMax;
        ep = epMax;
    }
}