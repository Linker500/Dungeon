package dungeon;

public class UIExplore
{
    UI ui;

    public UIExplore(UI newUI)
    {
        ui = newUI;
    }

    public void exploreInput(Explore e)
    {
        boolean loop = true;
        
        while(loop) //TODO unclean being here? idk
        {
            clear();
            output(stringMap(e.map, e.x,e.y));
            output("");
            output("\n\n\n"); //todo is there a more elegant logic to this? who knows.
            output("What would you like to do?\n"+
            "\033[0m(n)\033[90morth; " +
            "\033[0m(e)\033[90mast; " +
            "\033[0m(s)\033[90mouth; " +
            "\033[0m(w)\033[90mest; " +
            "\033[0m(m)\033[90menu;\u001B[0m"); //Submenus for Inventory, Skills, etc.
            String option = input();
            option.toLowerCase();
            int dY = 0;
            int dX = 0;

            switch(option)
            {
                case "n":
                    dY = -1;
                    break;

                case "e":
                    dX = 1;
                    break;

                case "s":
                    dY = 1;
                    break;

                case "w":
                    dX = -1;
                    break;
                
                case "m":
                    break;

                default:
                    output("Invalid option!");
                    wait(250);
                    break;
            }

            if(e.map.get(e.x+dX,e.y+dY).isWall)
            {
                output("Pathway blocked!");
                wait(500);
            }
            else
            {
                e.x += dX;
                e.y += dY;
            }
        }
    }

    private String stringMap(Map map, int x, int y)
    {
        //Because we can only print left to right top to bottom, a tile's string
        //must be split into 3 lines to be printed when possible.
        String[][] t = new String[9][3]; //[Tile][String #]

        int tileNumb = 0;
        for(int iY=-1; iY<=1; iY++)
        {
            for(int iX=-1; iX<=1; iX++)
            {
                String[] tile = map.get(x+iX,y+iY).art;
                t[tileNumb][0] = tile[0];
                t[tileNumb][1] = tile[1];
                t[tileNumb][2] = tile[2];
                tileNumb++;
            }
        }


        String g = "\033[90m";
        String w = "\033[0m";

        return g+"┌─────────┬─────────┬─────────┐"+
            "\n│"+w+t[0][0]+g+"│"+w+t[1][0]+g+"│"+w+t[2][0]+g+"│"+
            "\n│"+w+t[0][1]+g+"│"+w+t[1][1]+g+"│"+w+t[2][1]+g+"│"+
            "\n│"+w+t[0][2]+g+"│"+w+t[1][2]+g+"│"+w+t[2][2]+g+"│"+
            "\n├─────────┼─────────┼─────────┤"+
            "\n│"+w+t[3][0]+g+"│"+w+t[4][0]+g+"│"+w+t[5][0]+g+"│"+
            "\n│"+w+t[3][1]+g+"│"+w+t[4][1]+g+"│"+w+t[5][1]+g+"│"+
            "\n│"+w+t[3][2]+g+"│"+w+t[4][2]+g+"│"+w+t[5][2]+g+"│"+
            "\n├─────────┼─────────┼─────────┤"+
            "\n│"+w+t[6][0]+g+"│"+w+t[7][0]+g+"│"+w+t[8][0]+g+"│"+
            "\n│"+w+t[6][1]+g+"│"+w+t[7][1]+g+"│"+w+t[8][1]+g+"│"+
            "\n│"+w+t[6][2]+g+"│"+w+t[7][2]+g+"│"+w+t[8][2]+g+"│"+
            "\n└─────────┴─────────┴─────────┘"+w+"\n";

    }

    //TODO: is this a hack or is it good
    private String input() {return ui.input();}
    private void output(String string) {ui.output(string);}
    private void wait(int i) {ui.wait(i);}
    private void clear() {ui.clear();}
    private void clear(int i) {ui.clear(i);}
}