package Entity.animal;

import javafx.scene.image.Image;
import Graphics.Sprite;

import java.util.Random;

import Control.Move;

import static GameRunner.RunBomberman.enemy;
import static GameRunner.RunBomberman.list_kill;

public class Ballom extends Animal {
    private int swap_kill = 1;
    private int delayy = 0;  //Để tạo hoạt ảnh

    public Ballom(int is_move, int swap, String direction, int count, int count_to_run) {
        super(4, 1, "up", 0, 0);
    }
    
    public Ballom(){

    }
    
    private void killBallom(Animal animal) {    //Bomber destroys Balloon
        if (delayy % 16 == 0) {
            if (swap_kill <= Sprite.NUM_MOB_DEAD_FRAME) {
                animal.setImg(Sprite.mob_dead[swap_kill - 1].getFxImage());
                swap_kill++;
            }
            else {
                animal.setLife(false);
                enemy.remove(animal);
                swap_kill = 1;
            }
        }
    }

    private void kill() {
        for (Animal animal : enemy) {
            if (list_kill[animal.getX() / 32][animal.getY() / 32] == 4) {
                animal.setLife(false);
            }
        }
    }

    public Ballom(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        kill();
        delayy++;
        if (this instanceof Ballom && !this.life){
            killBallom(this);
            return;
        }
                

        if (this.y % 16 == 0 && this.x % 16 == 0) {
            Random random = new Random();
            int direction = random.nextInt(4);
            switch (direction) {
                case 0:
                    Move.down(this);
                    break;
                case 1:
                    Move.up(this);
                    break;
                case 2:
                    Move.left(this);
                    break;
                case 3:
                    Move.right(this);
                    break;
            }
        }
    }
}
