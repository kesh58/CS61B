package byog.Core;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;


import java.util.Random;

public class MapLauncher {
    public static void main(String[] args) {
        final int WIDTH = 80;
        final int HEIGHT = 30;
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        MapGenerator.Room_constructor(world, Tileset.FLOOR, Tileset.WALL, true);
        final long SEED = 2873123;
        final Random RANDOM = new Random(SEED);
        int tileNum = RANDOM.nextInt(3);

        for(int i = 0; i < 4000; i +=1){

            switch (tileNum) {
                case 0: MapGenerator.hallway_builder(world, Tileset.FLOOR, Tileset.WALL);
                case 1: MapGenerator.Room_constructor(world, Tileset.FLOOR, Tileset.WALL, false);
                case 2: MapGenerator.Room_constructor(world, Tileset.FLOOR, Tileset.WALL, false);
                default: MapGenerator.Room_constructor(world, Tileset.FLOOR, Tileset.WALL, false);
            }
        }
        MapGenerator.draw_door(world,RANDOM);

        ter.renderFrame(world);
    }
}
