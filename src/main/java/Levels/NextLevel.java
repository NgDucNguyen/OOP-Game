package Levels;

import static Entity.block.Portal.is_portal;
import static GameRunner.RunBomberman.author_view;
import static GameRunner.RunBomberman.level;
import javafx.scene.image.Image;

public class NextLevel {
    public static boolean wait;
    public static long waiting_time;

    public static void waitToLevelUp() {
        if (wait) {
            Image wait_to_next = new Image("images/levelUpBomberman.png");
            // 1. Reset lại vị trí về góc 0, 32 ( bỏ phần thanh menu)
            author_view.setX(0); 
            author_view.setY(32);
                
            // 2. Reset lại tỷ lệ về 1 (không thu nhỏ nữa)
            author_view.setScaleX(1);
            author_view.setScaleY(1);
                
            // 3. Set kích thước full màn hình
            author_view.setFitWidth(800);
            author_view.setFitHeight(480);
            // add image level up to authorView
            author_view.setImage(wait_to_next);
            // load next Level
            long now = System.currentTimeMillis();
            if (now - waiting_time > 3000) {
                switch(level) {
                    case 1:
                        is_portal = false;
                        new Level2();
                        break;
                    case 2:
                        new Level3();
                        break;
                    case 3:
                        new Level4();
                        break;
                    case 4:
                        new Level5();
                        break;
                    case 5:
                        new Level1();
                        break;
                }
                wait = false;
            }
        }
    }
}
