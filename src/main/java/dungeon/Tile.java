package dungeon;

public class Tile //TODO: do I really want to store ASCII art in a data structure? Shouldn't this be a class for UI or something
{
    String[] art;

    boolean isWall;

    public Tile()
    {
        art = new String[] {"         ","         ","         "};
        isWall = false;
    }

    public Tile(String line1, String line2, String line3, boolean newIsWall)
    {
        art = new String[] {line1,line2,line3};
        isWall = newIsWall;
    }

    public Tile(String[] newArt, boolean newIsWall)
    {
        art = newArt;
        isWall = newIsWall;
    }
}