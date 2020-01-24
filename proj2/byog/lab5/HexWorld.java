package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */

public class HexWorld {

    private static final long SEED = 2873123;
    private static final Random RANDOM = new Random(SEED);
    /*
    Position class with parameter x and y.
     */
    public static class Position {
        public int x;
        public int y;
        public Position(int px, int py){
            this.x = px;
            this.y = py;
        }
    }

    /*
    Return the width of the row.
    @param s : size of the hexagon.
    @param i : row num of the hexagon, 0 is the bottom row and 2s - 1 is the top row.
     */
    public static int hex_width(int s, int i){

        if (i >= s){
            int effectiveI = 2 * s - 1 - i;
            return effectiveI*2 + s;
        }
        else{
            return i*2 + s;
        }
    }

    /*
    Return the x position of the first tile in the row.
    @param s : size of the hexagon.
    @param i : row num of the hexagon, 0 is the bottom row and 2s - 1 is the top row.
     */
    public static int row_position(int s, int i){
        int effectiveI = i;
        if(i >= s){
            effectiveI = 2 * s - 1 - i;
        }
        return - effectiveI;
    }

    /*
    Draw a specific row i of a size s hexagon on the world on a position p.
    @param s : size of the hexagon.
    @param i : row num of the hexagon, 0 is the bottom row and 2s - 1 is the top row.
    @param p : place to draw the row.
     */
    public static void add_row(int s, int i, TETile[][] world, Position p, TETile t){
        int hex_w = hex_width(s, i);
        int P_x = p.x + row_position(s, i);
        for(int j  = 0; j < hex_w; j+=1){
            world[P_x][p.y] = t;
            P_x +=1;
        }
    }

    /*
    Adds a hexagon to the world.
    @param s : size of the hexagon.
    @param i : row num of the hexagon, 0 is the bottom row and 2s - 1 is the top row.
    @param p : place to draw the row.
     */
    public static void addHexagon(TETile[][] world, Position p, int s, TETile t){
        if (s < 2) {
            throw new IllegalArgumentException("Hexagon must be at least size 2.");
        }
        for (int i = 0; i < 2 * s; i += 1){
            add_row(s, i, world, p, t);
            int P_x = p.x;
            int P_y = p.y + 1;
            p = new Position(P_x, P_y);

        }
    }

    /*
    Picks a RANDOM tile with a 20% change of being
    a Grass, 20% chance of being a flower, 20%
    chance of being Tree, 20% chance of being a Sand,
    and 20% chance of being a mountain.
     */
    public static TETile randomTile() {
        int tileNum = RANDOM.nextInt(5);
        switch (tileNum) {
            case 0: return Tileset.GRASS;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.TREE;
            case 3: return Tileset.MOUNTAIN;
            case 4: return Tileset.SAND;
            default: return Tileset.NOTHING;
        }
    }

    /*
    Adds a hexagon to the world.
    @param s : size of the hexagon.
    @param hex_num: the number of hexagon.
    @param p : Position of the last hexagon.
     */
    public static Position Next_hex_position(int s, int hex_num, Position p){
        if (hex_num == 3 || hex_num ==16){
            int P_x = p.x + 2 * s -1;
            int P_y = p.y - 5 * s;
            Position N_p = new Position(P_x, P_y);
            return N_p;
        } else if (hex_num == 7 || hex_num == 12){
            int P_x = p.x + 2 * s -1;
            int P_y = p.y - 7 * s;
            Position N_p = new Position(P_x, P_y);
            return N_p;
        } else {
            int P_x = p.x;
            int P_y = p.y + 2 * s;
            Position N_p = new Position(P_x, P_y);
            return N_p;
        }
    }


    public static void main(String[] args) {
        final int WIDTH = 50;
        final int HEIGHT = 50;
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        Position p = new Position(10, 10);
        for (int i = 1; i <= 19; i += 1){
            TETile t = randomTile();
            addHexagon(world, p, 3, t);
            p = Next_hex_position(3, i, p);

        }

        ter.renderFrame(world);
    }
}
