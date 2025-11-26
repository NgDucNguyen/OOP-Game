package Control;

import Entity.Entity;
import static GameRunner.RunBomberman.*; 

public class Blocked {

    public static class Tile {
        public static final int EMPTY = 0;
        public static final int WALL = 1;
        public static final int BRICK = 2;
        public static final int BOMB = 3;
        public static final int EXPLOSION = 6;
        public static final int PORTAL = 7;
        public static final int ITEM = 8;

        // ★★★ FAKE WALL — Tường giả ★★★
        public static final int FAKE_WALL = 9;  // đi xuyên được nhưng chặn bom
    }

    private static final int TILE_SIZE = 32;
    private static final int ENTITY_WIDTH = 32;
    private static final int ENTITY_HEIGHT = 32;

    // Kiểm tra ngoài map
    private static boolean outOfMap(int x, int y) {
        return x < 0 || y < 0 || x >= id_objects.length || y >= id_objects[0].length;
    }

    // Lấy tile an toàn
    private static int safeTileAt(int tileX, int tileY) {
        if (outOfMap(tileX, tileY)) return Tile.WALL;
        return id_objects[tileX][tileY];
    }

    // Xác định tile có phải SOLID (không đi xuyên được)
    private static boolean isSolid(int tile) {

        // Fake wall
        if (tile == Tile.FAKE_WALL) return false;

        return tile == Tile.WALL ||
                tile == Tile.BRICK ||
                tile == Tile.BOMB;
    }

    // Smooth AABB movement
    private static boolean canMoveSmooth(Entity e, int dx, int dy) {

        int left   = e.getX() + dx;
        int right  = e.getX() + ENTITY_WIDTH  + dx - 1;
        int top    = e.getY() + dy;
        int bottom = e.getY() + ENTITY_HEIGHT + dy - 1;

        int tileLeft   = left   / TILE_SIZE;
        int tileRight  = right  / TILE_SIZE;
        int tileTop    = top    / TILE_SIZE;
        int tileBottom = bottom / TILE_SIZE;

        int t1 = safeTileAt(tileLeft,  tileTop);
        int t2 = safeTileAt(tileRight, tileTop);
        int t3 = safeTileAt(tileLeft,  tileBottom);
        int t4 = safeTileAt(tileRight, tileBottom);

        return !isSolid(t1) && !isSolid(t2) && !isSolid(t3) && !isSolid(t4);
    }

    // Kiểm tra bom có thể lan qua tile
    private static boolean canExplosionPass(int x, int y) {
        int t = safeTileAt(x, y);

        // ★★★ FAKE WALL CHẶN LỬA (giống tường thật) ★★★
        if (t == Tile.FAKE_WALL) return false;

        return t == Tile.EMPTY ||
                t == Tile.BOMB ||
                t == Tile.EXPLOSION ||
                t == Tile.PORTAL ||
                t == Tile.ITEM;
    }

    private static boolean explosionCheck(Entity e, int dx, int dy, int power) {
        int tileX = e.getX() / TILE_SIZE + dx * (power + 1);
        int tileY = e.getY() / TILE_SIZE + dy * (power + 1);
        return canExplosionPass(tileX, tileY);
    }

    // API movement
    public static boolean block_down(Entity entity) { return canMoveSmooth(entity, 0, 1); }
    public static boolean block_up(Entity entity) { return canMoveSmooth(entity, 0, -1); }
    public static boolean block_left(Entity entity) { return canMoveSmooth(entity, -1, 0); }
    public static boolean block_right(Entity entity) { return canMoveSmooth(entity, 1, 0); }

    // API explosion
    public static boolean block_down_bomb(Entity entity, int power) { return explosionCheck(entity, 0, 1, power); }
    public static boolean block_up_bomb(Entity entity, int power) { return explosionCheck(entity, 0, -1, power); }
    public static boolean block_left_bomb(Entity entity, int power) { return explosionCheck(entity, -1, 0, power); }
    public static boolean block_right_bomb(Entity entity, int power) { return explosionCheck(entity, 1, 0, power); }
}
