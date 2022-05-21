package dungeon;

public class UIExplore
{
    UI ui;

    public UIExplore(UI newUI)
    {
        ui = newUI;
    }

    public void exploreInput(Explore explore)
    {
        Map map = new Map();

        printMap(map,2,3);

        ui.output("");
    }

    private void printMap(Map map, int x, int y)
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

        output("+---------+---------+---------+"+
            "\n|"+t[0][0]+"|"+t[1][0]+"|"+t[2][0]+"|"+
            "\n|"+t[0][1]+"|"+t[1][1]+"|"+t[2][1]+"|"+
            "\n|"+t[0][2]+"|"+t[1][2]+"|"+t[2][2]+"|"+
            "\n+---------+---------+---------+"+
            "\n|"+t[3][0]+"|"+t[4][0]+"|"+t[5][0]+"|"+
            "\n|"+t[3][1]+"|"+t[4][1]+"|"+t[5][1]+"|"+
            "\n|"+t[3][2]+"|"+t[4][2]+"|"+t[5][2]+"|"+
            "\n+---------+---------+---------+"+
            "\n|"+t[6][0]+"|"+t[7][0]+"|"+t[8][0]+"|"+
            "\n|"+t[6][1]+"|"+t[7][1]+"|"+t[8][1]+"|"+
            "\n|"+t[6][2]+"|"+t[7][2]+"|"+t[8][2]+"|"+
            "\n+---------+---------+---------+\n");

    }

    //TODO: is this a hack or is it good
    private String input() {return ui.input();}
    private void output(String string) {ui.output(string);}
    private void wait(int i) {ui.wait(i);}
    private void clear() {ui.clear();}
    private void clear(int i) {ui.clear(i);}
}