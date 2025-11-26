package Control;

import Entity.Entity;
import static GameRunner.RunBomberman.*; // id_objects[][] phải có sẵn

public class Blocked {

    public static class Tile {
        public static final int EMPTY = 0;
        public static final int WALL = 1;
        public static final int BRICK = 2;
        public static final int BOMB = 3;
        public static final int EXPLOSION = 6;
        public static final int PORTAL = 7;
        public static final int ITEM = 8;
    }

    private static final int TILE_SIZE = 32; // Kích thước mỗi tile trong map


    // Kiểm tra tọa độ ngoài map
    private static boolean outOfMap(int x, int y) {
        return x < 0 || y < 0 || x >= id_objects.length || y >= id_objects[0].length;
    }

    // Lấy tile an toàn tại (x, y)
    private static int safeTileAt(int x, int y) {
        if (outOfMap(x, y)) return Tile.WALL;
        return id_objects[x][y];
    }

    // Kiểm tra entity có thể đi đến tile (dx, dy)
    private static boolean canMove(Entity e, int dx, int dy) {
        int x = e.getX() / TILE_SIZE + dx;
        int y = e.getY() / TILE_SIZE + dy;
        int tile = safeTileAt(x, y);
        return tile == Tile.EMPTY || tile == Tile.ITEM || tile == Tile.PORTAL;
    }

    // Kiểm tra bom có thể lan qua tile (dx, dy)
    private static boolean canExplosionPass(int x, int y) {
        int t = safeTileAt(x, y);
        return t == Tile.EMPTY ||
                t == Tile.BOMB ||
                t == Tile.EXPLOSION ||
                t == Tile.PORTAL ||
                t == Tile.ITEM;
    }

    private static boolean explosionCheck(Entity e, int dx, int dy, int power) {
        int x = e.getX() / TILE_SIZE + dx * (power + 1);
        int y = e.getY() / TILE_SIZE + dy * (power + 1);
        return canExplosionPass(x, y);
    }

    public static boolean block_down(Entity entity) {
        return canMove(entity, 0, 1);
    }

    public static boolean block_up(Entity entity) {
        return canMove(entity, 0, -1);
    }

    public static boolean block_left(Entity entity) {
        return canMove(entity, -1, 0);
    }

    public static boolean block_right(Entity entity) {
        return canMove(entity, 1, 0);
    }

    public static boolean block_down_bomb(Entity entity, int power) {
        return explosionCheck(entity, 0, 1, power);
    }

    public static boolean block_up_bomb(Entity entity, int power) {
        return explosionCheck(entity, 0, -1, power);
    }

    public static boolean block_left_bomb(Entity entity, int power) {
        return explosionCheck(entity, -1, 0, power);
    }

    public static boolean block_right_bomb(Entity entity, int power) {
        return explosionCheck(entity, 1, 0, power);
    }
}
