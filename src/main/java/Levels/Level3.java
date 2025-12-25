package Levels;

import static Control.Menu.bomb_number;
import static Control.Menu.time_number;
import Entity.animal.Animal;
import static Entity.animal.Bomber.swap_kill;
import Entity.animal.Doll;
import static Entity.block.Bomb.is_bomb;
import static Entity.items.SpeedItem.speed;
import static Features.SoundManager.is_sound_died;
import static Features.SoundManager.is_sound_title;
import static GameRunner.RunBomberman.author_view;
import static GameRunner.RunBomberman.block;
import static GameRunner.RunBomberman.enemy;
import static GameRunner.RunBomberman.player;
import Graphics.MapCreation;
import Graphics.Sprite;
import javafx.scene.image.Image;

public class Level3 {
    public Level3() {
        enemy.clear();
        block.clear();
        swap_kill = 1;
        new MapCreation("res/levels/Level3.txt");
        player.setLife(true);
        player.setX(32);
        player.setY(32);
        speed = 1;
        is_sound_died = false;
        is_sound_title = false;
        time_number = 120;
        bomb_number = 40;
        is_bomb = 0;

        player.setImg(Sprite.control_right_2.getFxImage());
        Image transparent = new Image("images/transparent.png");
        author_view.setImage(transparent);

        

        Animal enemy3 = new Doll(7, 5, Sprite.doll_left_1.getFxImage());
        enemy.add(enemy3);
        Animal enemy4 = new Doll(9, 10, Sprite.doll_left_1.getFxImage());
        enemy.add(enemy4);

        for (Animal animal : enemy) {
            animal.setLife(true);
        }
    }
}
