package dungeon;

public interface Intelligence
{
    public Action act(Character user, Party party, Party foes);
}