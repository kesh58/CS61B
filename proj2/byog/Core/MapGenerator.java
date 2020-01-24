package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import java.util.Random;

public class MapGenerator {
    /*
    1. hallway must be connect with another hallway or room.
    2. Room must be connect with hallway.

    - generate a bunch of rooms that are not overlapping.
    - generate some hallways that are at least connect with one room or one other hallway.
    - remove the room or the hallway that are not connected.
     */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;

    /*
    Position class with parameter x and y.
     */
    public static class Position {
        public int x;
        public int y;

        public Position(int px, int py) {
            this.x = px;
            this.y = py;
        }
    }

    /* room class with parameter height, width
    and lower left corner position p.
     */
    public static class room {
        public int height;
        public int width;
        public Position p;

        // room constructor;
        public room(int h, int w, Position p) {
            this.height = h;
            this.width = w;
            this.p = p;
        }
    }

    /*
    hallway class with parameter length;
    @param dir: true for horizontal and false for vertical;
    @param p: bottom end or left end of the hallway.
     */
    public static class hallway {
        public int length;
        public boolean dir;
        public Position p;

        // hallway constructor;
        public hallway(int l, boolean bol, Position p) {
            this.length = l;
            this.dir = bol;
            this.p = p;
        }
    }

    /*
    Construct a room on the world with given room r,
    room space TETile s, wall TETile w.
     */
    public static void Room_constructor(TETile[][] world, TETile s, TETile w, boolean first) {
        // generate a random width and height for the room.
        Random random = new Random();

        int RoomWidth = RandomUtils.uniform(random, 2, 7);
        int RoomHeight = RandomUtils.uniform(random, 2, 7);

        // generate a random position for room.
        int P_x = RandomUtils.uniform(random, 1, WIDTH - RoomWidth);
        int P_y = RandomUtils.uniform(random, 1, HEIGHT - RoomHeight);
        Position p_r = new Position(P_x, P_y);
        room r = new room(RoomHeight, RoomWidth, p_r);



        if (checkroom(r, world) || first) {
            /*
            @param R_x = x value of bottom left point of room floor.
            @param R_y = y value of bottom left point of room floor.
            @param W_x = x value of bottom left point of room wall.
            @param W_y = y value of bottom left point of room wall.
             */
            int R_x;
            int R_y = r.p.y;
            int W_x = r.p.x - 1;
            int W_y = r.p.y - 1;

        /*
        Draw the room floor.
         */
            for (int i = 0; i < r.height; i += 1) {
                R_x = r.p.x;
                for (int j = 0; j < r.width; j += 1) {
                    world[R_x][R_y] = s;
                    R_x += 1;
                }
                R_y += 1;
            }

        /*
        generate the walls of four sides of the room.
         */
            for (int i = 0; i < r.height + 2; i += 1) {
                if (world[W_x][W_y] == Tileset.NOTHING){
                    world[W_x][W_y] = w;
                }
                W_y += 1;
            }
            W_y -= 1;
            for (int i = 0; i < r.width + 1; i += 1) {
                W_x += 1;
                if (world[W_x][W_y] == Tileset.NOTHING){
                    world[W_x][W_y] = w;
                }
            }
            for (int i = 0; i < r.height + 1; i += 1) {
                W_y -= 1;
                if (world[W_x][W_y] == Tileset.NOTHING){
                    world[W_x][W_y] = w;
                }
            }
            for (int i = 0; i < r.width; i += 1) {
                W_x -= 1;
                if (world[W_x][W_y] == Tileset.NOTHING){
                    world[W_x][W_y] = w;
                }
            }
        }
    }

    /*
    check if the room is legal to be placed on world.
     */
    private static boolean checkroom(room c, TETile[][] world) {
        int P_x = c.p.x;
        int P_y = c.p.y;
        if (P_x + c.width + 1 > WIDTH || P_y + c.height + 1> HEIGHT) {
            return false;
        }
        for (int i = 0; i < c.height - 1; i += 1) {
            for (int j = 0; j < c.width - 1; j += 1) {
                if (world[P_x + j][P_y + i] != Tileset.NOTHING) {
                    return false;
                }
            }
        }
        /*
        check if the room is contact with the hallway at some point of its wall position.
         */
        int contact_point = 0;
        int W_x = c.p.x - 1;
        int W_y = c.p.y - 1;
        for (int i = 0; i < c.height; i += 1) {
            if(world[W_x][W_y + 1] == Tileset.FLOOR && world[W_x][W_y - 1] == Tileset.WALL && world[W_x][W_y + 1] == Tileset.WALL){
                contact_point += 1;
            }
            W_y += 1;
        }
        W_y += 1;
        for (int i = 0; i < c.width; i += 1) {
            W_x += 1;
            if(world[W_x][W_y] == Tileset.FLOOR && world[W_x - 1][W_y] == Tileset.WALL && world[W_x + 1][W_y] == Tileset.WALL){
                contact_point += 1;
            }
        }
        W_x += 1;
        for (int i = 0; i < c.height ; i += 1) {
            W_y -= 1;
            if(world[W_x][W_y] == Tileset.FLOOR && world[W_x][W_y - 1] == Tileset.WALL && world[W_x][W_y + 1] == Tileset.WALL){
                contact_point += 1;
            }
        }
        W_y -= 1;
        for (int i = 0; i < c.width; i += 1) {
            W_x -= 1;
            if(world[W_x][W_y] == Tileset.FLOOR && world[W_x - 1][W_y] == Tileset.WALL && world[W_x + 1][W_y] == Tileset.WALL){
                contact_point += 1;
            }
        }
        return contact_point == 1;
    }

