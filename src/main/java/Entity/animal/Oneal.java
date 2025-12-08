package Entity.animal;

import Graphics.Sprite;
import javafx.scene.image.Image;

import static GameRunner.RunBomberman.*;

import Control.Move;

public class Oneal extends Animal {
    private int swap_kill = 1;
    private int count_kill = 0;

    
    public Oneal(int x, int y, Image img) {
        super(x, y, img);
    }

    public Oneal(int is_move, int swap, String direction, int count, int count_to_run) {
        super(4, 1, "up", 0, 0);
    }

    public Oneal(boolean life) {
        super(life);
    }

    public Oneal() {
        
    }

    private void killOneal(Animal animal) {
        if (count_kill % 16 == 0) {
            if (swap_kill <= Sprite.NUM_ONEAL_DEAD_FRAME) {
                animal.setImg(Sprite.oneal_dead[swap_kill - 1].getFxImage());
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
    @Override
    public void update() {
        count_kill++;
        kill();
        if (this instanceof Oneal && !this.life){
            killOneal(this);
            return;
        }
            
        if (this.y % 16 == 0 && this.x % 16 == 0) {//Di chuyển theo hướng về ng chơi (ko phải cố bắt)
            if (player.getX() < this.x) {
                Move.left(this);
            }
            if (player.getX() > this.x) {
                Move.right(this);
            }
            if (player.getY() > this.y) {
                Move.down(this);
            }
            if (player.getY() < this.y) {
                Move.up(this);
            }
        }
    }

}
