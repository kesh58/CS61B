package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().
        String inp = input.toLowerCase();
        TETile[][] finalWorldFrame;


        if(inp.startsWith("n") && inp.endsWith("s")){
            Long input_long;
            int start = inp.indexOf("n") + 1;
            int end = inp.indexOf("s");

            try {
                input_long = Long.parseLong(inp.substring(start,end));
            } catch(Exception e) {
                throw new RuntimeException("Seed has to be an integer but you input: " + input.substring(start, end) + ".");
            }

            ter.initialize(WIDTH, HEIGHT);
            finalWorldFrame = new TETile[WIDTH][HEIGHT];
            for (int x = 0; x < WIDTH; x += 1) {
                for (int y = 0; y < HEIGHT; y += 1) {
                    finalWorldFrame[x][y] = Tileset.NOTHING;
                }
            }

            MapGenerator.Room_constructor(finalWorldFrame, Tileset.FLOOR, Tileset.WALL, true);
            final long SEED = input_long;
            final Random RANDOM = new Random(SEED);
            int tileNum = RANDOM.nextInt(3);

            for(int i = 0; i < 4000; i +=1){
                switch (tileNum) {
                    case 0: MapGenerator.hallway_builder(finalWorldFrame, Tileset.FLOOR, Tileset.WALL);
                    case 1: MapGenerator.Room_constructor(finalWorldFrame, Tileset.FLOOR, Tileset.WALL, false);
                    case 2: MapGenerator.Room_constructor(finalWorldFrame, Tileset.FLOOR, Tileset.WALL, false);
                    default: MapGenerator.Room_constructor(finalWorldFrame, Tileset.FLOOR, Tileset.WALL, false);
                }
            }
            MapGenerator.draw_door(finalWorldFrame,RANDOM);

            ter.renderFrame(finalWorldFrame);
        } else {
            throw new RuntimeException("You must put a string start with 'n/N' and end with 's/S'.");
        }
        return finalWorldFrame;
    }
}