    public static void hallway_builder(TETile[][] world, TETile t, TETile w) {
        // generate a random hallway length;
        Random random = new Random();
        int hallwayLength = RandomUtils.uniform(random, 2, 10);

        //generate a random hallway direction.
        boolean dir = false;
        if (RandomUtils.uniform(random) > 0.5) {
            dir = true;
        }

        // generate a random position for hallway.
        int P_x = RandomUtils.uniform(random, 1, WIDTH - 1);
        int P_y = RandomUtils.uniform(random, 1, HEIGHT - 1);
        Position p_r = new Position(P_x, P_y);
        hallway h = new hallway(hallwayLength, dir, p_r);

        //Draw only if the hallway is legal on the world
        if (check_hallway(h, world)) {

            // Draw the inner space of the hallway
            for (int i = 0; i < h.length; i += 1) {
                world[P_x][P_y] = t;
                if (h.dir) {
                    P_x += 1;
                } else {
                    P_y += 1;
                }
            }
            P_x = h.p.x;
            P_y = h.p.y;
            //Draw the wall of the hallway
            //Draw the horizontal hallway
            if (h.dir) {
                for (int j = 0; j < h.length + 2; j += 1) {
                    if(world[P_x - 1][P_y - 1] == Tileset.NOTHING){
                        world[P_x - 1][P_y - 1] = w;
                    }
                    P_x += 1;
                }
                P_x -= 2;
                if(world[P_x][P_y] == Tileset.NOTHING){
                    world[P_x][P_y] = w;
                }
                P_y += 1;
                for (int k = 0; k < h.length + 2; k += 1) {
                    if(world[P_x][P_y] == Tileset.NOTHING){
                        world[P_x][P_y] = w;
                    }
                    P_x -= 1;
                }
                if(world[P_x + 1][P_y - 1] == Tileset.NOTHING){
                    world[P_x + 1][P_y - 1] = w;
                }
            } else {
                //Draw the vertical hallway
                for (int i = 0; i < h.length + 2; i += 1) {
                    if(world[P_x - 1][P_y - 1] == Tileset.NOTHING){
                        world[P_x - 1][P_y - 1] = w;
                    }
                    P_y += 1;
                }
                P_y -= 2;
                if(world[P_x][P_y] == Tileset.NOTHING){
                    world[P_x][P_y] = w;
                }
                P_x += 1;
                for (int k = 0; k < h.length + 2; k += 1) {
                    if(world[P_x][P_y] == Tileset.NOTHING){
                        world[P_x][P_y] = w;
                    }
                    P_y -= 1;
                }
                if(world[P_x - 1][P_y + 1] == Tileset.NOTHING){
                    world[P_x - 1][P_y + 1] = w;
                }
            }
        }

    }

    /*
    check if the hallway is legal in the world;
    hallway must not extend out of the world;
    hallway must connect with another hallway or a room.
    hallway must not overlap with room.
     */
    private static boolean check_hallway(hallway h, TETile[][] world) {
        if (h.dir){
            int flag = 0;
            if (h.length + h.p.x + 1> WIDTH ) {
                return false;
            }
            for (int i = 0; i < h.length; i += 1) {
                if (world[h.p.x + i][h.p.y] == Tileset.FLOOR) {
                    return false;
                }
            }
            if (world[h.p.x-1][h.p.y] == Tileset.FLOOR){
                flag += 1;
            }
            if (world[h.p.x + h.length][h.p.y] ==Tileset.FLOOR){
                flag += 1;
            }
            for (int i = 0; i < h.length; i+=1){
                if (world[h.p.x + i][h.p.y + 1] == Tileset.FLOOR) {
                    flag += 1;
                }
                if (world[h.p.x + i][h.p.y - 1] == Tileset.FLOOR) {
                    flag += 1;
                }
            }
            return flag == 1;

        } else {
            if (h.length + h.p.y + 1> HEIGHT) {
                return false;
            }
            for (int i = 0; i < h.length; i += 1) {
                if (world[h.p.x][h.p.y + i] == Tileset.FLOOR) {
                    return false;
                }
            }
            int flag = 0;

            if (world[h.p.x][h.p.y - 1] == Tileset.FLOOR){
                flag += 1;
            }
            if (world[h.p.x][h.p.y + h.length] ==Tileset.FLOOR){
                flag += 1;
            }
            for (int i = 0; i < h.length; i+=1){
                if (world[h.p.x - 1][h.p.y + i] == Tileset.FLOOR) {
                    flag += 1;
                }
                if (world[h.p.x + 1][h.p.y + i] == Tileset.FLOOR) {
                    flag += 1;
                }
            }
            return flag == 1;
        }
    }

    public static void draw_door(TETile world[][], Random random){
        boolean sign = false;

        while(!sign){
            int n_space = 0;
            int n_wall = 0;
            int n_floor = 0;
            Position p = new Position(RandomUtils.uniform(random, 1, WIDTH - 1), RandomUtils.uniform(random, 1, HEIGHT - 1));
            if(world[p.x][p.y] == Tileset.WALL){
                for(int i = p.x - 1; i <= p.x + 1; i += 1 ){
                    for(int j = p.y -1; j <= p.y + 1; j+=1){
                        if (world[i][j] == Tileset.FLOOR){
                            n_floor += 1;
                        } else if(world[i][j] == Tileset.WALL){
                            n_wall += 1;
                        } else{
                            n_space += 1;
                        }
                    }
                }
                if (n_floor >= 2 && n_wall >= 3 && n_space >= 2) {
                    world[p.x][p.y] = Tileset.LOCKED_DOOR;
                    sign = true;
                }
            }
        }
    }

}
