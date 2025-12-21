package Entity.animal;

import Control.Move;
import static GameRunner.RunBomberman.enemy;
import static GameRunner.RunBomberman.list_kill;
import static GameRunner.RunBomberman.width;
import Graphics.Sprite;
import javafx.scene.image.Image;

public class Kondoria extends Animal {

    private int swap_kill = 1;
    private int count_kill = 0;
    private boolean direction;

    public Kondoria(int x, int y, Image img) {
        super(x, y, img);
    }

    public Kondoria(int is_move, int swap, String directionection, int count, int count_to_run) {
        super(4, 1, "up", 0, 0);
    }

    public Kondoria(boolean life) {
        super(life);
    }

    public Kondoria() {
    }

    private void killKondoria(Animal animal) {
        if (count_kill % 16 == 0) {
            if (swap_kill <= Sprite.NUM_KONDORIA_DEAD_FRAME) {
                animal.setImg(Sprite.kondoria_dead[swap_kill - 1].getFxImage());
                swap_kill++;
            } 
            else {
                animal.setLife(false);
                enemy.remove(animal);
                swap_kill = 1;
            }
        }
    }
    private  void kill() {
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
        if (this instanceof Kondoria && !this.life){
            killKondoria(this);
            return ;
        }
        if (this.y % 16 == 0 && this.x % 16 == 0) {//Đi theo thuật toán lên xuống 
            if (this.x / 32 <= 1 || this.x / 32 >= width - 2)
                direction = !direction;

            if (direction)
                Move.left(this);
            else
                Move.right(this);
        }
    }

}
