package dungeon;

import java.util.ArrayList;

public class World
{
    public ArrayList<Map> maps;

    public World()
    {
        maps = new ArrayList<Map>();
        maps.add(new Map());
    }

    public World(ArrayList<Map> newMaps)
    {
        maps = newMaps;
    }

}