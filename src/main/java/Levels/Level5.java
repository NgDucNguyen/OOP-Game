package Levels;

import static Control.Menu.bomb_number;
import static Control.Menu.time_number;
import Entity.animal.Animal;
import static Entity.animal.Bomber.swap_kill;
import Entity.animal.Doll;
import Entity.animal.Kondoria;
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

public class Level5 {
    public Level5() {
        enemy.clear();
        block.clear();
        swap_kill = 1;
        new MapCreation("res/levels/Level5.txt");
        player.setLife(true);
        player.setX(32);
        player.setY(32);
        speed = 2; // Tăng tốc độ mặc định cho màn cuối
        is_sound_died = false;
        is_sound_title = false;
        time_number = 180;
        bomb_number = 50;
        is_bomb = 0;

        player.setImg(Sprite.control_right_2.getFxImage());
        author_view.setImage(new Image("images/transparent.png"));

        // Kẻ địch mạnh nhất xuất hiện nhiều hơn
        enemy.add(new Kondoria(12, 1, Sprite.kondoria_right_1.getFxImage()));
        enemy.add(new Kondoria(12, 13, Sprite.kondoria_right_1.getFxImage()));
        enemy.add(new Doll(2, 7, Sprite.doll_left_1.getFxImage()));
        enemy.add(new Doll(22, 7, Sprite.doll_left_1.getFxImage()));
        enemy.add(new Oneal(13, 7, Sprite.oneal_right_1.getFxImage()));
        enemy.add(new Oneal(19, 14, Sprite.oneal_right_1.getFxImage()));
        enemy.add(new Doll(7, 9, Sprite.doll_left_1.getFxImage()));
        for (Animal animal : enemy) {
            animal.setLife(true);
        }
    }
}