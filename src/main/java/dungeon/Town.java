package dungeon;

import dungeon.ui.UI;
import dungeon.ui.UITown;

public class Town
{
    public UI ui;
    public Party pc;

    public Town(UI newUI, Party newPC)
    {
        ui = newUI;
        pc = newPC;
    }

    public void start()
    {
        ui.town.init(this);

        ui.town.townInput();
    }
}