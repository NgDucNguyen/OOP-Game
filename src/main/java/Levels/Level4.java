package Levels;

import static Control.Menu.bomb_number;
import static Control.Menu.time_number;
import Entity.animal.Animal;
import Entity.animal.Ballom;
import static Entity.animal.Bomber.swap_kill;
import Entity.animal.Doll;
import Entity.animal.Oneal;
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

public class Level4 {
    public Level4() {
        enemy.clear();
        block.clear();
        swap_kill = 1;
        new MapCreation("res/levels/Level4.txt");
        player.setLife(true);
        player.setX(32);
        player.setY(32);
        speed = 1;
        is_sound_died = false;
        is_sound_title = false;
        time_number = 150; // Tăng thời gian cho map mê cung
        bomb_number = 45;
        is_bomb = 0;

        player.setImg(Sprite.control_right_2.getFxImage());
        author_view.setImage(new Image("images/transparent.png"));

        // Phối hợp nhiều loại kẻ địch
        enemy.add(new Ballom(5, 5, Sprite.ballom_left_1.getFxImage()));
        enemy.add(new Ballom(17, 3, Sprite.ballom_left_1.getFxImage()));
        enemy.add(new Doll(23, 10, Sprite.doll_left_1.getFxImage()));
        enemy.add(new Oneal(5, 12, Sprite.oneal_right_1.getFxImage()));
        enemy.add(new Oneal(13, 7, Sprite.oneal_right_1.getFxImage()));
        enemy.add(new Doll(2, 9, Sprite.doll_left_1.getFxImage()));
        

        for (Animal animal : enemy) {
            animal.setLife(true);
        }
    }
}