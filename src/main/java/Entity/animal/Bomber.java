package Entity.animal;

import javafx.scene.image.Image;
import Graphics.Sprite;

import static GameRunner.RunBomberman.*;

public class Bomber extends Animal {
    public static int swap_kill = 1;
    private static int count_kill = 0;

    public Bomber(int is_move, int swap, String direction, int count, int count_to_run) {
        super(8, 1, "down", 0, 0);
    }

    public Bomber() {

    }

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
    }

     private void killBomber(Animal animal) {
        if (count_kill % 16 == 0) {
            if (swap_kill <= Sprite.NUM_PLAYER_DEAD_FRAME) {
                animal.setImg(Sprite.player_dead[swap_kill - 1].getFxImage());
                swap_kill++;
            }
            else { 
                animal.setImg(Sprite.transparent.getFxImage());
                running = false;
                Image gameOver = new Image("images/gameOver.png");
                author_view.setImage(gameOver);
            }
        }
    }

    private void checkBombs() {//Người chơi chạm vào quái
        if (list_kill[player.getX() / 32][player.getY() / 32] == 4)
            player.setLife(false);
    }
    private void checkEnemy() {
        int ax = player.getX();
        int ay = player.getY();
        for (Animal animal : enemy) {
            if (!animal.isLife()) {
            continue; // Nếu quái đã chết, bỏ qua, không kiểm tra va chạm
        }
            int bx = animal.getX();
            int by = animal.getY();
            if (
                ax == bx && by - 32 <= ay && by + 32 >= ay
                || ay == by && bx - 32 <= ax && bx + 32 >= ax
            ) 
            {
                player.life = false;
                break;
            }
        }
    }

    @Override
    public void update() {
        checkBombs();
        checkEnemy();
        count_kill++;
        if (!player.life)
            killBomber(player);
    }

}
