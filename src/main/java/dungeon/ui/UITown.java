package dungeon.ui;

import dungeon.Town;

public class UITown
{
    UI ui;
    Town t;

    public UITown(UI newUI)
    {
        ui = newUI;
    }

    public void init(Town newT)
    {
        t = newT;
    }

    public void townInput()
    {
        boolean loop = true;
        
        while(loop) //TODO unclean being here? idk
        {
            clear();
            ui.reset();
            output("Welcome to town."+"\n\n", Color.L_BLACK);
            output("What would you like to do?\n");
            output("(h)"); output("eal; ", Color.L_BLACK);
            output("(l)"); output("eave; ", Color.L_BLACK);

            String option = input();
            option.toLowerCase();

            switch(option)
            {
                case "h":
                {
                    t.pc.restore();
                    message("Party restored!");
                    break;
                }

                case "l":
                    break;

                default:
                    output("Invalid option!");
                    wait(250);
                    break;
            }
        }
    }

    public void message(String string)
    {
        clear();
        printInfo();
        output(string+"\n", Color.L_BLACK);
        output("Press Enter to continue...");
        input();
    }

    private void printInfo()
    {
        outputRaw("");
    }

    //TODO: is this a hack or is it good
    private String input() {return ui.input();}
    private void output(String string) {ui.output(string);}
    private void output(String string, Color color) {ui.output(string, color);}
    private void output(String string, Modifier modifier) {ui.output(string, modifier);}
    private void output(String string, Color color, Modifier modifier) {ui.output(string, color, modifier);}
    private void outputRaw(String string) {ui.outputRaw(string);}
    private void setCol(Color color) {ui.setCol(color);}
    private void setMod(Modifier modifier) {ui.setMod(modifier);}
    private void wait(int i) {ui.wait(i);}
    private void clear() {ui.clear();}
    private void clear(int i) {ui.clear(i);}
}